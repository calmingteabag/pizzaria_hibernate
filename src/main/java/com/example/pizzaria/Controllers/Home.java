package com.example.pizzaria.Controllers;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

    @RequestMapping("/")
    public String home(Model model) {

        return "teste";
    }
}
