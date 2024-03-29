package com.example.trabalhoweb4bi.repository;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.domain.ContasPagRec;
import com.example.trabalhoweb4bi.enums.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface ContasPagRecRepository extends JpaRepository<ContasPagRec, Long>, JpaSpecificationExecutor<ContasPagRec> {
    @Query
    public List<ContasPagRec> findAllByAtivoIsTrueOrderByIdDesc();

    @Query
    public  List<ContasPagRec> findALlByDescricaoContainsIgnoreCaseAndAtivoIsTrueOrderByIdDesc(String descricao);


    @Query
    public  List<ContasPagRec> findALlByDataLctoAndAtivoIsTrueOrderByIdDesc(Date dataLcto);
    public  List<ContasPagRec> findALlByDescricaoContainsIgnoreCaseAndDataLctoAndAtivoIsTrueOrderByIdDesc(String descricao, Date dataLcto);

    @Query(value = "SELECT SUM(CONTASPAGREC.VALOR) \n" +
                   "  FROM CONTASPAGREC,\n" +
                   "  \t   CATEGORIA\n" +
                   " WHERE CONTASPAGREC.CATEGORIA_ID = CATEGORIA.ID \n" +
                   "   AND CONTASPAGREC.ATIVO\n" +
                   "   AND CATEGORIA.TIPO_CONTA = :tipoConta",
    nativeQuery = true)
    BigDecimal retornaSomaContas(@Param("tipoConta") String tipoConta);

    List<ContasPagRec> findTop10ByAtivoIsTrueOrderByIdDesc();
}
