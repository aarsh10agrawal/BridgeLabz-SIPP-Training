import { useState } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import Calendar from '../components/Calendar';
import TaskList from '../components/TaskList';
import Journal from '../components/Journal';
import BorrowLendSummary from '../components/BorrowLendSummary';
import ExpenseForm from '../components/ExpenseForm';
import ExpenseList from '../components/ExpenseList';

const Home = () => {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [editingExpense, setEditingExpense] = useState(null);
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  const handleDateChange = (date) => {
    setSelectedDate(date);
    setEditingExpense(null);
  };

  const handleExpenseAdded = () => {
    setRefreshTrigger(prev => prev + 1);
    setEditingExpense(null);
  };

  const handleExpenseDeleted = () => {
    setRefreshTrigger(prev => prev + 1);
  };

  const handleEditExpense = (expense) => {
    setEditingExpense(expense);
  };

  const handleCancelEdit = () => {
    setEditingExpense(null);
  };

  return (
    <div className="flex flex-col min-h-screen bg-background bg-app-bg bg-cover bg-center">
      <Navbar />
      <div className="container mx-auto px-4 py-6 flex-1 overflow-y-auto max-w-[80%] w-full">
        <div className="mb-6 grid grid-cols-1 lg:grid-cols-2 gap-6">
          <Calendar selectedDate={selectedDate} onDateChange={handleDateChange} />
          <TaskList selectedDate={selectedDate} />
        </div>
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 pb-8">
          <div className="space-y-6 flex flex-col">
            <div className="flex-1">
              <Journal selectedDate={selectedDate} />
            </div>
            <div className="flex-1 min-h-0">
              <BorrowLendSummary selectedDate={selectedDate} refreshTrigger={refreshTrigger} />
            </div>
          </div>
          <div className="space-y-6 flex flex-col">
            <div className="flex-shrink-0">
              <ExpenseForm
                selectedDate={selectedDate}
                onExpenseAdded={handleExpenseAdded}
                editingExpense={editingExpense}
                onCancelEdit={handleCancelEdit}
              />
            </div>
            <div className="flex-1 min-h-0">
              <ExpenseList
                selectedDate={selectedDate}
                onEditExpense={handleEditExpense}
                onExpenseDeleted={handleExpenseDeleted}
                refreshTrigger={refreshTrigger}
              />
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Home;

