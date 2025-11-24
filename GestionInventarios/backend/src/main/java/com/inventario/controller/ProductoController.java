package com.inventario.controller;

import com.inventario.model.Producto;
import com.inventario.service.ProductoService;
import com.inventario.service.LogAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private LogAuditoriaService logService;
    
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }
    
    @GetMapping("/activos")
    public ResponseEntity<List<Producto>> listarProductosActivos() {
        return ResponseEntity.ok(productoService.listarProductosActivos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable String id) {
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Producto> obtenerProductoPorCodigo(@PathVariable String codigo) {
        return productoService.obtenerProductoPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable String categoriaId) {
        return ResponseEntity.ok(productoService.obtenerProductosPorCategoria(categoriaId));
    }
    
    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorProveedor(@PathVariable String proveedorId) {
        return ResponseEntity.ok(productoService.obtenerProductosPorProveedor(proveedorId));
    }
    
    @GetMapping("/bajo-stock")
    public ResponseEntity<List<Producto>> obtenerProductosBajoStock() {
        return ResponseEntity.ok(productoService.obtenerProductosBajoStock());
    }
    
    @PostMapping
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.crearProducto(producto);
            logService.registrarLog("SYSTEM", "Sistema", "CREATE", "PRODUCTO", 
                nuevoProducto.getId(), "Producto creado: " + nuevoProducto.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable String id, 
            @Valid @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto)
                .map(p -> {
                    logService.registrarLog("SYSTEM", "Sistema", "UPDATE", "PRODUCTO", 
                        id, "Producto actualizado: " + p.getNombre());
                    return ResponseEntity.ok(p);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        boolean eliminado = productoService.eliminarProducto(id);
        if (eliminado) {
            logService.registrarLog("SYSTEM", "Sistema", "DELETE", "PRODUCTO", 
                id, "Producto eliminado");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
