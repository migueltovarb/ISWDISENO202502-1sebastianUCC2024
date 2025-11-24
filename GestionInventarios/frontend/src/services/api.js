import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Productos
export const productosAPI = {
  getAll: () => api.get('/productos'),
  getById: (id) => api.get(`/productos/${id}`),
  getByCodigo: (codigo) => api.get(`/productos/codigo/${codigo}`),
  getByCategoria: (categoriaId) => api.get(`/productos/categoria/${categoriaId}`),
  getByProveedor: (proveedorId) => api.get(`/productos/proveedor/${proveedorId}`),
  getBajoStock: () => api.get('/productos/bajo-stock'),
  create: (data) => api.post('/productos', data),
  update: (id, data) => api.put(`/productos/${id}`, data),
  delete: (id) => api.delete(`/productos/${id}`),
};

// Categorías
export const categoriasAPI = {
  getAll: () => api.get('/categorias'),
  getActivas: () => api.get('/categorias/activas'),
  getById: (id) => api.get(`/categorias/${id}`),
  create: (data) => api.post('/categorias', data),
  update: (id, data) => api.put(`/categorias/${id}`, data),
  delete: (id) => api.delete(`/categorias/${id}`),
};

// Proveedores
export const proveedoresAPI = {
  getAll: () => api.get('/proveedores'),
  getActivos: () => api.get('/proveedores/activos'),
  getById: (id) => api.get(`/proveedores/${id}`),
  getByNit: (nit) => api.get(`/proveedores/nit/${nit}`),
  create: (data) => api.post('/proveedores', data),
  update: (id, data) => api.put(`/proveedores/${id}`, data),
  delete: (id) => api.delete(`/proveedores/${id}`),
};

// Usuarios
export const usuariosAPI = {
  getAll: () => api.get('/usuarios'),
  getById: (id) => api.get(`/usuarios/${id}`),
  getByEmail: (email) => api.get(`/usuarios/email/${email}`),
  getByRol: (rol) => api.get(`/usuarios/rol/${rol}`),
  create: (data) => api.post('/usuarios', data),
  update: (id, data) => api.put(`/usuarios/${id}`, data),
  delete: (id) => api.delete(`/usuarios/${id}`),
};

// Entradas
export const entradasAPI = {
  getAll: () => api.get('/entradas'),
  getById: (id) => api.get(`/entradas/${id}`),
  getByProducto: (codigoProducto) => api.get(`/entradas/producto/${codigoProducto}`),
  create: (data) => api.post('/entradas', data),
  delete: (id) => api.delete(`/entradas/${id}`),
};

// Salidas
export const salidasAPI = {
  getAll: () => api.get('/salidas'),
  getById: (id) => api.get(`/salidas/${id}`),
  getByProducto: (codigoProducto) => api.get(`/salidas/producto/${codigoProducto}`),
  create: (data) => api.post('/salidas', data),
  delete: (id) => api.delete(`/salidas/${id}`),
};

// Logs
export const logsAPI = {
  getAll: () => api.get('/logs'),
  getByUsuario: (usuarioId) => api.get(`/logs/usuario/${usuarioId}`),
  getByEntidad: (entidad) => api.get(`/logs/entidad/${entidad}`),
  getByFecha: (inicio, fin) => api.get(`/logs/fecha?inicio=${inicio}&fin=${fin}`),
};

export default api;
