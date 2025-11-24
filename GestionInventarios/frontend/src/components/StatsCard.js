import React from 'react';
import './StatsCard.css';

const StatsCard = ({ title, value, icon, gradient, trend }) => {
  return (
    <div className="stats-card" style={{ background: gradient }}>
      <div className="stats-content">
        <div className="stats-info">
          <p className="stats-title">{title}</p>
          <h2 className="stats-value">{value}</h2>
          {trend && (
            <span className={`stats-trend ${trend.type}`}>
              {trend.type === 'up' ? '↑' : '↓'} {trend.value}%
            </span>
          )}
        </div>
        <div className="stats-icon">
          {icon}
        </div>
      </div>
      <div className="stats-glow"></div>
    </div>
  );
};

export default StatsCard;
