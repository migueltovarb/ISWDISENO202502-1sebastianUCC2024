import React, { useState, useEffect } from 'react';
import { FaPlus, FaEdit, FaTrash } from 'react-icons/fa';
import { proveedoresAPI } from '../services/api';

const Proveedores = () => {
  const [proveedores, setProveedores] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [editing, setEditing] = useState(null);
  const [formData, setFormData] = useState({ nombre: '', nit: '', telefono: '', direccion: '', email: '', contacto: '', activo: true });

  useEffect(() => { loadData(); }, []);

  const loadData = async () => {
    const res = await proveedoresAPI.getAll();
    setProveedores(res.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editing) {
        await proveedoresAPI.update(editing.id, formData);
      } else {
        await proveedoresAPI.create(formData);
      }
      loadData();
      setShowModal(false);
      setFormData({ nombre: '', nit: '', telefono: '', direccion: '', email: '', contacto: '', activo: true });
    } catch (error) {
      alert('Error al guardar');
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('¿Eliminar proveedor?')) {
      await proveedoresAPI.delete(id);
      loadData();
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Proveedores</h1>
      </div>
      <div className="card">
        <button className="btn btn-primary" onClick={() => { setEditing(null); setShowModal(true); }}>
          <FaPlus /> Nuevo Proveedor
        </button>
        <table style={{ marginTop: '1.5rem' }}>
          <thead>
            <tr><th>Nombre</th><th>NIT</th><th>Teléfono</th><th>Email</th><th>Estado</th><th>Acciones</th></tr>
          </thead>
          <tbody>
            {proveedores.map(prov => (
              <tr key={prov.id}>
                <td><strong>{prov.nombre}</strong></td>
                <td>{prov.nit}</td>
                <td>{prov.telefono}</td>
                <td>{prov.email}</td>
                <td><span className={prov.activo ? 'badge badge-success' : 'badge badge-danger'}>
                  {prov.activo ? 'Activo' : 'Inactivo'}
                </span></td>
                <td>
                  <button className="btn btn-secondary" onClick={() => { setEditing(prov); setFormData(prov); setShowModal(true); }} style={{ marginRight: '0.5rem', padding: '0.5rem' }}>
                    <FaEdit />
                  </button>
                  <button className="btn btn-danger" onClick={() => handleDelete(prov.id)} style={{ padding: '0.5rem' }}>
                    <FaTrash />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h2 className="modal-title">{editing ? 'Editar' : 'Nuevo'} Proveedor</h2>
              <button className="modal-close" onClick={() => setShowModal(false)}>&times;</button>
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label className="form-label">Nombre *</label>
                <input className="form-input" value={formData.nombre} onChange={(e) => setFormData({ ...formData, nombre: e.target.value })} required />
              </div>
              <div className="form-group">
                <label className="form-label">NIT *</label>
                <input className="form-input" value={formData.nit} onChange={(e) => setFormData({ ...formData, nit: e.target.value })} required />
              </div>
              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
                <div className="form-group">
                  <label className="form-label">Teléfono</label>
                  <input className="form-input" value={formData.telefono} onChange={(e) => setFormData({ ...formData, telefono: e.target.value })} />
                </div>
                <div className="form-group">
                  <label className="form-label">Email</label>
                  <input type="email" className="form-input" value={formData.email} onChange={(e) => setFormData({ ...formData, email: e.target.value })} />
                </div>
              </div>
              <div className="form-group">
                <label className="form-label">Dirección</label>
                <input className="form-input" value={formData.direccion} onChange={(e) => setFormData({ ...formData, direccion: e.target.value })} />
              </div>
              <div className="form-group">
                <label className="form-label">Contacto</label>
                <input className="form-input" value={formData.contacto} onChange={(e) => setFormData({ ...formData, contacto: e.target.value })} />
              </div>
              <div style={{ display: 'flex', gap: '1rem', justifyContent: 'flex-end' }}>
                <button type="button" className="btn btn-secondary" onClick={() => setShowModal(false)}>Cancelar</button>
                <button type="submit" className="btn btn-primary">{editing ? 'Actualizar' : 'Crear'}</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Proveedores;
