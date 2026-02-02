import { FaCheckCircle, FaExclamationCircle, FaTimesCircle, FaTimes } from 'react-icons/fa';

const AlertDialog = ({ isOpen, title, message, type = 'info', onClose }) => {
  if (!isOpen) return null;

  const getIcon = () => {
    switch (type) {
      case 'success':
        return <FaCheckCircle className="text-2xl text-green-500" />;
      case 'error':
        return <FaTimesCircle className="text-2xl text-red-500" />;
      case 'warning':
        return <FaExclamationCircle className="text-2xl text-yellow-500" />;
      default:
        return <FaExclamationCircle className="text-2xl text-primary" />;
    }
  };

  const getHeaderColor = () => {
    switch (type) {
      case 'success':
        return 'bg-green-500/10 border-green-500/30';
      case 'error':
        return 'bg-red-500/10 border-red-500/30';
      case 'warning':
        return 'bg-yellow-500/10 border-yellow-500/30';
      default:
        return 'bg-primary/10 border-primary/30';
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-surface rounded-lg shadow-2xl p-6 max-w-sm w-11/12 border border-surface">
        <div className={`flex items-center gap-3 mb-4 p-4 rounded-lg border ${getHeaderColor()}`}>
          {getIcon()}
          <h3 className="text-lg font-bold text-text">{title}</h3>
        </div>
        
        <p className="text-text/80 mb-6">{message}</p>
        
        <div className="flex justify-end">
          <button
            onClick={onClose}
            className="px-6 py-2 bg-primary hover:bg-primary/90 active:bg-primary/75 text-background rounded font-semibold transition-colors"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
};

export default AlertDialog;
