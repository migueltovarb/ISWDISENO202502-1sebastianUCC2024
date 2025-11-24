package com.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "proveedores")
public class Proveedor {
    @Id
    private String id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "El NIT es obligatorio")
    private String nit;
    
    private String telefono;
    
    private String direccion;
    
    @Email(message = "Email inválido")
    private String email;
    
    private String contacto;
    
    private Boolean activo = true;
}
