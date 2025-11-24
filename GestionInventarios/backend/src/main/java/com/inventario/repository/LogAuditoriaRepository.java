package com.inventario.repository;

import com.inventario.model.LogAuditoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogAuditoriaRepository extends MongoRepository<LogAuditoria, String> {
    List<LogAuditoria> findByUsuarioId(String usuarioId);
    List<LogAuditoria> findByEntidad(String entidad);
    List<LogAuditoria> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<LogAuditoria> findByAccion(String accion);
}
