package com.example.trabalhoweb4bi.service;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.domain.ContasPagRec;
import com.example.trabalhoweb4bi.repository.ContasPagRecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service
public class ContasPagRecService {

    @Autowired
    private ContasPagRecRepository contasPagRecRepository;

    public List<ContasPagRec> listAll(){
        return contasPagRecRepository.findAllByAtivoIsTrueOrderByIdAsc();
    }

    public List<ContasPagRec> listByFilter(String descricao, String dataLcto) {
        Date dataFormatada = null;
        if(!dataLcto.isEmpty()){
            dataFormatada = convertData(dataLcto);
        }

        if (descricao.isEmpty() && dataLcto.isEmpty()) {
            return contasPagRecRepository.findAllByAtivoIsTrueOrderByIdAsc();

        }else if(!descricao.isEmpty() && dataLcto.isEmpty()){
            return contasPagRecRepository.findALlByDescricaoContainsIgnoreCaseAndAtivoIsTrueOrderByIdAsc(descricao);

        }else if(descricao.isEmpty() && !dataLcto.isEmpty()){
            return contasPagRecRepository.findALlByDataLctoAndAtivoIsTrueOrderByIdAsc(dataFormatada);

        }else {
            return contasPagRecRepository.findALlByDescricaoContainsIgnoreCaseAndDataLctoAndAtivoIsTrueOrderByIdAsc(descricao, dataFormatada);
        }
    }

    private Date convertData(String data){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = dateFormat.parse(data);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
