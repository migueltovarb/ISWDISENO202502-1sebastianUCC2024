import React, { useState, useEffect } from 'react';
import { FaPlus } from 'react-icons/fa';
import { salidasAPI, productosAPI, usuariosAPI } from '../services/api';

const Salidas = () => {
  const [salidas, setSalidas] = useState([]);
  const [productos, setProductos] = useState([]);
  const [usuarios, setUsuarios] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [formData, setFormData] = useState({ codigoProducto: '', cantidad: '', usuarioId: '', observaciones: '' });

  useEffect(() => { loadData(); }, []);

  const loadData = async () => {
    const [salRes, prodRes, userRes] = await Promise.all([
      salidasAPI.getAll(),
      productosAPI.getAll(),
      usuariosAPI.getAll()
    ]);
    setSalidas(salRes.data);
    setProductos(prodRes.data);
    setUsuarios(userRes.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await salidasAPI.create(formData);
      loadData();
      setShowModal(false);
      setFormData({ codigoProducto: '', cantidad: '', usuarioId: '', observaciones: '' });
    } catch (error) {
      alert(error.response?.data || 'Error al registrar salida');
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Salidas de Productos</h1>
      </div>
      <div className="card">
        <button className="btn btn-primary" onClick={() => setShowModal(true)}>
          <FaPlus /> Registrar Salida
        </button>
        <table style={{ marginTop: '1.5rem' }}>
          <thead>
            <tr><th>Fecha</th><th>Producto</th><th>Cantidad</th><th>Usuario</th><th>Observaciones</th></tr>
          </thead>
          <tbody>
            {salidas.map(salida => (
              <tr key={salida.id}>
                <td>{new Date(salida.fecha).toLocaleString()}</td>
                <td>{salida.codigoProducto}</td>
                <td><span className="badge badge-danger">-{salida.cantidad}</span></td>
                <td>{usuarios.find(u => u.id === salida.usuarioId)?.nombre || '-'}</td>
                <td>{salida.observaciones}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h2 className="modal-title">Registrar Salida</h2>
              <button className="modal-close" onClick={() => setShowModal(false)}>&times;</button>
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label className="form-label">Producto *</label>
                <select className="form-select" value={formData.codigoProducto} onChange={(e) => setFormData({ ...formData, codigoProducto: e.target.value })} required>
                  <option value="">Seleccionar...</option>
                  {productos.map(p => <option key={p.id} value={p.codigo}>{p.codigo} - {p.nombre} (Stock: {p.cantidad})</option>)}
                </select>
              </div>
              <div className="form-group">
                <label className="form-label">Cantidad *</label>
                <input type="number" className="form-input" value={formData.cantidad} onChange={(e) => setFormData({ ...formData, cantidad: e.target.value })} required />
              </div>
              <div className="form-group">
                <label className="form-label">Usuario *</label>
                <select className="form-select" value={formData.usuarioId} onChange={(e) => setFormData({ ...formData, usuarioId: e.target.value })} required>
                  <option value="">Seleccionar...</option>
                  {usuarios.map(u => <option key={u.id} value={u.id}>{u.nombre}</option>)}
                </select>
              </div>
              <div className="form-group">
                <label className="form-label">Observaciones</label>
                <textarea className="form-textarea" value={formData.observaciones} onChange={(e) => setFormData({ ...formData, observaciones: e.target.value })} rows="3" />
              </div>
              <div style={{ display: 'flex', gap: '1rem', justifyContent: 'flex-end' }}>
                <button type="button" className="btn btn-secondary" onClick={() => setShowModal(false)}>Cancelar</button>
                <button type="submit" className="btn btn-primary">Registrar</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Salidas;
