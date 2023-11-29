package com.example.trabalhoweb4bi.controller;

import com.example.trabalhoweb4bi.service.ContasPagRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/contasPagRec")
public class ContasPagRecController {
    @Autowired
    private ContasPagRecService contasPagRecService;

    @GetMapping
    public ModelAndView listaCategorias(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("contasPagRec/listar");

        if (model.containsAttribute("contasPagRec"))
            modelAndView.addObject("contasPagRec", model.getAttribute("contasPagRec"));
        else {
            modelAndView.addObject("contasPagRec", contasPagRecService.listAll());
        }

        return modelAndView;
    }
}
