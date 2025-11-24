import React from 'react';
import { FaSearch } from 'react-icons/fa';
import './SearchBar.css';

const SearchBar = ({ value, onChange, placeholder = "Buscar..." }) => {
  return (
    <div className="search-bar">
      <FaSearch className="search-icon" />
      <input
        type="text"
        className="search-input"
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />
      {value && (
        <button 
          className="search-clear"
          onClick={() => onChange({ target: { value: '' } })}
        >
          ×
        </button>
      )}
    </div>
  );
};

export default SearchBar;
