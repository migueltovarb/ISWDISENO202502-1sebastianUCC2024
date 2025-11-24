package com.inventario.controller;

import com.inventario.model.SalidaProducto;
import com.inventario.service.SalidaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/salidas")
@CrossOrigin(origins = "*")
public class SalidaProductoController {
    
    @Autowired
    private SalidaProductoService salidaService;
    
    @GetMapping
    public ResponseEntity<List<SalidaProducto>> listarSalidas() {
        return ResponseEntity.ok(salidaService.listarSalidas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SalidaProducto> obtenerSalidaPorId(@PathVariable String id) {
        return salidaService.obtenerSalidaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/producto/{codigoProducto}")
    public ResponseEntity<List<SalidaProducto>> obtenerSalidasPorProducto(@PathVariable String codigoProducto) {
        return ResponseEntity.ok(salidaService.obtenerSalidasPorProducto(codigoProducto));
    }
    
    @PostMapping
    public ResponseEntity<?> registrarSalida(@Valid @RequestBody SalidaProducto salida) {
        try {
            SalidaProducto nuevaSalida = salidaService.registrarSalida(salida);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSalida);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSalida(@PathVariable String id) {
        return salidaService.eliminarSalida(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
