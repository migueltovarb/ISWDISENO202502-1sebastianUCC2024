package com.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "salidas")
public class SalidaProducto {
    @Id
    private String id;
    
    @NotBlank(message = "El código del producto es obligatorio")
    private String codigoProducto;
    
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;
    
    @NotBlank(message = "El usuario es obligatorio")
    private String usuarioId;
    
    private LocalDateTime fecha;
    
    private String observaciones;
}
