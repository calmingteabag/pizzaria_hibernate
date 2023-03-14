package com.example.pizzaria.Controllers;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzaria.DAO.CreateCliente;
import com.example.pizzaria.DAO.CreatePedido;
import com.example.pizzaria.Entities.Clientes;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class InserirEntidades {

    @PostMapping("/insert_cliente")
    public String insertCliente(@RequestParam String form_nome, @RequestParam String form_sobrenome,
            HttpServletRequest request, Model model) {
        System.out.println(String.format("nome = %s e sobrenome = %s", form_nome, form_sobrenome));

        Clientes cliente = new Clientes();
        CreateCliente insert = new CreateCliente();
        String insertFeedback = insert.insertClient(cliente, "clientes", "cliente_nome", form_nome, form_sobrenome);

        model.addAttribute("nome_digitado", form_nome);
        model.addAttribute("sobrenome_digitado", form_sobrenome);
        model.addAttribute("insert_feedback", insertFeedback);
        return "blank";
    }

    @PostMapping("/insert_pedido")
    public String insertPedido(@RequestParam String form_nome, @RequestParam String form_sobrenome,
            HttpServletRequest request, Model model) {
        System.out.println(String.format("nome = %s e sobrenome = %s", form_nome, form_sobrenome));

        CreatePedido newPedido = new CreatePedido();
        newPedido.testInsert();
        return "blank";
    }

}
