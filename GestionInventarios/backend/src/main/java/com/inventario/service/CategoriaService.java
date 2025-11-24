package com.inventario.service;

import com.inventario.model.Categoria;
import com.inventario.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
    
    public List<Categoria> listarCategoriasActivas() {
        return categoriaRepository.findByActivaTrue();
    }
    
    public Optional<Categoria> obtenerCategoriaPorId(String id) {
        return categoriaRepository.findById(id);
    }
    
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    public Optional<Categoria> actualizarCategoria(String id, Categoria categoria) {
        return categoriaRepository.findById(id).map(c -> {
            c.setNombre(categoria.getNombre());
            c.setDescripcion(categoria.getDescripcion());
            c.setActiva(categoria.getActiva());
            return categoriaRepository.save(c);
        });
    }
    
    public boolean eliminarCategoria(String id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
