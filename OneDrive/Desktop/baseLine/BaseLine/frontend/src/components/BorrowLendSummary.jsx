import { useState, useEffect } from 'react';
import { expenseAPI } from '../services/api';
import { FaRupeeSign, FaChevronUp, FaChevronDown } from 'react-icons/fa';

const BorrowLendSummary = ({ selectedDate, refreshTrigger }) => {
  const [expenseData, setExpenseData] = useState([]);
  const [loadingExpenses, setLoadingExpenses] = useState(false);
  const [isVisible, setIsVisible] = useState(true);

  useEffect(() => {
    loadExpenseData();
  }, [refreshTrigger]);

  const loadExpenseData = async () => {
    setLoadingExpenses(true);
    try {
      // Fetch ALL expenses without date filter to get all borrow/lend records
      const response = await expenseAPI.getExpenses();
      const expenses = response.data;
      
      // Filter expenses for borrow and lend categories
      const borrowLendExpenses = expenses.filter(exp => 
        exp.category === 'borrow' || exp.category === 'lend'
      );
      
      // Group by name (using 'from' for borrow, 'to' for lend, or 'with' or 'where' as fallback)
      const groupedData = {};
      borrowLendExpenses.forEach(exp => {
        let name;
        if (exp.category === 'borrow') {
          name = exp.from || exp.with || exp.where || 'Unknown';
        } else if (exp.category === 'lend') {
          name = exp.to || exp.with || exp.where || 'Unknown';
        } else {
          name = exp.from || exp.to || exp.with || exp.where || 'Unknown';
        }
        
        if (!groupedData[name]) {
          groupedData[name] = {
            name: name,
            borrowedAmount: 0,
            lendAmount: 0,
            balance: 0
          };
        }
        
        if (exp.category === 'borrow') {
          groupedData[name].borrowedAmount += exp.amount;
        } else if (exp.category === 'lend') {
          groupedData[name].lendAmount += exp.amount;
        }
      });
      
      // Calculate balance for each entry
      Object.values(groupedData).forEach(entry => {
        entry.balance = entry.borrowedAmount - entry.lendAmount;
      });
      
      setExpenseData(Object.values(groupedData));
    } catch (error) {
      console.error('Error loading expense data:', error);
      setExpenseData([]);
    } finally {
      setLoadingExpenses(false);
    }
  };

  return (
    <div className="bg-surface rounded-lg shadow-md p-6 h-full flex flex-col border border-surface hover:shadow-lg transition-shadow">
      <div className="mb-4 flex justify-between items-center">
        <div>
          <h2 className="text-2xl font-bold text-text">Borrow/Lend Summary</h2>
        </div>
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
          {loadingExpenses ? (
            <div className="text-center text-text/50 py-8">Loading expense data...</div>
          ) : expenseData.length === 0 ? (
            <div className="text-center text-text/50 py-8">No borrow/lend entries for this date</div>
          ) : (
            <div className="flex-1 overflow-hidden border border-surface rounded-lg bg-background flex flex-col">
              <div className="overflow-x-auto overflow-y-auto flex-1">
                <table className="w-full text-sm">
                  <thead className="sticky top-0 bg-surface border-b border-surface">
                    <tr>
                      <th className="px-4 py-3 text-left text-text font-semibold whitespace-nowrap">Name</th>
                      <th className="px-4 py-3 text-right text-text font-semibold whitespace-nowrap">Borrowed</th>
                      <th className="px-4 py-3 text-right text-text font-semibold whitespace-nowrap">Lend</th>
                      <th className="px-4 py-3 text-right text-text font-semibold whitespace-nowrap">Balance</th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-surface">
                    {expenseData.map((entry, index) => (
                      <tr key={index} className="hover:bg-surface/50 transition-colors">
                        <td className="px-4 py-3 text-text font-medium">{entry.name.charAt(0).toUpperCase() + entry.name.slice(1)}</td>
                        <td className="px-4 py-3 text-right text-text">
                          <span className="inline-flex items-center gap-1 whitespace-nowrap">
                            <FaRupeeSign className="text-xs flex-shrink-0" />
                            <span>{entry.borrowedAmount.toFixed(2)}</span>
                          </span>
                        </td>
                        <td className="px-4 py-3 text-right text-text">
                          <span className="inline-flex items-center gap-1 whitespace-nowrap">
                            <FaRupeeSign className="text-xs flex-shrink-0" />
                            <span>{entry.lendAmount.toFixed(2)}</span>
                          </span>
                        </td>
                        <td className={`px-4 py-3 text-right font-semibold ${
                          entry.balance > 0 ? 'text-red-500' : entry.balance < 0 ? 'text-green-500' : 'text-text'
                        }`}>
                          <span className="inline-flex items-center gap-1 whitespace-nowrap">
                            <span className="flex-shrink-0">{entry.balance > 0 ? '+' : entry.balance < 0 ? '−' : ''}</span>
                            <FaRupeeSign className="text-xs flex-shrink-0" />
                            <span>{Math.abs(entry.balance).toFixed(2)}</span>
                          </span>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}
        </>
      )}
    </div>
  );
};

export default BorrowLendSummary;


