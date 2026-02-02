import { useState, useEffect } from 'react';
import { journalAPI } from '../services/api';
import { FaChevronUp, FaChevronDown } from 'react-icons/fa';

const Journal = ({ selectedDate }) => {
  const [content, setContent] = useState('');
  const [loading, setLoading] = useState(false);
  const [saving, setSaving] = useState(false);
  const [isVisible, setIsVisible] = useState(true);

  useEffect(() => {
    loadJournal();
  }, [selectedDate]);

  const loadJournal = async () => {
    setLoading(true);
    try {
      const dateStr = selectedDate.toISOString().split('T')[0];
      const response = await journalAPI.getJournal(dateStr);
      setContent(response.data.content || '');
    } catch (error) {
      console.error('Error loading journal:', error);
      setContent('');
    } finally {
      setLoading(false);
    }
  };


  const handleSave = async () => {
    setSaving(true);
    try {
      const dateStr = selectedDate.toISOString().split('T')[0];
      await journalAPI.createOrUpdateJournal({
        date: dateStr,
        content
      });
      alert('Journal saved successfully!');
    } catch (error) {
      console.error('Error saving journal:', error);
      alert('Error saving journal');
    } finally {
      setSaving(false);
    }
  };

  const formatDate = (date) => {
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    return date.toLocaleDateString('en-US', options);
  };

  if (loading) {
    return (
      <div className="bg-surface rounded-lg shadow-md p-6 h-full border border-surface">
        <div className="text-center text-text">Loading...</div>
      </div>
    );
  }

  return (
    <div className="bg-surface rounded-lg shadow-md p-6 h-full flex flex-col border border-surface hover:shadow-lg transition-shadow">
      <div className="mb-4 flex justify-between items-start">
        <div>
          <h2 className="text-2xl font-bold text-text">Journal</h2>
          <p className="text-text/70 mt-1 text-sm">{formatDate(selectedDate)}</p>
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
        <div className="flex-1 flex flex-col min-h-0">
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            placeholder="Write your thoughts for today..."
            className="flex-1 w-full p-4 border border-surface bg-background text-text rounded-lg resize-none focus:outline-none focus:ring-2 focus:ring-primary placeholder:text-text/50 font-light text-sm leading-relaxed"
          />
          <button
            onClick={handleSave}
            disabled={saving}
            className="mt-4 bg-primary hover:bg-primary/90 active:bg-primary/75 text-background px-6 py-2 rounded font-semibold transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
          >
            {saving ? 'Saving...' : 'Save Journal'}
          </button>
        </div>
      )}
    </div>
  );
};

export default Journal;

