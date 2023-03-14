package com.example.pizzaria.Controllers;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Entrada {

    @RequestMapping("/")
    public String home(Model model) {

        String teste_1 = "É o Tchan!";
        model.addAttribute("teste_1", teste_1);
        return "teste";
    }
}
