import React, { useState, useEffect } from 'react';
import { FaPlus } from 'react-icons/fa';
import { entradasAPI, productosAPI, usuariosAPI } from '../services/api';

const Entradas = () => {
  const [entradas, setEntradas] = useState([]);
  const [productos, setProductos] = useState([]);
  const [usuarios, setUsuarios] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [formData, setFormData] = useState({ codigoProducto: '', cantidad: '', usuarioId: '', observaciones: '' });

  useEffect(() => { loadData(); }, []);

  const loadData = async () => {
    const [entRes, prodRes, userRes] = await Promise.all([
      entradasAPI.getAll(),
      productosAPI.getAll(),
      usuariosAPI.getAll()
    ]);
    setEntradas(entRes.data);
    setProductos(prodRes.data);
    setUsuarios(userRes.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await entradasAPI.create(formData);
      loadData();
      setShowModal(false);
      setFormData({ codigoProducto: '', cantidad: '', usuarioId: '', observaciones: '' });
    } catch (error) {
      alert(error.response?.data || 'Error al registrar entrada');
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Entradas de Productos</h1>
      </div>
      <div className="card">
        <button className="btn btn-success" onClick={() => setShowModal(true)}>
          <FaPlus /> Registrar Entrada
        </button>
        <table style={{ marginTop: '1.5rem' }}>
          <thead>
            <tr><th>Fecha</th><th>Producto</th><th>Cantidad</th><th>Usuario</th><th>Observaciones</th></tr>
          </thead>
          <tbody>
            {entradas.map(entrada => (
              <tr key={entrada.id}>
                <td>{new Date(entrada.fecha).toLocaleString()}</td>
                <td>{entrada.codigoProducto}</td>
                <td><span className="badge badge-success">+{entrada.cantidad}</span></td>
                <td>{usuarios.find(u => u.id === entrada.usuarioId)?.nombre || '-'}</td>
                <td>{entrada.observaciones}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h2 className="modal-title">Registrar Entrada</h2>
              <button className="modal-close" onClick={() => setShowModal(false)}>&times;</button>
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label className="form-label">Producto *</label>
                <select className="form-select" value={formData.codigoProducto} onChange={(e) => setFormData({ ...formData, codigoProducto: e.target.value })} required>
                  <option value="">Seleccionar...</option>
                  {productos.map(p => <option key={p.id} value={p.codigo}>{p.codigo} - {p.nombre}</option>)}
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
                <button type="submit" className="btn btn-success">Registrar</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Entradas;
