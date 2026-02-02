import { useState, useEffect } from 'react';
import { taskAPI } from '../services/api';
import { FaEdit, FaTrash, FaCheck, FaChevronUp, FaChevronDown } from 'react-icons/fa';
import ConfirmDialog from './ConfirmDialog';

const TaskList = ({ selectedDate }) => {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState('');
  const [editingId, setEditingId] = useState(null);
  const [editingText, setEditingText] = useState('');
  const [loading, setLoading] = useState(false);
  const [submitting, setSubmitting] = useState(false);
  const [isVisible, setIsVisible] = useState(true);
  const [confirmDialog, setConfirmDialog] = useState({ isOpen: false, taskId: null });

  useEffect(() => {
    loadTasks();
  }, [selectedDate]);

  const loadTasks = async () => {
    setLoading(true);
    try {
      const dateStr = selectedDate.toISOString().split('T')[0];
      const response = await taskAPI.getTasks(dateStr);
      setTasks(response.data);
    } catch (error) {
      console.error('Error loading tasks:', error);
      setTasks([]);
    } finally {
      setLoading(false);
    }
  };

  const handleAddTask = async (e) => {
    e.preventDefault();
    if (!newTask.trim()) return;

    setSubmitting(true);
    try {
      const dateStr = selectedDate.toISOString().split('T')[0];
      const response = await taskAPI.createTask({
        date: dateStr,
        task: newTask.trim()
      });
      setTasks([...tasks, response.data]);
      setNewTask('');
    } catch (error) {
      console.error('Error creating task:', error);
      alert('Error creating task');
    } finally {
      setSubmitting(false);
    }
  };

  const handleStartEdit = (task) => {
    setEditingId(task._id);
    setEditingText(task.task);
  };

  const handleSaveEdit = async (id) => {
    if (!editingText.trim()) {
      setEditingId(null);
      return;
    }

    try {
      const response = await taskAPI.updateTask(id, { task: editingText.trim() });
      setTasks(tasks.map(t => t._id === id ? response.data : t));
      setEditingId(null);
      setEditingText('');
    } catch (error) {
      console.error('Error updating task:', error);
      alert('Error updating task');
    }
  };

  const handleCancelEdit = () => {
    setEditingId(null);
    setEditingText('');
  };

  const handleToggleComplete = async (id) => {
    try {
      const response = await taskAPI.toggleTask(id);
      setTasks(tasks.map(t => t._id === id ? response.data : t));
    } catch (error) {
      console.error('Error toggling task:', error);
      alert('Error updating task');
    }
  };

  const handleDelete = async (id) => {
    setConfirmDialog({ isOpen: true, taskId: id });
  };

  const confirmDelete = async () => {
    try {
      await taskAPI.deleteTask(confirmDialog.taskId);
      setTasks(tasks.filter(t => t._id !== confirmDialog.taskId));
      setConfirmDialog({ isOpen: false, taskId: null });
    } catch (error) {
      console.error('Error deleting task:', error);
      setConfirmDialog({ isOpen: false, taskId: null });
    }
  };

  const handleKeyPress = (e, id) => {
    if (e.key === 'Enter') {
      handleSaveEdit(id);
    } else if (e.key === 'Escape') {
      handleCancelEdit();
    }
  };

  if (loading) {
    return (
      <div className="bg-surface rounded-lg shadow-md p-6 border border-surface">
        <div className="text-center text-text">Loading tasks...</div>
      </div>
    );
  }

  return (
    <div className="bg-surface rounded-lg shadow-md p-6 border border-surface hover:shadow-lg transition-shadow flex flex-col h-full">
      <ConfirmDialog
        isOpen={confirmDialog.isOpen}
        title="Delete Task"
        message="Are you sure you want to delete this task? This action cannot be undone."
        confirmText="Delete"
        cancelText="Cancel"
        isDangerous={true}
        onConfirm={confirmDelete}
        onCancel={() => setConfirmDialog({ isOpen: false, taskId: null })}
      />
      <div className="flex justify-between items-center mb-4 flex-shrink-0">
        <h3 className="text-xl font-bold text-text">Daily Tasks</h3>
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
          <form onSubmit={handleAddTask} className="mb-4 flex-shrink-0">
        <div className="flex gap-2">
          <input
            type="text"
            value={newTask}
            onChange={(e) => setNewTask(e.target.value)}
            placeholder="Add a task for today"
            className="flex-1 px-3 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary placeholder:text-text/50 transition"
          />
          <button
            type="submit"
            disabled={submitting || !newTask.trim()}
            className="bg-primary hover:bg-primary/90 active:bg-primary/75 text-background px-4 py-2 rounded font-semibold transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
          >
            Add Task
          </button>
        </div>
      </form>

      <div className="flex-1 overflow-y-auto space-y-2 pr-2">
        {tasks.length === 0 ? (
          <p className="text-text/50 text-center py-8">No tasks for this date</p>
        ) : (
          tasks.map((task) => (
            <div
              key={task._id}
              className={`flex items-center gap-3 p-3 rounded-lg border transition ${
                task.completed 
                  ? 'bg-green-500/10 border-green-500/30 hover:bg-green-500/15' 
                  : 'bg-background border-surface hover:bg-background/80 hover:border-primary/50'
              }`}
            >
              <div className="flex-1 flex items-center gap-3 min-w-0">
                {editingId === task._id ? (
                  <input
                    type="text"
                    value={editingText}
                    onChange={(e) => setEditingText(e.target.value)}
                    onKeyDown={(e) => handleKeyPress(e, task._id)}
                    onBlur={() => handleSaveEdit(task._id)}
                    autoFocus
                    className="flex-1 px-2 py-1 border border-primary bg-background text-text rounded focus:outline-none focus:ring-2 focus:ring-primary"
                  />
                ) : (
                  <span
                    className={`flex-1 text-sm break-words ${
                      task.completed 
                        ? 'line-through text-text/50' 
                        : 'text-text font-medium'
                    }`}
                  >
                    {task.task}
                  </span>
                )}
              </div>
              
              {editingId !== task._id && (
                <div className="flex items-center gap-1 flex-shrink-0">
                  <button
                    onClick={() => handleStartEdit(task)}
                    className="text-primary hover:bg-primary/20 p-2 rounded transition-colors"
                    title="Edit"
                  >
                    <FaEdit className="text-sm" />
                  </button>
                  <button
                    onClick={() => handleDelete(task._id)}
                    className="text-red-500 hover:bg-red-500/20 p-2 rounded transition-colors"
                    title="Delete"
                  >
                    <FaTrash className="text-sm" />
                  </button>
                  <button
                    onClick={() => handleToggleComplete(task._id)}
                    className={`p-2 rounded transition-colors ${
                      task.completed
                        ? 'text-green-500 hover:bg-green-500/20'
                        : 'text-text/50 hover:text-green-500 hover:bg-green-500/20'
                    }`}
                    title={task.completed ? 'Mark incomplete' : 'Mark complete'}
                  >
                    <FaCheck className="text-sm" />
                  </button>
                </div>
              )}
            </div>
          ))
        )}
      </div>
        </>
      )}
    </div>
  );
};

export default TaskList;

