import React, { useState, useEffect } from 'react';
import { FaPlus, FaEdit, FaTrash } from 'react-icons/fa';
import { productosAPI, categoriasAPI, proveedoresAPI } from '../services/api';
import SearchBar from '../components/SearchBar';

const Productos = () => {
  const [productos, setProductos] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [proveedores, setProveedores] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [editingProducto, setEditingProducto] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [formData, setFormData] = useState({
    codigo: '',
    nombre: '',
    descripcion: '',
    precio: '',
    cantidad: '',
    categoriaId: '',
    proveedorId: '',
    stockMinimo: 5,
    stockMaximo: 100,
    activo: true,
  });

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      const [prodRes, catRes, provRes] = await Promise.all([
        productosAPI.getAll(),
        categoriasAPI.getAll(),
        proveedoresAPI.getAll(),
      ]);
      setProductos(prodRes.data);
      setCategorias(catRes.data);
      setProveedores(provRes.data);
      setLoading(false);
    } catch (error) {
      console.error('Error cargando datos:', error);
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingProducto) {
        await productosAPI.update(editingProducto.id, formData);
      } else {
        await productosAPI.create(formData);
      }
      loadData();
      closeModal();
    } catch (error) {
      console.error('Error guardando producto:', error);
      alert(error.response?.data || 'Error al guardar el producto');
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('¿Estás seguro de eliminar este producto?')) {
      try {
        await productosAPI.delete(id);
        loadData();
      } catch (error) {
        console.error('Error eliminando producto:', error);
      }
    }
  };

  const openModal = (producto = null) => {
    if (producto) {
      setEditingProducto(producto);
      setFormData(producto);
    } else {
      setEditingProducto(null);
      setFormData({
        codigo: '',
        nombre: '',
        descripcion: '',
        precio: '',
        cantidad: '',
        categoriaId: '',
        proveedorId: '',
        stockMinimo: 5,
        stockMaximo: 100,
        activo: true,
      });
    }
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setEditingProducto(null);
  };

  const filteredProductos = productos.filter(p =>
    p.nombre.toLowerCase().includes(searchTerm.toLowerCase()) ||
    p.codigo.toLowerCase().includes(searchTerm.toLowerCase())
  );

  if (loading) return <div className="loading">Cargando productos...</div>;

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Productos</h1>
        <p className="page-subtitle">Gestión de productos del inventario</p>
      </div>

      <div className="card">
        <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '2rem', gap: '1rem', flexWrap: 'wrap', alignItems: 'center' }}>
          <SearchBar
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            placeholder="Buscar por nombre o código..."
          />
          <button className="btn btn-primary" onClick={() => openModal()}>
            <FaPlus /> Nuevo Producto
          </button>
        </div>

        <div className="table-container">
          <table>
            <thead>
              <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Stock</th>
                <th>Categoría</th>
                <th>Estado</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {filteredProductos.map((producto) => (
                <tr key={producto.id}>
                  <td><strong>{producto.codigo}</strong></td>
                  <td>{producto.nombre}</td>
                  <td>${producto.precio.toFixed(2)}</td>
                  <td>
                    <span className={producto.cantidad <= producto.stockMinimo ? 'badge badge-warning' : 'badge badge-success'}>
                      {producto.cantidad}
                    </span>
                  </td>
                  <td>{categorias.find(c => c.id === producto.categoriaId)?.nombre || '-'}</td>
                  <td>
                    <span className={producto.activo ? 'badge badge-success' : 'badge badge-danger'}>
                      {producto.activo ? 'Activo' : 'Inactivo'}
                    </span>
                  </td>
                  <td>
                    <button className="btn btn-secondary" onClick={() => openModal(producto)} style={{ marginRight: '0.5rem', padding: '0.5rem' }}>
                      <FaEdit />
                    </button>
                    <button className="btn btn-danger" onClick={() => handleDelete(producto.id)} style={{ padding: '0.5rem' }}>
                      <FaTrash />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {showModal && (
        <div className="modal-overlay" onClick={closeModal}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h2 className="modal-title">{editingProducto ? 'Editar Producto' : 'Nuevo Producto'}</h2>
              <button className="modal-close" onClick={closeModal}>&times;</button>
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label className="form-label">Código *</label>
                <input
                  type="text"
                  className="form-input"
                  value={formData.codigo}
                  onChange={(e) => setFormData({ ...formData, codigo: e.target.value })}
                  required
                  disabled={editingProducto}
                />
              </div>
              <div className="form-group">
                <label className="form-label">Nombre *</label>
                <input
                  type="text"
                  className="form-input"
                  value={formData.nombre}
                  onChange={(e) => setFormData({ ...formData, nombre: e.target.value })}
                  required
                />
              </div>
              <div className="form-group">
                <label className="form-label">Descripción</label>
                <textarea
                  className="form-textarea"
                  value={formData.descripcion}
                  onChange={(e) => setFormData({ ...formData, descripcion: e.target.value })}
                  rows="3"
                />
              </div>
              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
                <div className="form-group">
                  <label className="form-label">Precio *</label>
                  <input
                    type="number"
                    step="0.01"
                    className="form-input"
                    value={formData.precio}
                    onChange={(e) => setFormData({ ...formData, precio: e.target.value })}
                    required
                  />
                </div>
                <div className="form-group">
                  <label className="form-label">Cantidad *</label>
                  <input
                    type="number"
                    className="form-input"
                    value={formData.cantidad}
                    onChange={(e) => setFormData({ ...formData, cantidad: e.target.value })}
                    required
                  />
                </div>
              </div>
              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
                <div className="form-group">
                  <label className="form-label">Categoría</label>
                  <select
                    className="form-select"
                    value={formData.categoriaId}
                    onChange={(e) => setFormData({ ...formData, categoriaId: e.target.value })}
                  >
                    <option value="">Seleccionar...</option>
                    {categorias.map(cat => (
                      <option key={cat.id} value={cat.id}>{cat.nombre}</option>
                    ))}
                  </select>
                </div>
                <div className="form-group">
                  <label className="form-label">Proveedor</label>
                  <select
                    className="form-select"
                    value={formData.proveedorId}
                    onChange={(e) => setFormData({ ...formData, proveedorId: e.target.value })}
                  >
                    <option value="">Seleccionar...</option>
                    {proveedores.map(prov => (
                      <option key={prov.id} value={prov.id}>{prov.nombre}</option>
                    ))}
                  </select>
                </div>
              </div>
              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
                <div className="form-group">
                  <label className="form-label">Stock Mínimo</label>
                  <input
                    type="number"
                    className="form-input"
                    value={formData.stockMinimo}
                    onChange={(e) => setFormData({ ...formData, stockMinimo: e.target.value })}
                  />
                </div>
                <div className="form-group">
                  <label className="form-label">Stock Máximo</label>
                  <input
                    type="number"
                    className="form-input"
                    value={formData.stockMaximo}
                    onChange={(e) => setFormData({ ...formData, stockMaximo: e.target.value })}
                  />
                </div>
              </div>
              <div style={{ display: 'flex', gap: '1rem', justifyContent: 'flex-end' }}>
                <button type="button" className="btn btn-secondary" onClick={closeModal}>
                  Cancelar
                </button>
                <button type="submit" className="btn btn-primary">
                  {editingProducto ? 'Actualizar' : 'Crear'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Productos;
