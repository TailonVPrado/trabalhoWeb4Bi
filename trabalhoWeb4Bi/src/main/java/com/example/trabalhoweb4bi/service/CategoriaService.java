package com.example.trabalhoweb4bi.service;

import com.example.trabalhoweb4bi.Specification.CategoriaSpecification;
import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Categoria> listAll(){
        return categoriaRepository.findAllByAtivoIsTrueOrderByIdDesc();
    }


    public List<Categoria> listByFilter(String descricao, String tipoConta) {

        Specification<Categoria> spec = Specification.where(null);

        spec = spec.and(CategoriaSpecification.ativo());

        if(!descricao.isEmpty()){
            spec = spec.and(CategoriaSpecification.descricaoContains(descricao));
        }
        if(!tipoConta.isEmpty()){
            spec = spec.and(CategoriaSpecification.tipoContaEquals(tipoConta));
        }

        return categoriaRepository.findAll(spec, Sort.by("id").descending());

    }

    public List<String> validate(Categoria categoria){
        List<String> msg = new ArrayList<>();
        String regex = "^[a-zA-Z0-9]*$";

        if(!Pattern.matches(regex, categoria.getDescricao().replace(" ", ""))){
            msg.add("A descrição deve conter apenas caracteres alfanumericos.");
        }
        if(categoria.getTipoConta() == null){
            msg.add("O tipo da categória precisa ser informado.");
        }
        if(categoria.getDescricao().trim().length() < 5 || categoria.getDescricao().trim().length() > 60){
            msg.add("O tamanho da descrição deve estar entre 5 e 60 caracteres");
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
