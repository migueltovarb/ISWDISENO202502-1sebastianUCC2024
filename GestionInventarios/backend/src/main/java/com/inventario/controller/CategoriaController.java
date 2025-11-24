package com.inventario.controller;

import com.inventario.model.Categoria;
import com.inventario.service.CategoriaService;
import com.inventario.service.LogAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private LogAuditoriaService logService;
    
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
    
    @GetMapping("/activas")
    public ResponseEntity<List<Categoria>> listarCategoriasActivas() {
        return ResponseEntity.ok(categoriaService.listarCategoriasActivas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable String id) {
        return categoriaService.obtenerCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
        logService.registrarLog("SYSTEM", "Sistema", "CREATE", "CATEGORIA", 
            nuevaCategoria.getId(), "Categoría creada: " + nuevaCategoria.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(
            @PathVariable String id, 
            @Valid @RequestBody Categoria categoria) {
        return categoriaService.actualizarCategoria(id, categoria)
                .map(c -> {
                    logService.registrarLog("SYSTEM", "Sistema", "UPDATE", "CATEGORIA", 
                        id, "Categoría actualizada: " + c.getNombre());
                    return ResponseEntity.ok(c);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable String id) {
        boolean eliminado = categoriaService.eliminarCategoria(id);
        if (eliminado) {
            logService.registrarLog("SYSTEM", "Sistema", "DELETE", "CATEGORIA", 
                id, "Categoría eliminada");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
