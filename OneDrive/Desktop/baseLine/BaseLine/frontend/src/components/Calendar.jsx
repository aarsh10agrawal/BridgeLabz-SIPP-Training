import { useState, useEffect } from 'react';
import { FaChevronUp, FaChevronDown } from 'react-icons/fa';

const Calendar = ({ selectedDate, onDateChange }) => {
  const [currentMonth, setCurrentMonth] = useState(new Date());
  const [calendarDays, setCalendarDays] = useState([]);
  const [isVisible, setIsVisible] = useState(true);

  useEffect(() => {
    generateCalendar();
  }, [currentMonth]);

  const generateCalendar = () => {
    const year = currentMonth.getFullYear();
    const month = currentMonth.getMonth();
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const daysInMonth = lastDay.getDate();
    const startingDayOfWeek = firstDay.getDay();

    const days = [];
    
    for (let i = 0; i < startingDayOfWeek; i++) {
      days.push(null);
    }

    for (let day = 1; day <= daysInMonth; day++) {
      days.push(new Date(year, month, day));
    }

    setCalendarDays(days);
  };

  const isSameDay = (date1, date2) => {
    if (!date1 || !date2) return false;
    return (
      date1.getDate() === date2.getDate() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getFullYear() === date2.getFullYear()
    );
  };

  const isToday = (date) => {
    if (!date) return false;
    const today = new Date();
    return isSameDay(date, today);
  };

  const handleDateClick = (date) => {
    if (date) {
      onDateChange(date);
    }
  };

  const goToPreviousMonth = () => {
    setCurrentMonth(new Date(currentMonth.getFullYear(), currentMonth.getMonth() - 1, 1));
  };

  const goToNextMonth = () => {
    setCurrentMonth(new Date(currentMonth.getFullYear(), currentMonth.getMonth() + 1, 1));
  };

  const goToToday = () => {
    const today = new Date();
    setCurrentMonth(today);
    onDateChange(today);
  };

  const monthNames = [
    'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'
  ];

  const dayNames = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

  return (
    <div className="bg-surface rounded-lg shadow-md p-6 border border-surface hover:shadow-lg transition-shadow">
      <div className="flex justify-between items-center mb-6">
        <div className="flex items-center gap-2 flex-1">
          <h2 className="text-xl font-bold text-text">
            {monthNames[currentMonth.getMonth()]} {currentMonth.getFullYear()}
          </h2>
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
          <div className="flex justify-between items-center mb-4 gap-2">
            <button
              onClick={goToPreviousMonth}
              className="px-3 py-2 bg-surface hover:bg-primary/20 text-text rounded font-semibold transition-colors"
              title="Previous month"
            >
              ←
            </button>
            <span className="text-sm font-medium text-text/70 px-2">Navigate</span>
            <button
              onClick={goToNextMonth}
              className="px-3 py-2 bg-surface hover:bg-primary/20 text-text rounded font-semibold transition-colors"
              title="Next month"
            >
              →
            </button>
          </div>
          <div className="grid grid-cols-7 gap-2 mb-4">
            {dayNames.map((day) => (
              <div key={day} className="text-center text-xs font-bold text-text/70 py-2 uppercase tracking-widest">
                {day}
              </div>
            ))}
          </div>
          <div className="grid grid-cols-7 gap-2 mb-4">
            {calendarDays.map((date, index) => (
              <button
                key={index}
                onClick={() => handleDateClick(date)}
                disabled={!date}
                className={`
                  py-2 rounded font-semibold transition text-sm
                  ${!date ? 'cursor-default opacity-0' : 'hover:scale-105 hover:bg-primary/20 cursor-pointer text-text'}
                  ${date && isSameDay(date, selectedDate) ? 'bg-primary text-background shadow-lg' : ''}
                  ${date && !isSameDay(date, selectedDate) && isToday(date) ? 'bg-primary/30 text-primary ring-2 ring-primary' : ''}
                  ${date && !isSameDay(date, selectedDate) && !isToday(date) ? 'bg-background text-text border border-surface/50' : ''}
                `}
              >
                {date ? date.getDate() : ''}
              </button>
            ))}
          </div>
          <button
            onClick={goToToday}
            className="w-full bg-primary hover:bg-primary/90 active:bg-primary/75 text-background py-2 rounded font-semibold transition-colors"
          >
            Today
          </button>
        </>
      )}
    </div>
  );
};

export default Calendar;

