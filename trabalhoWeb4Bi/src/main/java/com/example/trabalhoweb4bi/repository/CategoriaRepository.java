package com.example.trabalhoweb4bi.repository;

import com.example.trabalhoweb4bi.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query
    public List<Categoria> findAllByDescricaoContainingIgnoreCaseOrderByIdAsc(String descricao);
    @Query
    public List<Categoria> findAllByOrderByIdAsc();
}
