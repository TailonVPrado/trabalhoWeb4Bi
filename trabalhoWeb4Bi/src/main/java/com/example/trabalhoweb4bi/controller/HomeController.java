package com.example.trabalhoweb4bi.controller;

import com.example.trabalhoweb4bi.enums.TipoConta;
import com.example.trabalhoweb4bi.service.CategoriaService;
import com.example.trabalhoweb4bi.service.ContasPagRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ContasPagRecService contasPagRecService;

    @GetMapping
    public ModelAndView retornaHome() {
        ModelAndView modelAndView = new ModelAndView("index");

        BigDecimal totalDespesa = contasPagRecService.retornaSomaContas(TipoConta.DESPESA);
        BigDecimal totalReceita =  contasPagRecService.retornaSomaContas(TipoConta.RECEITA);

        modelAndView.addObject("totalDespesa", totalDespesa != null ? totalDespesa : 0);
        modelAndView.addObject("totalReceita", totalReceita != null ? totalReceita : 0);
        modelAndView.addObject("ultimas10ContasLancadas", contasPagRecService.retornaUltimas10ContasLancadas());

        return modelAndView;
    }
}
