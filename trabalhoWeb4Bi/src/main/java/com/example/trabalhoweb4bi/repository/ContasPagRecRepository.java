package com.example.trabalhoweb4bi.repository;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.domain.ContasPagRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.List;

@Repository
public interface ContasPagRecRepository extends JpaRepository<ContasPagRec, Long> {
    @Query
    public List<ContasPagRec> findAllByAtivoIsTrueOrderByIdAsc();

    @Query
    public  List<ContasPagRec> findALlByDescricaoContainsIgnoreCaseAndAtivoIsTrueOrderByIdAsc(String descricao);


    @Query
    public  List<ContasPagRec> findALlByDataLctoAndAtivoIsTrueOrderByIdAsc(Date dataLcto);
    public  List<ContasPagRec> findALlByDescricaoContainsIgnoreCaseAndDataLctoAndAtivoIsTrueOrderByIdAsc(String descricao, Date dataLcto);

}
