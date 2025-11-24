import React, { useState, useEffect } from 'react';
import { FaPlus, FaEdit, FaTrash } from 'react-icons/fa';
import { categoriasAPI } from '../services/api';

const Categorias = () => {
  const [categorias, setCategorias] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [editing, setEditing] = useState(null);
  const [formData, setFormData] = useState({ nombre: '', descripcion: '', activa: true });

  useEffect(() => { loadData(); }, []);

  const loadData = async () => {
    const res = await categoriasAPI.getAll();
    setCategorias(res.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editing) {
        await categoriasAPI.update(editing.id, formData);
      } else {
        await categoriasAPI.create(formData);
      }
      loadData();
      setShowModal(false);
      setFormData({ nombre: '', descripcion: '', activa: true });
    } catch (error) {
      alert('Error al guardar');
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('¿Eliminar categoría?')) {
      await categoriasAPI.delete(id);
      loadData();
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Categorías</h1>
      </div>
      <div className="card">
        <button className="btn btn-primary" onClick={() => { setEditing(null); setShowModal(true); }}>
          <FaPlus /> Nueva Categoría
        </button>
        <table style={{ marginTop: '1.5rem' }}>
          <thead>
            <tr><th>Nombre</th><th>Descripción</th><th>Estado</th><th>Acciones</th></tr>
          </thead>
          <tbody>
            {categorias.map(cat => (
              <tr key={cat.id}>
                <td><strong>{cat.nombre}</strong></td>
                <td>{cat.descripcion}</td>
                <td><span className={cat.activa ? 'badge badge-success' : 'badge badge-danger'}>
                  {cat.activa ? 'Activa' : 'Inactiva'}
                </span></td>
                <td>
                  <button className="btn btn-secondary" onClick={() => { setEditing(cat); setFormData(cat); setShowModal(true); }} style={{ marginRight: '0.5rem', padding: '0.5rem' }}>
                    <FaEdit />
                  </button>
                  <button className="btn btn-danger" onClick={() => handleDelete(cat.id)} style={{ padding: '0.5rem' }}>
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
              <h2 className="modal-title">{editing ? 'Editar' : 'Nueva'} Categoría</h2>
              <button className="modal-close" onClick={() => setShowModal(false)}>&times;</button>
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label className="form-label">Nombre *</label>
                <input className="form-input" value={formData.nombre} onChange={(e) => setFormData({ ...formData, nombre: e.target.value })} required />
              </div>
              <div className="form-group">
                <label className="form-label">Descripción</label>
                <textarea className="form-textarea" value={formData.descripcion} onChange={(e) => setFormData({ ...formData, descripcion: e.target.value })} rows="3" />
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

export default Categorias;
