import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { FaBox, FaTags, FaTruck, FaArrowDown, FaArrowUp, FaUsers, FaClipboardList, FaChartBar } from 'react-icons/fa';
import Logo from './Logo';
import './Layout.css';

const Layout = ({ children }) => {
  const location = useLocation();

  const menuItems = [
    { path: '/', icon: <FaChartBar />, label: 'Dashboard' },
    { path: '/productos', icon: <FaBox />, label: 'Productos' },
    { path: '/categorias', icon: <FaTags />, label: 'Categorías' },
    { path: '/proveedores', icon: <FaTruck />, label: 'Proveedores' },
    { path: '/entradas', icon: <FaArrowDown />, label: 'Entradas' },
    { path: '/salidas', icon: <FaArrowUp />, label: 'Salidas' },
    { path: '/usuarios', icon: <FaUsers />, label: 'Usuarios' },
    { path: '/logs', icon: <FaClipboardList />, label: 'Auditoría' },
  ];

  return (
    <div className="layout">
      <nav className="navbar">
        <div className="navbar-brand">
          <h1><Logo /> Inventarios</h1>
        </div>
        <div className="navbar-nav">
          {menuItems.map((item) => (
            <Link
              key={item.path}
              to={item.path}
              className={`nav-item ${location.pathname === item.path ? 'active' : ''}`}
            >
              <span className="nav-icon">{item.icon}</span>
              <span className="nav-label">{item.label}</span>
            </Link>
          ))}
        </div>
      </nav>
      <main className="main-content">
        {children}
      </main>
    </div>
  );
};

export default Layout;
