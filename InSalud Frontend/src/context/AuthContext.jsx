import React, { createContext, useContext, useEffect, useState } from 'react';
import api from '../lib/api';

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const email = localStorage.getItem('email');
    const userId = localStorage.getItem('userId');
    if (token && email) setUser({ email, userId });
  }, []);

  const login = async (email, password) => {
    const res = await api.post('/auth/login', { email, password });
    const { token, userId, email: respEmail } = res.data;
    localStorage.setItem('token', token);
    localStorage.setItem('email', respEmail || email);
    if (userId) localStorage.setItem('userId', String(userId));
    setUser({ email: respEmail || email, userId });
    return res.data;
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    localStorage.removeItem('userId');
    setUser(null);
    window.location.href = '/login';
  };

  return <AuthContext.Provider value={{ user, login, logout }}>{children}</AuthContext.Provider>;
}

export function useAuth(){ return useContext(AuthContext); }
