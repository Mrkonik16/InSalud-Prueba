import React from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route, useLocation } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import Login from './pages/Login';
import AdminAtenciones from './pages/AdminAtenciones';
import MisAtenciones from './pages/PacienteMisAtenciones';
import ProtectedRoute from './components/ProtectedRoute';
import Navbar from './components/Navbar';
import './index.css';

function AppRoutes() {
  const location = useLocation();
  const hideNavbarByRoute = location.pathname === '/login';

  const hideNavbar = hideNavbarByRoute;

  return (
    <>
      {!hideNavbar && <Navbar />}

      <main>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/" element={<div className="p-6"><h2>InSalud Frontend</h2></div>} />
          <Route path="/admin/atenciones" element={<ProtectedRoute><AdminAtenciones/></ProtectedRoute>} />
          <Route path="/paciente/mis-atenciones" element={<ProtectedRoute><MisAtenciones/></ProtectedRoute>} />
        </Routes>
      </main>
    </>
  );
}

function Main() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <AppRoutes />
      </BrowserRouter>
    </AuthProvider>
  );
}

createRoot(document.getElementById('root')).render(<Main />);
