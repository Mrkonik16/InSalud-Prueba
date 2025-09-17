import React from 'react';
import { Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <nav className="" style={{ background: '#40E0D0', color: '#012a4a' }}>
      <div className="max-w-6xl mx-auto flex items-center justify-between">
        <div className="font-bold text-lg">InSalud</div>
        <div className="space-x-4">
          <Link to="/admin/atenciones" className="font-medium hover:underline">Atenciones</Link>
          <Link to="/paciente/mis-atenciones" className="font-medium hover:underline">Mis Atenciones</Link>
        </div>
      </div>
    </nav>
  );
}
