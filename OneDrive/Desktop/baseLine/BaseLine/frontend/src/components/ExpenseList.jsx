import { useState, useEffect } from 'react';
import { expenseAPI } from '../services/api';
import { FaRupeeSign, FaChevronUp, FaChevronDown } from 'react-icons/fa';
import ConfirmDialog from './ConfirmDialog';

const ExpenseList = ({ selectedDate, onEditExpense, onExpenseDeleted, refreshTrigger }) => {
  const [expenses, setExpenses] = useState([]);
  const [stats, setStats] = useState({ categoryTotals: {}, dailyOutgoing: 0, dailyIncoming: 0, monthlyOutgoing: 0, monthlyIncoming: 0 });
  const [loading, setLoading] = useState(false);
  const [isVisible, setIsVisible] = useState(true);
  const [confirmDialog, setConfirmDialog] = useState({ isOpen: false, expenseId: null });

  useEffect(() => {
    loadExpenses();
  }, [selectedDate, refreshTrigger]);

  useEffect(() => {
    loadStats();
  }, [expenses]);

  const loadExpenses = async () => {
    setLoading(true);
    try {
      const dateStr = selectedDate.toISOString().split('T')[0];
      const response = await expenseAPI.getExpenses({ date: dateStr });
      setExpenses(response.data);
    } catch (error) {
      console.error('Error loading expenses:', error);
      setExpenses([]);
    } finally {
      setLoading(false);
    }
  };

  const loadStats = async () => {
    try {
      const year = new Date().getFullYear();
      const month = new Date().getMonth() + 1;
      
      const monthlyResponse = await expenseAPI.getExpenseStats({ month, year });

      // Calculate daily outgoing/incoming from current expenses array
      let dailyOutgoing = 0, dailyIncoming = 0;
      expenses.forEach(exp => {
        if (exp.type === 'DEBIT') dailyOutgoing += exp.amount;
        else dailyIncoming += exp.amount;
      });

      // Calculate monthly outgoing/incoming from stats
      let monthlyOutgoing = 0, monthlyIncoming = 0;
      if (monthlyResponse.data.categoryTotals) {
        Object.values(monthlyResponse.data.categoryTotals).forEach(total => {
          monthlyOutgoing += total;
        });
      }

      setStats({
        categoryTotals: monthlyResponse.data.categoryTotals || {},
        dailyOutgoing: dailyOutgoing,
        dailyIncoming: dailyIncoming,
        monthlyOutgoing: monthlyOutgoing,
        monthlyIncoming: monthlyIncoming
      });
    } catch (error) {
      console.error('Error loading stats:', error);
    }
  };

  const handleDelete = async (id) => {
    setConfirmDialog({ isOpen: true, expenseId: id });
  };

  const confirmDelete = async () => {
    try {
      await expenseAPI.deleteExpense(confirmDialog.expenseId);
      await loadExpenses();
      setConfirmDialog({ isOpen: false, expenseId: null });
      onExpenseDeleted();
    } catch (error) {
      console.error('Error deleting expense:', error);
      setConfirmDialog({ isOpen: false, expenseId: null });
    }
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
    return days[date.getDay()];
  };

  if (loading) {
    return (
      <div className="bg-surface rounded-lg shadow-md p-6 border border-surface">
        <div className="text-center text-text">Loading expenses...</div>
      </div>
    );
  }

  return (
    <div className="space-y-4 flex flex-col h-full">
      <ConfirmDialog
        isOpen={confirmDialog.isOpen}
        title="Delete Expense"
        message="Are you sure you want to delete this expense? This action cannot be undone."
        confirmText="Delete"
        cancelText="Cancel"
        isDangerous={true}
        onConfirm={confirmDelete}
        onCancel={() => setConfirmDialog({ isOpen: false, expenseId: null })}
      />
      <div className="bg-surface rounded-lg shadow-md p-6 border border-surface hover:shadow-lg transition-shadow flex-shrink-0">
        <div className="flex justify-between items-center mb-4">
          <h3 className="text-xl font-bold text-text">Expense Statistics</h3>
          <button
            onClick={() => setIsVisible(!isVisible)}
            className="p-2 hover:bg-primary/20 text-text rounded transition-colors"
            title={isVisible ? 'Hide' : 'Show'}
          >
            {isVisible ? <FaChevronUp className="text-lg" /> : <FaChevronDown className="text-lg" />}
          </button>
        </div>
        {isVisible && (
          <>
        <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mb-4">
          <div className="bg-background p-4 rounded border border-surface hover:border-primary/50 transition">
            <p className="text-xs text-text/70 uppercase tracking-wide font-semibold">Daily Outgoing</p>
            <p className="text-2xl font-bold text-red-500 flex items-center gap-1 mt-1">
              <FaRupeeSign className="text-lg" />
              {stats.dailyOutgoing.toFixed(2)}
            </p>
          </div>
          <div className="bg-background p-4 rounded border border-surface hover:border-primary/50 transition">
            <p className="text-xs text-text/70 uppercase tracking-wide font-semibold">Daily Incoming</p>
            <p className="text-2xl font-bold text-green-500 flex items-center gap-1 mt-1">
              <FaRupeeSign className="text-lg" />
              {stats.dailyIncoming.toFixed(2)}
            </p>
          </div>
          <div className="bg-background p-4 rounded border border-surface hover:border-primary/50 transition">
            <p className="text-xs text-text/70 uppercase tracking-wide font-semibold">Monthly Outgoing</p>
            <p className="text-2xl font-bold text-red-500 flex items-center gap-1 mt-1">
              <FaRupeeSign className="text-lg" />
              {stats.monthlyOutgoing.toFixed(2)}
            </p>
          </div>
          <div className="bg-background p-4 rounded border border-surface hover:border-primary/50 transition">
            <p className="text-xs text-text/70 uppercase tracking-wide font-semibold">Monthly Incoming</p>
            <p className="text-2xl font-bold text-green-500 flex items-center gap-1 mt-1">
              <FaRupeeSign className="text-lg" />
              {stats.monthlyIncoming.toFixed(2)}
            </p>
          </div>
        </div>
        {Object.keys(stats.categoryTotals).length > 0 && (
          <div>
            <p className="text-sm font-semibold text-text mb-3">Category Breakdown:</p>
            <div className="space-y-2">
              {Object.entries(stats.categoryTotals).map(([category, total]) => (
                <div key={category} className="flex justify-between items-center text-sm text-text bg-background/50 p-2 rounded">
                  <span className="capitalize font-medium">{category}</span>
                  <span className="font-semibold flex items-center gap-1">
                    <FaRupeeSign className="text-xs" />
                    {total.toFixed(2)}
                  </span>
                </div>
              ))}
            </div>
          </div>
        )}
          </>
        )}
      </div>

      <div className="bg-surface rounded-lg shadow-md p-6 border border-surface hover:shadow-lg transition-shadow flex-1 overflow-hidden flex flex-col">
        <div className="flex-shrink-0 mb-4">
          <h3 className="text-xl font-bold text-text">Expenses for {selectedDate.toLocaleDateString()}</h3>
        </div>
        {isVisible && (
          <div className="flex-1 overflow-y-auto">
        {expenses.length === 0 ? (
          <p className="text-text/50 text-center py-8">No expenses recorded for this date</p>
        ) : (
          <div className="space-y-3 pr-2">
            {expenses.map((expense) => (
              <div
                key={expense._id}
                className="border border-surface rounded-lg p-4 hover:bg-background hover:border-primary/50 transition bg-background/50"
              >
                <div className="flex justify-between items-start mb-3">
                  <div className="flex-1">
                    <div className="flex items-center gap-2 mb-2 flex-wrap">
                      <span className="text-xs text-text/60 font-medium">{formatDate(expense.date)}</span>
                      <span className={`px-2 py-1 rounded text-xs font-semibold ${
                        expense.type === 'DEBIT' ? 'bg-red-500/20 text-red-500' : 'bg-green-500/20 text-green-500'
                      }`}>
                        {expense.type}
                      </span>
                      <span className="px-2 py-1 rounded text-xs bg-primary/20 text-primary capitalize font-medium">
                        {expense.category}
                      </span>
                    </div>
                    <p className="font-semibold text-text text-sm">{expense.where}</p>
                    {expense.category === 'borrow' && expense.from && <p className="text-xs text-text/70 mt-1">From: <span className="font-medium">{expense.from}</span></p>}
                    {expense.category === 'lend' && expense.to && <p className="text-xs text-text/70 mt-1">To: <span className="font-medium">{expense.to}</span></p>}
                    {expense.with && <p className="text-xs text-text/70 mt-1">With: <span className="font-medium">{expense.with}</span></p>}
                    {expense.why && <p className="text-xs text-text/70 mt-1">Why: <span className="font-medium">{expense.why}</span></p>}
                  </div>
                  <div className="text-right flex-shrink-0 ml-4">
                    <p className={`text-lg font-bold flex items-center gap-1 whitespace-nowrap ${
                      expense.type === 'DEBIT' ? 'text-red-500' : 'text-green-500'
                    }`}>
                      {expense.type === 'DEBIT' ? '−' : '+'}
                      <FaRupeeSign className="text-base" />
                      {expense.amount.toFixed(2)}
                    </p>
                  </div>
                </div>
                <div className="flex gap-2 mt-3 pt-3 border-t border-surface/50">
                  <button
                    onClick={() => onEditExpense(expense)}
                    className="text-xs bg-primary hover:bg-primary/90 active:bg-primary/75 text-background px-3 py-1.5 rounded transition-colors font-semibold"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => handleDelete(expense._id)}
                    className="text-xs bg-red-600 hover:bg-red-700 active:bg-red-800 text-white px-3 py-1.5 rounded transition-colors font-semibold"
                  >
                    Delete
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
          </div>
        )}
      </div>
    </div>
  );
};

export default ExpenseList;

