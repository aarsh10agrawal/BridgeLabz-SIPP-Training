import { useState, useEffect } from 'react';
import { expenseAPI } from '../services/api';
import { FaChevronUp, FaChevronDown } from 'react-icons/fa';

const ExpenseForm = ({ selectedDate, onExpenseAdded, editingExpense, onCancelEdit }) => {
  const [formData, setFormData] = useState({
    where: '',
    with: '',
    type: 'DEBIT',
    why: '',
    amount: '',
    category: 'food',
    from: '',
    to: ''
  });
  const [submitting, setSubmitting] = useState(false);
  const [isVisible, setIsVisible] = useState(true);

  const categories = [
    'food',
    'travel',
    'lend',
    'borrow',
    'shopping',
    'bills',
    'entertainment',
    'health',
    'education',
    'other'
  ];

  useEffect(() => {
    if (editingExpense) {
      setFormData({
        where: editingExpense.where || '',
        with: editingExpense.with || '',
        type: editingExpense.type || 'DEBIT',
        why: editingExpense.why || '',
        amount: editingExpense.amount || '',
        category: editingExpense.category || 'food',
        from: editingExpense.from || '',
        to: editingExpense.to || ''
      });
    } else {
      setFormData({
        where: '',
        with: '',
        type: 'DEBIT',
        why: '',
        amount: '',
        category: 'food',
        from: '',
        to: ''
      });
    }
  }, [editingExpense]);

  const handleChange = (e) => {
    const newFormData = {
      ...formData,
      [e.target.name]: e.target.value
    };
    
    // Clear "from" and "to" fields if category changes away from borrow/lend
    if (e.target.name === 'category') {
      if (e.target.value !== 'borrow' && e.target.value !== 'lend') {
        newFormData.from = '';
        newFormData.to = '';
      } else if (e.target.value === 'borrow') {
        // Clear "to" when switching to borrow
        newFormData.to = '';
      } else if (e.target.value === 'lend') {
        // Clear "from" when switching to lend
        newFormData.from = '';
      }
    }
    
    setFormData(newFormData);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);

    try {
      const dateStr = selectedDate.toISOString().split('T')[0];
      const payload = {
        date: dateStr,
        where: formData.where,
        with: formData.with,
        type: formData.type,
        why: formData.why,
        amount: parseFloat(formData.amount),
        category: formData.category,
        from: formData.category === 'borrow' ? formData.from.toLowerCase().trim() : '',
        to: formData.category === 'lend' ? formData.to.toLowerCase().trim() : ''
      };

      if (editingExpense) {
        await expenseAPI.updateExpense(editingExpense._id, payload);
      } else {
        await expenseAPI.createExpense(payload);
      }

      setFormData({
        where: '',
        with: '',
        type: 'DEBIT',
        why: '',
        amount: '',
        category: 'food',
        from: '',
        to: ''
      });

      onExpenseAdded();
      if (onCancelEdit) onCancelEdit();
    } catch (error) {
      console.error('Error saving expense:', error);
      alert('Error saving expense');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="bg-surface rounded-lg shadow-md p-6 border border-surface hover:shadow-lg transition-shadow">
      <div className="flex justify-between items-center mb-4">
        <h3 className="text-xl font-bold text-text">
          {editingExpense ? 'Edit Expense' : 'Add New Expense'}
        </h3>
        <button
          onClick={() => setIsVisible(!isVisible)}
          className="p-2 hover:bg-primary/20 text-text rounded transition-colors"
          title={isVisible ? 'Hide' : 'Show'}
        >
          {isVisible ? <FaChevronUp className="text-lg" /> : <FaChevronDown className="text-lg" />}
        </button>
      </div>
      {isVisible && (
        <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block text-sm font-medium text-text mb-2">
            Where did I spend <span className="text-red-500">*</span>
          </label>
          <input
            type="text"
            name="where"
            value={formData.where}
            onChange={handleChange}
            required
            className="w-full px-3 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary transition"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-text mb-2">
            With
          </label>
          <input
            type="text"
            name="with"
            value={formData.with}
            onChange={handleChange}
            className="w-full px-3 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary transition"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-text mb-2">
            Type <span className="text-red-500">*</span>
          </label>
          <select
            name="type"
            value={formData.type}
            onChange={handleChange}
            required
            className="w-full px-3 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary transition"
          >
            <option value="DEBIT">DEBIT</option>
            <option value="CREDIT">CREDIT</option>
          </select>
        </div>

        <div>
          <label className="block text-sm font-medium text-text mb-2">
            Why
          </label>
          <input
            type="text"
            name="why"
            value={formData.why}
            onChange={handleChange}
            className="w-full px-3 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary transition"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-text mb-2">
            Amount <span className="text-red-500">*</span>
          </label>
          <input
            type="number"
            name="amount"
            value={formData.amount}
            onChange={handleChange}
            required
            min="0"
            step="0.01"
            className="w-full px-3 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary transition"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-text mb-2">
            Category <span className="text-red-500">*</span>
          </label>
          <select
            name="category"
            value={formData.category}
            onChange={handleChange}
            required
            className="w-full px-3 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary transition"
          >
            {categories.map((cat) => (
              <option key={cat} value={cat}>
                {cat.charAt(0).toUpperCase() + cat.slice(1)}
              </option>
            ))}
          </select>
        </div>

        {formData.category === 'borrow' && (
          <div>
            <label className="block text-sm font-medium text-text mb-2">
              From <span className="text-red-500">*</span>
            </label>
            <input
              type="text"
              name="from"
              value={formData.from}
              onChange={handleChange}
              required
              className="w-full px-3 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary transition"
              placeholder="Enter person name"
            />
          </div>
        )}

        {formData.category === 'lend' && (
          <div>
            <label className="block text-sm font-medium text-text mb-2">
              To <span className="text-red-500">*</span>
            </label>
            <input
              type="text"
              name="to"
              value={formData.to}
              onChange={handleChange}
              required
              className="w-full px-3 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary transition"
              placeholder="Enter person name"
            />
          </div>
        )}

        <div className="flex gap-3 pt-2">
          <button
            type="submit"
            disabled={submitting}
            className="flex-1 bg-primary hover:bg-primary/90 active:bg-primary/75 text-background px-4 py-2 rounded font-semibold transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
          >
            {submitting ? 'Saving...' : editingExpense ? 'Update' : 'Add Expense'}
          </button>
          {editingExpense && (
            <button
              type="button"
              onClick={onCancelEdit}
              className="px-4 py-2 bg-surface hover:bg-surface/80 text-text rounded transition border border-surface font-medium"
            >
              Cancel
            </button>
          )}
        </div>
      </form>
      )}
    </div>
  );
};

export default ExpenseForm;

