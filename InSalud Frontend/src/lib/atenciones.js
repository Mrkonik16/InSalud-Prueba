import api from './api';

export const getAllAtenciones = () => api.get('/atenciones');
export const getMisAtenciones = () => api.get('/atenciones/mias');
export const createAtencion = (payload) => api.post('/atenciones', payload);
export const updateAtencion = (id, payload) => api.put(`/atenciones/${id}`, payload);
export const deleteAtencion = (id) => api.delete(`/atenciones/${id}`);