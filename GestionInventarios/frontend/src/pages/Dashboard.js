import { useState, useEffect } from 'react';
import { FaBox, FaArrowDown, FaArrowUp, FaExclamationTriangle } from 'react-icons/fa';
import { productosAPI, entradasAPI, salidasAPI } from '../services/api';
import StatsCard from '../components/StatsCard';
import Loading from '../components/Loading';
import EmptyState from '../components/EmptyState';

const Dashboard = () => {
  const [stats, setStats] = useState({
    totalProductos: 0,
    totalEntradas: 0,
    totalSalidas: 0,
    productosBajoStock: 0,
  });
  const [productosBajoStock, setProductosBajoStock] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadDashboardData();
  }, []);

  const loadDashboardData = async () => {
    try {
      const [productos, entradas, salidas, bajoStock] = await Promise.all([
        productosAPI.getAll(),
        entradasAPI.getAll(),
        salidasAPI.getAll(),
        productosAPI.getBajoStock(),
      ]);

      setStats({
        totalProductos: productos.data.length,
        totalEntradas: entradas.data.length,
        totalSalidas: salidas.data.length,
        productosBajoStock: bajoStock.data.length,
      });

      setProductosBajoStock(bajoStock.data);
      setLoading(false);
    } catch (error) {
      console.error('Error cargando dashboard:', error);
      setLoading(false);
    }
  };

  if (loading) {
    return <Loading message="Cargando dashboard..." />;
  }

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Dashboard</h1>
        <p className="page-subtitle">Resumen general del inventario</p>
      </div>

      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(280px, 1fr))', gap: '2rem', marginBottom: '2rem' }}>
        <StatsCard
          title="Total Productos"
          value={stats.totalProductos}
          icon={<FaBox />}
          gradient="linear-gradient(135deg, #667eea 0%, #764ba2 100%)"
        />
        
        <StatsCard
          title="Total Entradas"
          value={stats.totalEntradas}
          icon={<FaArrowDown />}
          gradient="linear-gradient(135deg, #10b981 0%, #059669 100%)"
        />
        
        <StatsCard
          title="Total Salidas"
          value={stats.totalSalidas}
          icon={<FaArrowUp />}
          gradient="linear-gradient(135deg, #3b82f6 0%, #2563eb 100%)"
        />
        
        <StatsCard
          title="Stock Bajo"
          value={stats.productosBajoStock}
          icon={<FaExclamationTriangle />}
          gradient="linear-gradient(135deg, #f59e0b 0%, #d97706 100%)"
        />
      </div>

      {productosBajoStock.length > 0 ? (
        <div className="card">
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '1.5rem' }}>
            <FaExclamationTriangle style={{ color: '#f59e0b', fontSize: '1.5rem' }} />
            <h3 style={{ margin: 0, color: '#1e293b', fontSize: '1.25rem', fontWeight: 700 }}>
              Productos con Stock Bajo
            </h3>
          </div>
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Código</th>
                  <th>Nombre</th>
                  <th>Stock Actual</th>
                  <th>Stock Mínimo</th>
                  <th>Estado</th>
                </tr>
              </thead>
              <tbody>
                {productosBajoStock.map((producto) => (
                  <tr key={producto.id}>
                    <td><strong>{producto.codigo}</strong></td>
                    <td>{producto.nombre}</td>
                    <td><strong style={{ color: '#ef4444' }}>{producto.cantidad}</strong></td>
                    <td>{producto.stockMinimo}</td>
                    <td>
                      <span className="badge badge-warning">Stock Bajo</span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      ) : stats.totalProductos === 0 ? (
        <EmptyState
          icon={<FaBox />}
          title="No hay productos"
          message="Comienza agregando productos al inventario para ver las estadísticas"
        />
      ) : (
        <div className="card" style={{ textAlign: 'center', padding: '3rem' }}>
          <FaBox style={{ fontSize: '3rem', color: '#10b981', marginBottom: '1rem' }} />
          <h3 style={{ color: '#1e293b', marginBottom: '0.5rem' }}>¡Todo en orden!</h3>
          <p style={{ color: '#64748b' }}>Todos los productos tienen stock suficiente</p>
        </div>
      )}
    </div>
  );
};

export default Dashboard;
