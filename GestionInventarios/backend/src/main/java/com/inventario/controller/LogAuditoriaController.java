package com.inventario.controller;

import com.inventario.model.LogAuditoria;
import com.inventario.service.LogAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "*")
public class LogAuditoriaController {
    
    @Autowired
    private LogAuditoriaService logService;
    
    @GetMapping
    public ResponseEntity<List<LogAuditoria>> listarLogs() {
        return ResponseEntity.ok(logService.listarLogs());
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<LogAuditoria>> obtenerLogsPorUsuario(@PathVariable String usuarioId) {
        return ResponseEntity.ok(logService.obtenerLogsPorUsuario(usuarioId));
    }
    
    @GetMapping("/entidad/{entidad}")
    public ResponseEntity<List<LogAuditoria>> obtenerLogsPorEntidad(@PathVariable String entidad) {
        return ResponseEntity.ok(logService.obtenerLogsPorEntidad(entidad));
    }
    
    @GetMapping("/fecha")
    public ResponseEntity<List<LogAuditoria>> obtenerLogsPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(logService.obtenerLogsPorFecha(inicio, fin));
    }
}
