package com.inventario.repository;

import com.inventario.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
    Optional<Producto> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
    List<Producto> findByCategoriaId(String categoriaId);
    List<Producto> findByProveedorId(String proveedorId);
    List<Producto> findByCantidadLessThanEqual(Integer cantidad);
    List<Producto> findByActivoTrue();
}
