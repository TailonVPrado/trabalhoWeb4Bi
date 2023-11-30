package com.example.trabalhoweb4bi.controller;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.domain.ContasPagRec;
import com.example.trabalhoweb4bi.service.CategoriaService;
import com.example.trabalhoweb4bi.service.ContasPagRecService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/contasPagRec")
public class ContasPagRecController {

    @Autowired
    private ContasPagRecService contasPagRecService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listarContasPagRec(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("contasPagRec/listar");

        if (model.containsAttribute("contasPagRecs"))
            modelAndView.addObject("contasPagRecs", model.getAttribute("contasPagRecs"));
        else {
            modelAndView.addObject("contasPagRecs", contasPagRecService.listAll());
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

    @GetMapping(path = "/criar")
    public ModelAndView retornaNovaContasPagRec(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("contasPagRec/inserir");

        if (model.containsAttribute("contasPagRec")) {
            modelAndView.addObject("contasPagRec", model.getAttribute("contasPagRec"));

            modelAndView.addObject("msg", model.getAttribute("msg"));

        } else {
            modelAndView.addObject("contasPagRec", new ContasPagRec());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        modelAndView.addObject("categorias", categoriaService.listAll());
        System.out.println(categoriaService.listAll().size());
        return modelAndView;
    }

    @PostMapping
    public String salvarcontasPagRec(@Valid ContasPagRec contasPagRec,
                                     @RequestParam("idCategoria") Long idCategoria,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes){


        List<String> msg = new ArrayList<>();
        if(idCategoria > 0){
            contasPagRec.setCategoria(categoriaService.findById(idCategoria));
        }
        msg.addAll(contasPagRecService.validate(contasPagRec));

        if (bindingResult.hasErrors() || !msg.isEmpty()) {
            redirectAttributes.addFlashAttribute("contasPagRec", contasPagRec);


            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage()); // vem das anotações @NotEmpty e outras
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/contasPagRec/criar";
        }

        contasPagRecService.insert(contasPagRec);

        return "redirect:/contasPagRec";
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarConstasPagRec(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("contasPagRec/inserir");
        modelAndView.addObject("contasPagRec", contasPagRecService.findById(id));
        modelAndView.addObject("categorias", categoriaService.listAll());
        return modelAndView;
    }

    @GetMapping(path = "/remover/{id}")
    public String removercontasPagRec(@PathVariable("id") Long id){
        contasPagRecService.delete(id);
        return "redirect:/contasPagRec";
    }
}
