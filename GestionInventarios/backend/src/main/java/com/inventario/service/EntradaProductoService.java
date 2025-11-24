package com.inventario.service;

import com.inventario.model.EntradaProducto;
import com.inventario.model.Producto;
import com.inventario.repository.EntradaProductoRepository;
import com.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EntradaProductoService {
    
    @Autowired
    private EntradaProductoRepository entradaRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<EntradaProducto> listarEntradas() {
        return entradaRepository.findAll();
    }
    
    public Optional<EntradaProducto> obtenerEntradaPorId(String id) {
        return entradaRepository.findById(id);
    }
    
    public List<EntradaProducto> obtenerEntradasPorProducto(String codigoProducto) {
        return entradaRepository.findByCodigoProducto(codigoProducto);
    }
    
    public EntradaProducto registrarEntrada(EntradaProducto entrada) {
        entrada.setFecha(LocalDateTime.now());
        
        // Actualizar cantidad del producto
        Optional<Producto> productoOpt = productoRepository.findByCodigo(entrada.getCodigoProducto());
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            producto.setCantidad(producto.getCantidad() + entrada.getCantidad());
            productoRepository.save(producto);
        } else {
            throw new RuntimeException("Producto no encontrado con código: " + entrada.getCodigoProducto());
        }
        
        return entradaRepository.save(entrada);
    }
    
    public boolean eliminarEntrada(String id) {
        if (entradaRepository.existsById(id)) {
            entradaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
