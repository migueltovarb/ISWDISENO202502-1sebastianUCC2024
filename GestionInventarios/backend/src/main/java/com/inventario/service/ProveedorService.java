package com.inventario.service;

import com.inventario.model.Proveedor;
import com.inventario.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    
    @Autowired
    private ProveedorRepository proveedorRepository;
    
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }
    
    public List<Proveedor> listarProveedoresActivos() {
        return proveedorRepository.findByActivoTrue();
    }
    
    public Optional<Proveedor> obtenerProveedorPorId(String id) {
        return proveedorRepository.findById(id);
    }
    
    public Optional<Proveedor> obtenerProveedorPorNit(String nit) {
        return proveedorRepository.findByNit(nit);
    }
    
    public Proveedor crearProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }
    
    public Optional<Proveedor> actualizarProveedor(String id, Proveedor proveedor) {
        return proveedorRepository.findById(id).map(p -> {
            p.setNombre(proveedor.getNombre());
            p.setNit(proveedor.getNit());
            p.setTelefono(proveedor.getTelefono());
            p.setDireccion(proveedor.getDireccion());
            p.setEmail(proveedor.getEmail());
            p.setContacto(proveedor.getContacto());
            p.setActivo(proveedor.getActivo());
            return proveedorRepository.save(p);
        });
    }
    
    public boolean eliminarProveedor(String id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
