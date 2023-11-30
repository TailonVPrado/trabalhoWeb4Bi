package com.example.trabalhoweb4bi.controller;

import com.example.trabalhoweb4bi.domain.Categoria;
import com.example.trabalhoweb4bi.enums.TipoConta;
import com.example.trabalhoweb4bi.service.CategoriaService;
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
@RequestMapping(path = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listaCategorias(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("categoria/listar");

        if (model.containsAttribute("categorias"))
            modelAndView.addObject("categorias", model.getAttribute("categorias"));
        else {
            modelAndView.addObject("categorias", categoriaService.listAll());
        }

        return modelAndView;
    }

    @GetMapping(path = "/filtrar")
    public String filtrarCategorias(@RequestParam("descricao") String descricao,
                                    @RequestParam("tipoConta") String tipoConta,
                                     RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("categorias",
                categoriaService.listByFilter(descricao, tipoConta));
        System.out.println(categoriaService.listByFilter(descricao, tipoConta).size());
        return "redirect:/categoria";
    }


    @PostMapping
    public String salvarDisciplina(@Valid Categoria categoria,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        List<String> msg = new ArrayList<>();
        msg.addAll(categoriaService.validate(categoria));

        if (bindingResult.hasErrors() || !msg.isEmpty()) {
            redirectAttributes.addFlashAttribute("categoria", categoria);

            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage()); // vem das anotações @NotEmpty e outras
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/categoria/criar";
        }

        categoriaService.insert(categoria);

        return "redirect:/categoria";
    }

    @GetMapping(path = "/criar")
    public ModelAndView retornaNovaCategoria(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("categoria/inserir");

        if (model.containsAttribute("categoria")) {
            modelAndView.addObject("categoria", model.getAttribute("categoria"));
            modelAndView.addObject("msg", model.getAttribute("msg"));

        } else {
            modelAndView.addObject("categoria", new Categoria());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        return modelAndView;
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarCategoria(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("categoria/inserir");
        modelAndView.addObject("categoria", categoriaService.findById(id));
        return modelAndView;
    }

    @GetMapping(path = "/remover/{id}")
    public String removerCategoria(@PathVariable("id") Long id){
        categoriaService.delete(id);
        return "redirect:/categoria";
    }
}
