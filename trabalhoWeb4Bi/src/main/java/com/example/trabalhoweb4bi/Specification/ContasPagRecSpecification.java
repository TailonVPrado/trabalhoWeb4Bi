package com.example.trabalhoweb4bi.Specification;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.domain.ContasPagRec;
import com.example.trabalhoweb4bi.enums.TipoConta;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;

public class ContasPagRecSpecification {

    public static Specification<ContasPagRec> ativo() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("ativo"));
    }
    public static Specification<ContasPagRec> descricaoContains(String descricao){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + descricao.toLowerCase()+"%");
    }

    public static Specification<ContasPagRec> tipoContaEquals(String tipoConta) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("categoria").get("tipoConta"), TipoConta.valueOf(tipoConta));
    }
    public static Specification<ContasPagRec> dataLctoEquals(Date dataLcto) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("dataLcto"), dataLcto);
    }

    public static Specification<ContasPagRec> pertenceACategoria(Long idCategoria) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("categoria").get("id"), idCategoria);
    }
}
