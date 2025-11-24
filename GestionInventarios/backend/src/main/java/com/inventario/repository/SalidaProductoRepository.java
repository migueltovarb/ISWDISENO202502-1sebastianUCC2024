package com.inventario.repository;

import com.inventario.model.SalidaProducto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SalidaProductoRepository extends MongoRepository<SalidaProducto, String> {
    List<SalidaProducto> findByCodigoProducto(String codigoProducto);
    List<SalidaProducto> findByUsuarioId(String usuarioId);
}
