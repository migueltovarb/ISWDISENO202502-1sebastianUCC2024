import React, { useState, useEffect } from 'react';
import { logsAPI } from '../services/api';

const Logs = () => {
  const [logs, setLogs] = useState([]);

  useEffect(() => { loadData(); }, []);

  const loadData = async () => {
    const res = await logsAPI.getAll();
    setLogs(res.data);
  };

  const getBadgeClass = (accion) => {
    switch (accion) {
      case 'CREATE': return 'badge-success';
      case 'UPDATE': return 'badge-info';
      case 'DELETE': return 'badge-danger';
      default: return 'badge-secondary';
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Auditoría</h1>
        <p className="page-subtitle">Registro de actividades del sistema</p>
      </div>
      <div className="card">
        <table>
          <thead>
            <tr><th>Fecha</th><th>Usuario</th><th>Acción</th><th>Entidad</th><th>Descripción</th></tr>
          </thead>
          <tbody>
            {logs.map(log => (
              <tr key={log.id}>
                <td>{new Date(log.fecha).toLocaleString()}</td>
                <td>{log.usuarioNombre}</td>
                <td><span className={`badge ${getBadgeClass(log.accion)}`}>{log.accion}</span></td>
                <td>{log.entidad}</td>
                <td>{log.descripcion}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Logs;
