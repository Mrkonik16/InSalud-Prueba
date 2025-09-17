import React, { useEffect, useRef, useState } from 'react';

export default function AtencionForm({ initial = {}, onSubmit }) {
  const [pacienteId, setPacienteId] = useState(initial.pacienteId ?? '');
  const [medicoId, setMedicoId] = useState(initial.medicoId ?? '');
  const [fecha, setFecha] = useState(initial.fecha ? initial.fecha.substring(0,16) : '');
  const [motivo, setMotivo] = useState(initial.motivo ?? '');
  const [estado, setEstado] = useState(initial.estado ?? 'PENDIENTE');

  const [loading, setLoading] = useState(false);
  const initialIdRef = useRef(initial.id ?? initial.pacienteId ?? null);

  useEffect(() => {
    const incomingId = initial.id ?? initial.pacienteId ?? null;
    if (incomingId !== initialIdRef.current) {
      initialIdRef.current = incomingId;
      setPacienteId(initial.pacienteId ?? '');
      setMedicoId(initial.medicoId ?? '');
      setFecha(initial.fecha ? initial.fecha.substring(0,16) : '');
      setMotivo(initial.motivo ?? '');
      setEstado(initial.estado ?? 'PENDIENTE');
    }
  }, [initial]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      if (!pacienteId) throw new Error('Ingrese ID de paciente');
      if (!medicoId) throw new Error('Ingrese ID de médico');
      if (!fecha) throw new Error('Seleccione fecha');

      const payload = {
        pacienteId: pacienteId ? Number(pacienteId) : null,
        medicoId: medicoId ? Number(medicoId) : null,
        fecha: fecha ? (fecha + ':00') : null,
        motivo: motivo?.trim(),
        estado: estado
      };

      await onSubmit(payload);
    } catch (err) {
      alert(err?.message ?? 'Error al enviar');
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="card max-w-lg w-full p-4">
      <label className="block mb-3">
        <div className="mb-1 text-sm font-medium text-[#012a4a]">ID Paciente</div>
        <input type="number" value={pacienteId} onChange={e=>setPacienteId(e.target.value)} className="w-full p-2 border rounded" required />
      </label>

      <label className="block mb-3">
        <div className="mb-1 text-sm font-medium text-[#012a4a]">ID Médico</div>
        <input type="number" value={medicoId} onChange={e=>setMedicoId(e.target.value)} className="w-full p-2 border rounded" required />
      </label>

      <label className="block mb-3">
        <div className="mb-1 text-sm font-medium text-[#012a4a]">Fecha y hora</div>
        <input type="datetime-local" value={fecha} onChange={e=>setFecha(e.target.value)} className="w-full p-2 border rounded" required />
      </label>

      <label className="block mb-3">
        <div className="mb-1 text-sm font-medium text-[#012a4a]">Motivo</div>
        <input value={motivo} onChange={e=>setMotivo(e.target.value)} className="w-full p-2 border rounded" placeholder="Motivo de la atención" required />
      </label>

      {/* --- Campo Estado --- */}
      <label className="block mb-3">
        <div className="mb-1 text-sm font-medium text-[#012a4a]">Estado</div>
        <select value={estado} onChange={e=>setEstado(e.target.value)} className="w-full p-2 border rounded" required>
          <option value="PENDIENTE">PENDIENTE</option>
          <option value="REALIZADA">REALIZADA</option>
          <option value="CANCELADA">CANCELADA</option>
        </select>
      </label>

      <div className="mt-4 flex gap-2">
        <button type="submit" disabled={loading} className="btn-primary px-4 py-2 rounded font-semibold">
          {loading ? 'Guardando...' : 'Guardar'}
        </button>
        <button type="button" onClick={() => {
          setPacienteId(''); setMedicoId(''); setFecha(''); setMotivo(''); setEstado('PENDIENTE');
        }} className="px-4 py-2 border rounded">
          Limpiar
        </button>
      </div>
    </form>
  );
}
