package com.example.trabalhoweb4bi.controller;

import com.example.trabalhoweb4bi.enums.TipoConta;
import com.example.trabalhoweb4bi.service.CategoriaService;
import com.example.trabalhoweb4bi.service.ContasPagRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ContasPagRecService contasPagRecService;

    @GetMapping
    public ModelAndView retornaHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("totalDespesa", contasPagRecService.retornaSomaContas(TipoConta.DESPESA));
        modelAndView.addObject("totalReceita", contasPagRecService.retornaSomaContas(TipoConta.RECEITA));
        modelAndView.addObject("ultimas10ContasLancadas", contasPagRecService.retornaUltimas10ContasLancadas());

        return modelAndView;
    }
}
