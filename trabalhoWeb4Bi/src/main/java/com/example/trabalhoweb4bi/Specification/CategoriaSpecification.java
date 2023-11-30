package com.example.trabalhoweb4bi.Specification;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.enums.TipoConta;
import org.springframework.data.jpa.domain.Specification;

public class CategoriaSpecification {
    public static Specification<Categoria> descricaoContains(String descricao){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + descricao.toLowerCase()+"%");
    }
    public static Specification<Categoria> tipoContaEquals(String tipoConta) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoConta"), TipoConta.valueOf(tipoConta));
    }
    public static Specification<Categoria> ativo() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("ativo"));
    }
}
