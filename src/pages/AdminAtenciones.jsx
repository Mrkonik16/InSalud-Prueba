import React, { useEffect, useState } from 'react';
import { getAllAtenciones, createAtencion, updateAtencion, deleteAtencion } from '../lib/atenciones';
import AtencionForm from '../components/AtencionForm';

export default function AdminAtenciones(){
  const [items, setItems] = useState([]);
  const [editing, setEditing] = useState(null);

  const fetchAll = async () => {
    const res = await getAllAtenciones();
    setItems(res.data);
  };

  useEffect(()=>{ fetchAll(); }, []);

  const handleCreate = async (payload) => {
    await createAtencion(payload);
    fetchAll();
  };

  const handleUpdate = async (payload) => {
    await updateAtencion(editing.id, payload);
    setEditing(null);
    fetchAll();
  };

  const handleDelete = async (id) => {
    if (!confirm('Eliminar?')) return;
    await deleteAtencion(id);
    fetchAll();
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl mb-4">Panel Admin - Atenciones</h1>
      <section className="mb-6">
        <h2>Crear</h2>
        <AtencionForm onSubmit={handleCreate} pacientes={[]} medicos={[]} />
      </section>

      <section>
        <h2>Listado</h2>
        <table className="w-full">
          <thead className="bg-turquesa text-azulin"><tr><th>ID</th><th>Fecha</th><th>Motivo</th><th>Paciente</th><th>Médico</th><th>Acciones</th></tr></thead>
          <tbody>
            {items.map(it => (
              <tr key={it.id}><td>{it.id}</td><td>{it.fecha}</td><td>{it.motivo}</td><td>{it.pacienteNombre}</td><td>{it.medicoNombre}</td>
                <td>
                  <button onClick={()=>setEditing(it)}>Editar</button>
                  <button onClick={()=>handleDelete(it.id)}>Eliminar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>

      {editing && <div className="fixed inset-0 bg-black/40 flex items-center justify-center">
        <div className="card w-full max-w-lg">
          <h3>Editar #{editing.id}</h3>
          <AtencionForm initial={editing} onSubmit={handleUpdate} pacientes={[]} medicos={[]} />
          <button onClick={()=>setEditing(null)}>Cerrar</button>
        </div>
      </div>}
    </div>
  );
}
