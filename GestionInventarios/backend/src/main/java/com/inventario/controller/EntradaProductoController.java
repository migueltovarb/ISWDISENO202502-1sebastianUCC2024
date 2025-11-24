package com.inventario.controller;

import com.inventario.model.EntradaProducto;
import com.inventario.service.EntradaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/entradas")
@CrossOrigin(origins = "*")
public class EntradaProductoController {
    
    @Autowired
    private EntradaProductoService entradaService;
    
    @GetMapping
    public ResponseEntity<List<EntradaProducto>> listarEntradas() {
        return ResponseEntity.ok(entradaService.listarEntradas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EntradaProducto> obtenerEntradaPorId(@PathVariable String id) {
        return entradaService.obtenerEntradaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/producto/{codigoProducto}")
    public ResponseEntity<List<EntradaProducto>> obtenerEntradasPorProducto(@PathVariable String codigoProducto) {
        return ResponseEntity.ok(entradaService.obtenerEntradasPorProducto(codigoProducto));
    }
    
    @PostMapping
    public ResponseEntity<?> registrarEntrada(@Valid @RequestBody EntradaProducto entrada) {
        try {
            EntradaProducto nuevaEntrada = entradaService.registrarEntrada(entrada);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEntrada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrada(@PathVariable String id) {
        return entradaService.eliminarEntrada(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
