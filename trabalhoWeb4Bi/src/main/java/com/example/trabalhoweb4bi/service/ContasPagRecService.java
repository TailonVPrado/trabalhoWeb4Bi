package com.example.trabalhoweb4bi.service;

import com.example.trabalhoweb4bi.domain.ContasPagRec;
import com.example.trabalhoweb4bi.repository.ContasPagRecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContasPagRecService {

    @Autowired
    private ContasPagRecRepository contasPagRecRepository;

    public List<ContasPagRec> listAll(){
        return contasPagRecRepository.findAll();
    }
}
