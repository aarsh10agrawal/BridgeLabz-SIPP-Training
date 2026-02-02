import { useState } from 'react';
import { FaExclamationTriangle, FaTimes } from 'react-icons/fa';

const ConfirmDialog = ({ isOpen, title, message, confirmText = 'Confirm', cancelText = 'Cancel', onConfirm, onCancel, isDangerous = false }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-surface rounded-lg shadow-2xl p-6 max-w-sm w-11/12 border border-surface">
        <div className="flex items-center gap-3 mb-4">
          <FaExclamationTriangle className={`text-xl ${isDangerous ? 'text-red-500' : 'text-primary'}`} />
          <h3 className="text-lg font-bold text-text">{title}</h3>
        </div>
        
        <p className="text-text/80 mb-6">{message}</p>
        
        <div className="flex gap-3 justify-end">
          <button
            onClick={onCancel}
            className="px-4 py-2 bg-surface hover:bg-surface/80 text-text rounded font-semibold transition-colors border border-surface"
          >
            {cancelText}
          </button>
          <button
            onClick={onConfirm}
            className={`px-4 py-2 rounded font-semibold transition-colors text-white ${
              isDangerous
                ? 'bg-red-600 hover:bg-red-700 active:bg-red-800'
                : 'bg-primary hover:bg-primary/90 active:bg-primary/75'
            }`}
          >
            {confirmText}
          </button>
        </div>
      </div>
    </div>
  );
};

export default ConfirmDialog;
