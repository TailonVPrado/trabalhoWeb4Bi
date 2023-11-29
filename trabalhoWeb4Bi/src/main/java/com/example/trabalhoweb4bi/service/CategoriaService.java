package com.example.trabalhoweb4bi.service;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Categoria> listAll(){
        return categoriaRepository.findAllByAtivoIsTrueOrderByIdAsc();
    }
    public List<Categoria> listByFilter(String descricao) {
        if (descricao.isEmpty()) {
            return categoriaRepository.findAllByAtivoIsTrueOrderByIdAsc();
        } else {
            return categoriaRepository.findAllByDescricaoContainingIgnoreCaseAndAtivoIsTrueOrderByIdAsc(descricao);
        }
    }

    public List<String> validate(Categoria categoria){
        List<String> msg = new ArrayList<>();
        String regex = "^[a-zA-Z0-9]*$";

        if(!Pattern.matches(regex, categoria.getDescricao())){
            msg.add("A descrição deve conter apenas caracteres alfanumericos.");
        }
        if(categoria.getTipoConta() == null){
            msg.add("O tipo da categória precisa ser informado.");
        }

        return msg;
    }

    public void insert(Categoria categoria){
        categoriaRepository.saveAndFlush(categoria);
    }


    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).get();
    }

    public void delete(Long id) {
        Categoria categoria = findById(id);
        categoria.setAtivo(false);
        categoriaRepository.saveAndFlush(categoria);
    }
}
