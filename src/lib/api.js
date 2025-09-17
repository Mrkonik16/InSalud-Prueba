import axios from 'axios';

const API_BASE = import.meta.env.VITE_API_URL || 'http://localhost:8080';

const api = axios.create({
  baseURL: `${API_BASE}/api`,
  headers: { 'Content-Type': 'application/json' }
});

api.interceptors.request.use(config => {
  try {
    const token = localStorage.getItem('token');
    if (token) config.headers.Authorization = `Bearer ${token}`;
  } catch(e){ /* noop */ }
  return config;
}, err => Promise.reject(err));

api.interceptors.response.use(res => res, err => {
  if (err?.response?.status === 401) {
    try { localStorage.removeItem('token'); localStorage.removeItem('email'); } catch(e){}
    if (typeof window !== 'undefined') window.location.href = '/login';
  }
  return Promise.reject(err);
});

export default api;