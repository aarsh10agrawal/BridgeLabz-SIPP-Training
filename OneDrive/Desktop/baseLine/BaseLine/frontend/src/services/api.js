import axios from 'axios';

const rawApiUrl = import.meta.env.VITE_API_URL;
const API_URL = rawApiUrl ? rawApiUrl.replace(/\/+$/, '') + '/api' : '';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json'
  }
});

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);
export const authAPI = {
  register: (data) => api.post('/auth/register', data),
  login: (data) => api.post('/auth/login', data),
  getMe: () => api.get('/auth/me')
};

export const journalAPI = {
  getJournal: (date) => api.get('/journal', { params: { date } }),
  createOrUpdateJournal: (data) => api.post('/journal', data),
  updateJournal: (data) => api.put('/journal', data)
};
export const expenseAPI = {
  getExpenses: (params) => api.get('/expenses', { params }),
  createExpense: (data) => api.post('/expenses', data),
  updateExpense: (id, data) => api.put(`/expenses/${id}`, data),
  deleteExpense: (id) => api.delete(`/expenses/${id}`),
  getExpenseStats: (params) => api.get('/expenses/stats', { params })
};
export const taskAPI = {
  getTasks: (date) => api.get(`/tasks/${date}`),
  createTask: (data) => api.post('/tasks', data),
  updateTask: (id, data) => api.put(`/tasks/${id}`, data),
  toggleTask: (id) => api.patch(`/tasks/${id}/toggle`),
  deleteTask: (id) => api.delete(`/tasks/${id}`)
};
export default api;