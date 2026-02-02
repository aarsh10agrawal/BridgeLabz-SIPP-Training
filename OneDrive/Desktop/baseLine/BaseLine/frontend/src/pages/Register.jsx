import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { authAPI } from '../services/api';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import AlertDialog from '../components/AlertDialog';

const Register = () => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    confirmPassword: ''
  });
  const [alertDialog, setAlertDialog] = useState({ isOpen: false, title: '', message: '', type: 'error' });
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setAlertDialog({ isOpen: true, title: 'Validation Error', message: 'Passwords do not match', type: 'warning' });
      return;
    }

    if (formData.password.length < 6) {
      setAlertDialog({ isOpen: true, title: 'Validation Error', message: 'Password must be at least 6 characters', type: 'warning' });
      return;
    }

    setLoading(true);

    try {
      const response = await authAPI.register({
        username: formData.username,
        email: formData.email,
        password: formData.password
      });
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('user', JSON.stringify(response.data));
      navigate('/home');
    } catch (error) {
      const errorMsg = error.response?.data?.message || 'Registration failed';
      setAlertDialog({ isOpen: true, title: 'Registration Error', message: errorMsg, type: 'error' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-background bg-app-bg bg-cover bg-center flex flex-col">
      <Navbar />
      <AlertDialog
        isOpen={alertDialog.isOpen}
        title={alertDialog.title}
        message={alertDialog.message}
        type={alertDialog.type}
        onClose={() => setAlertDialog({ ...alertDialog, isOpen: false })}
      />
      <div className="flex-1 flex items-center justify-center p-4">
        <div className="bg-surface rounded-lg shadow-xl p-8 w-full max-w-md border border-surface">
          <h1 className="text-3xl font-bold text-center text-text mb-6">Register</h1>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-text mb-1">
                Username
              </label>
              <input
                type="text"
                name="username"
                value={formData.username}
                onChange={handleChange}
                required
                minLength={3}
                className="w-full px-4 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-text mb-1">
                Email
              </label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-text mb-1">
                Password
              </label>
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
                minLength={6}
                className="w-full px-4 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-text mb-1">
                Confirm Password
              </label>
              <input
                type="password"
                name="confirmPassword"
                value={formData.confirmPassword}
                onChange={handleChange}
                required
                minLength={6}
                className="w-full px-4 py-2 border border-surface bg-background text-text rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
              />
            </div>
            <button
              type="submit"
              disabled={loading}
              className="w-full bg-primary hover:opacity-90 text-background font-semibold py-2 px-4 rounded-lg transition disabled:opacity-50"
            >
              {loading ? 'Registering...' : 'Register'}
            </button>
          </form>
          <p className="mt-4 text-center text-text/70">
            Already have an account?{' '}
            <Link to="/login" className="text-primary hover:opacity-80 font-semibold">
              Login
            </Link>
          </p>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Register;

