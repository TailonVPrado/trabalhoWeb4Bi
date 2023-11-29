package com.example.trabalhoweb4bi.controller;

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
    private CategoriaService categoriaService;
    @Autowired
    private ContasPagRecService contasPagRecService;

    @GetMapping
    public ModelAndView retornaHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        /*modelAndView.addObject("totalProf", professorService.listAll().size());
        modelAndView.addObject("totalDisciplinas", disciplinaService.listAll().size());
*/
        return modelAndView;
    }
}
