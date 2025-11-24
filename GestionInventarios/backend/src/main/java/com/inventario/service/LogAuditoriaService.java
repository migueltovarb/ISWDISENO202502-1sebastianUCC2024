package com.inventario.service;

import com.inventario.model.LogAuditoria;
import com.inventario.repository.LogAuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogAuditoriaService {
    
    @Autowired
    private LogAuditoriaRepository logRepository;
    
    public void registrarLog(String usuarioId, String usuarioNombre, String accion, 
                            String entidad, String entidadId, String descripcion) {
        LogAuditoria log = new LogAuditoria();
        log.setUsuarioId(usuarioId);
        log.setUsuarioNombre(usuarioNombre);
        log.setAccion(accion);
        log.setEntidad(entidad);
        log.setEntidadId(entidadId);
        log.setDescripcion(descripcion);
        log.setFecha(LocalDateTime.now());
        logRepository.save(log);
    }
    
    public List<LogAuditoria> listarLogs() {
        return logRepository.findAll();
    }
    
    public List<LogAuditoria> obtenerLogsPorUsuario(String usuarioId) {
        return logRepository.findByUsuarioId(usuarioId);
    }
    
    public List<LogAuditoria> obtenerLogsPorEntidad(String entidad) {
        return logRepository.findByEntidad(entidad);
    }
    
    public List<LogAuditoria> obtenerLogsPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return logRepository.findByFechaBetween(inicio, fin);
    }
}
