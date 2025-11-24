import React, { useState, useEffect } from 'react';
import { FaPlus, FaEdit, FaTrash } from 'react-icons/fa';
import { usuariosAPI } from '../services/api';

const Usuarios = () => {
  const [usuarios, setUsuarios] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [editing, setEditing] = useState(null);
  const [formData, setFormData] = useState({ nombre: '', email: '', rol: 'AUXILIAR_BODEGA' });

  useEffect(() => { loadData(); }, []);

  const loadData = async () => {
    const res = await usuariosAPI.getAll();
    setUsuarios(res.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editing) {
        await usuariosAPI.update(editing.id, formData);
      } else {
        await usuariosAPI.create(formData);
      }
      loadData();
      setShowModal(false);
      setFormData({ nombre: '', email: '', rol: 'AUXILIAR_BODEGA' });
    } catch (error) {
      alert('Error al guardar');
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('¿Eliminar usuario?')) {
      await usuariosAPI.delete(id);
      loadData();
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Usuarios</h1>
      </div>
      <div className="card">
        <button className="btn btn-primary" onClick={() => { setEditing(null); setShowModal(true); }}>
          <FaPlus /> Nuevo Usuario
        </button>
        <table style={{ marginTop: '1.5rem' }}>
          <thead>
            <tr><th>Nombre</th><th>Email</th><th>Rol</th><th>Acciones</th></tr>
          </thead>
          <tbody>
            {usuarios.map(user => (
              <tr key={user.id}>
                <td><strong>{user.nombre}</strong></td>
                <td>{user.email}</td>
                <td><span className={user.rol === 'ADMINISTRADOR' ? 'badge badge-danger' : 'badge badge-info'}>
                  {user.rol}
                </span></td>
                <td>
                  <button className="btn btn-secondary" onClick={() => { setEditing(user); setFormData(user); setShowModal(true); }} style={{ marginRight: '0.5rem', padding: '0.5rem' }}>
                    <FaEdit />
                  </button>
                  <button className="btn btn-danger" onClick={() => handleDelete(user.id)} style={{ padding: '0.5rem' }}>
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
              <h2 className="modal-title">{editing ? 'Editar' : 'Nuevo'} Usuario</h2>
              <button className="modal-close" onClick={() => setShowModal(false)}>&times;</button>
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label className="form-label">Nombre *</label>
                <input className="form-input" value={formData.nombre} onChange={(e) => setFormData({ ...formData, nombre: e.target.value })} required />
              </div>
              <div className="form-group">
                <label className="form-label">Email *</label>
                <input type="email" className="form-input" value={formData.email} onChange={(e) => setFormData({ ...formData, email: e.target.value })} required />
              </div>
              <div className="form-group">
                <label className="form-label">Rol *</label>
                <select className="form-select" value={formData.rol} onChange={(e) => setFormData({ ...formData, rol: e.target.value })} required>
                  <option value="AUXILIAR_BODEGA">Auxiliar de Bodega</option>
                  <option value="ADMINISTRADOR">Administrador</option>
                </select>
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

export default Usuarios;
