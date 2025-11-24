package com.inventario.service;

import com.inventario.model.SalidaProducto;
import com.inventario.model.Producto;
import com.inventario.repository.SalidaProductoRepository;
import com.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalidaProductoService {
    
    @Autowired
    private SalidaProductoRepository salidaRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<SalidaProducto> listarSalidas() {
        return salidaRepository.findAll();
    }
    
    public Optional<SalidaProducto> obtenerSalidaPorId(String id) {
        return salidaRepository.findById(id);
    }
    
    public List<SalidaProducto> obtenerSalidasPorProducto(String codigoProducto) {
        return salidaRepository.findByCodigoProducto(codigoProducto);
    }
    
    public SalidaProducto registrarSalida(SalidaProducto salida) {
        salida.setFecha(LocalDateTime.now());
        
        // Actualizar cantidad del producto
        Optional<Producto> productoOpt = productoRepository.findByCodigo(salida.getCodigoProducto());
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            if (producto.getCantidad() < salida.getCantidad()) {
                throw new RuntimeException("Stock insuficiente. Disponible: " + producto.getCantidad());
            }
            producto.setCantidad(producto.getCantidad() - salida.getCantidad());
            productoRepository.save(producto);
        } else {
            throw new RuntimeException("Producto no encontrado con código: " + salida.getCodigoProducto());
        }
        
        return salidaRepository.save(salida);
    }
    
    public boolean eliminarSalida(String id) {
        if (salidaRepository.existsById(id)) {
            salidaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
