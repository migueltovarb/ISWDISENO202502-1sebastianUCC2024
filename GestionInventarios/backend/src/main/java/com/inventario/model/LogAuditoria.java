package com.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "logs_auditoria")
public class LogAuditoria {
    @Id
    private String id;
    
    private String usuarioId;
    
    private String usuarioNombre;
    
    private String accion; // CREATE, UPDATE, DELETE, LOGIN, LOGOUT
    
    private String entidad; // PRODUCTO, USUARIO, PROVEEDOR, ENTRADA, SALIDA
    
    private String entidadId;
    
    private String descripcion;
    
    private LocalDateTime fecha;
    
    private String ipAddress;
}
