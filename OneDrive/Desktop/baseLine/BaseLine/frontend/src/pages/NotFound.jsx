import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import { FaHome, FaFolderOpen } from 'react-icons/fa';
const NotFound = () => {
  const navigate = useNavigate();
  return (
    <div className="flex flex-col min-h-screen bg-background bg-app-bg bg-cover bg-center">
      <Navbar />
      <div className="flex-1 flex items-center justify-center p-4">
        <div className="text-center">
          <FaFolderOpen className="text-9xl text-primary/30 mb-6 mx-auto" />
          <h1 className="text-6xl font-bold text-text mb-4">404</h1>
          <h2 className="text-3xl font-semibold text-text/80 mb-4">Page Not Found</h2>
          <p className="text-text/60 text-lg mb-8 max-w-md mx-auto">
            Oops! The page you're looking for doesn't exist. Let's get you back on track.
          </p>
          <button
            onClick={() => navigate('/home')}
            className="inline-flex items-center gap-2 bg-primary hover:bg-primary/90 active:bg-primary/75 text-background px-8 py-3 rounded-lg font-semibold transition-colors"
          >
            <FaHome className="text-lg" />
            Back to Home
          </button>
        </div>
      </div>
      <Footer />
    </div>
  );
};
export default NotFound;