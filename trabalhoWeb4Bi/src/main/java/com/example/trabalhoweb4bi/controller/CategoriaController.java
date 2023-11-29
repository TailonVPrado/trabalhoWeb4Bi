package com.example.trabalhoweb4bi.controller;

import com.example.trabalhoweb4bi.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listaCategorias(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("categoria/listar");

        if (model.containsAttribute("categoria"))
            modelAndView.addObject("categoria", model.getAttribute("categoria"));
        else {
            modelAndView.addObject("categoria", categoriaService.listAll());
        }

        return modelAndView;
    }
}
