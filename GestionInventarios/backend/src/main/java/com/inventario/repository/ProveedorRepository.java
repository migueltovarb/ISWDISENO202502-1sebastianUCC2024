package com.inventario.repository;

import com.inventario.model.Proveedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ProveedorRepository extends MongoRepository<Proveedor, String> {
    Optional<Proveedor> findByNit(String nit);
    List<Proveedor> findByActivoTrue();
}
