package com.example.trabalhoweb4bi.service;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.domain.ContasPagRec;
import com.example.trabalhoweb4bi.enums.TipoConta;
import com.example.trabalhoweb4bi.repository.ContasPagRecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

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

    public List<String> validate(ContasPagRec contasPagRec){
        List<String> msg = new ArrayList<>();
        String regex = "^[a-zA-Z0-9]*$";
        String regex2 = "^[0-9]*$";

        if(!Pattern.matches(regex, contasPagRec.getDescricao().replace(" ", ""))){
            msg.add("A descrição deve conter apenas caracteres alfanumericos.");
        }
        if(contasPagRec.getCategoria() == null){
            msg.add("A categoria precisa ser informado.");
        }
        if(contasPagRec.getValor() != null && contasPagRec.getValor().compareTo(BigDecimal.ZERO) <= 0){
            msg.add("Valor inválido");
        }
        if(!Pattern.matches(regex2, String.valueOf(contasPagRec.getValor()).replaceAll(".",""))){
            msg.add("Informe apenas números.");
        }
        if(contasPagRec.getDescricao().trim().length() < 5 || contasPagRec.getDescricao().trim().length() > 60){
            msg.add("O tamanho da descrição deve estar entre 5 e 60 caracteres");
        }

        return msg;
    }

    public void insert(ContasPagRec contasPagRec){
        if(contasPagRec.getId() > 0){
            //gambiarra para nao quebrar o update setando a dataLcto para null em update
            contasPagRec.setDataLcto(findById(contasPagRec.getId()).getDataLcto());
        }
        contasPagRecRepository.saveAndFlush(contasPagRec);
    }

    public ContasPagRec findById(Long id) {
        return contasPagRecRepository.findById(id).get();
    }


    public void delete(Long id) {
        ContasPagRec contasPagRec = findById(id);
        contasPagRec.setAtivo(false);
        contasPagRecRepository.saveAndFlush(contasPagRec);
    }

    public BigDecimal retornaSomaContas(TipoConta tipoConta){
        return contasPagRecRepository.retornaSomaContas(tipoConta.toString());
    }

}
