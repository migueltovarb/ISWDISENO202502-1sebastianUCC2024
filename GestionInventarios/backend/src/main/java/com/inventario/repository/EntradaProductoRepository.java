package com.inventario.repository;

import com.inventario.model.EntradaProducto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntradaProductoRepository extends MongoRepository<EntradaProducto, String> {
    List<EntradaProducto> findByCodigoProducto(String codigoProducto);
    List<EntradaProducto> findByUsuarioId(String usuarioId);
}
