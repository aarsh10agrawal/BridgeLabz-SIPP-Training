import { useNavigate } from 'react-router-dom';
import { RiDatabaseLine } from 'react-icons/ri';

const Navbar = () => {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user') || '{}');
  const token = localStorage.getItem('token');
  const isLoggedIn = !!token && user.username;

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    navigate('/login');
  };

  const handleLogin = () => {
    navigate('/login');
  };

  return (
    <nav className="bg-surface text-text shadow-lg border-b border-surface sticky top-0 z-50">
      <div className="container mx-auto px-4 py-4 flex justify-between items-center max-w-[80%] mx-auto">
        <div className="flex items-center gap-3">
          <RiDatabaseLine className="text-2xl text-primary" />
          <h1 className="text-2xl font-bold text-text tracking-tight">BaseLine</h1>
        </div>
        <div className="flex items-center gap-4">
          {isLoggedIn ? (
            <>
              <span className="text-sm text-text/80">Welcome, <span className="font-semibold">{user.username || 'User'}</span></span>
              <button
                onClick={handleLogout}
                className="bg-primary hover:bg-primary/90 active:bg-primary/75 text-background px-4 py-2 rounded font-semibold transition-colors"
              >
                Logout
              </button>
            </>
          ) : (
            <button
              onClick={handleLogin}
              className="bg-primary hover:bg-primary/90 active:bg-primary/75 text-background px-4 py-2 rounded font-semibold transition-colors"
            >
              Login
            </button>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;

