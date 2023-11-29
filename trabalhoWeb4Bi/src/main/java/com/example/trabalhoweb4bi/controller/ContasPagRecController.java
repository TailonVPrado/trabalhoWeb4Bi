package com.example.trabalhoweb4bi.controller;

import com.example.trabalhoweb4bi.service.ContasPagRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/contasPagRec")
public class ContasPagRecController {
    @Autowired
    private ContasPagRecService contasPagRecService;

    @GetMapping
    public ModelAndView listarContasPagRec(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("contasPagRec/listar");

        if (model.containsAttribute("contasPagRecs"))
            modelAndView.addObject("contasPagRecs", model.getAttribute("contasPagRecs"));
        else {
            modelAndView.addObject("contasPagRec", contasPagRecService.listAll());
        }
        System.out.println(contasPagRecService.listAll().size());

        return modelAndView;
    }


    @GetMapping(path = "/filtrar")
    public String filtrarContasPagRec(@RequestParam("descricao") String descricao,
                                      @RequestParam("dataLcto") String dataLcto,
                                      RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("contasPagRecs",
                contasPagRecService.listByFilter(descricao, dataLcto));
        return "redirect:/contasPagRec";
    }
}
