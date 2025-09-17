import React, { useEffect, useState } from 'react';
import { getMisAtenciones } from '../lib/atenciones';

export default function MisAtenciones(){
  const [items, setItems] = useState([]);
  useEffect(()=> {
    getMisAtenciones().then(r => setItems(r.data)).catch(console.error);
  }, []);
  return (
    <div className="p-6">
      <h1 className="text-2xl mb-4">Mis atenciones</h1>
      <ul>
        {items.map(a => <li key={a.id} className="card mb-3"><div><b>Fecha:</b> {a.fecha}</div><div><b>Motivo:</b> {a.motivo}</div></li>)}
      </ul>
    </div>
  );
}