package com.inventario.controller;

import com.inventario.model.Proveedor;
import com.inventario.service.ProveedorService;
import com.inventario.service.LogAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {
    
    @Autowired
    private ProveedorService proveedorService;
    
    @Autowired
    private LogAuditoriaService logService;
    
    @GetMapping
    public ResponseEntity<List<Proveedor>> listarProveedores() {
        return ResponseEntity.ok(proveedorService.listarProveedores());
    }
    
    @GetMapping("/activos")
    public ResponseEntity<List<Proveedor>> listarProveedoresActivos() {
        return ResponseEntity.ok(proveedorService.listarProveedoresActivos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable String id) {
        return proveedorService.obtenerProveedorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nit/{nit}")
    public ResponseEntity<Proveedor> obtenerProveedorPorNit(@PathVariable String nit) {
        return proveedorService.obtenerProveedorPorNit(nit)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@Valid @RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.crearProveedor(proveedor);
        logService.registrarLog("SYSTEM", "Sistema", "CREATE", "PROVEEDOR", 
            nuevoProveedor.getId(), "Proveedor creado: " + nuevoProveedor.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(
            @PathVariable String id, 
            @Valid @RequestBody Proveedor proveedor) {
        return proveedorService.actualizarProveedor(id, proveedor)
                .map(p -> {
                    logService.registrarLog("SYSTEM", "Sistema", "UPDATE", "PROVEEDOR", 
                        id, "Proveedor actualizado: " + p.getNombre());
                    return ResponseEntity.ok(p);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable String id) {
        boolean eliminado = proveedorService.eliminarProveedor(id);
        if (eliminado) {
            logService.registrarLog("SYSTEM", "Sistema", "DELETE", "PROVEEDOR", 
                id, "Proveedor eliminado");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
