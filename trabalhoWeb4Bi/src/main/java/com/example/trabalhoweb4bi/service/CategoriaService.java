package com.example.trabalhoweb4bi.service;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Categoria> listAll(){
        return categoriaRepository.findAll();
    }
}
