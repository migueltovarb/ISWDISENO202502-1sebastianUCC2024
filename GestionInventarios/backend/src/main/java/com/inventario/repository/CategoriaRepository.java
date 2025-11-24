package com.inventario.repository;

import com.inventario.model.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, String> {
    List<Categoria> findByActivaTrue();
}
