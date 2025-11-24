package com.inventario.service;

import com.inventario.model.Producto;
import com.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
    
    public List<Producto> listarProductosActivos() {
        return productoRepository.findByActivoTrue();
    }
    
    public Optional<Producto> obtenerProductoPorId(String id) {
        return productoRepository.findById(id);
    }
    
    public Optional<Producto> obtenerProductoPorCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }
    
    public List<Producto> obtenerProductosPorCategoria(String categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    
    public List<Producto> obtenerProductosPorProveedor(String proveedorId) {
        return productoRepository.findByProveedorId(proveedorId);
    }
    
    public List<Producto> obtenerProductosBajoStock() {
        return productoRepository.findAll().stream()
            .filter(p -> p.getCantidad() <= p.getStockMinimo())
            .collect(Collectors.toList());
    }
    
    public Producto crearProducto(Producto producto) {
        if (productoRepository.existsByCodigo(producto.getCodigo())) {
            throw new RuntimeException("Ya existe un producto con el código: " + producto.getCodigo());
        }
        return productoRepository.save(producto);
    }
    
    public Optional<Producto> actualizarProducto(String id, Producto producto) {
        return productoRepository.findById(id).map(p -> {
            p.setNombre(producto.getNombre());
            p.setDescripcion(producto.getDescripcion());
            p.setPrecio(producto.getPrecio());
            p.setCantidad(producto.getCantidad());
            p.setCategoriaId(producto.getCategoriaId());
            p.setProveedorId(producto.getProveedorId());
            p.setStockMinimo(producto.getStockMinimo());
            p.setStockMaximo(producto.getStockMaximo());
            p.setActivo(producto.getActivo());
            return productoRepository.save(p);
        });
    }
    
    public boolean eliminarProducto(String id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
