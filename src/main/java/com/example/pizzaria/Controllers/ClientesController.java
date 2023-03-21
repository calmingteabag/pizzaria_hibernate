package com.example.pizzaria.Controllers;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzaria.DAO.CreateCliente;
import com.example.pizzaria.DAO.UpdateCliente;
import com.example.pizzaria.Entities.Clientes;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ClientesController {

    @PostMapping("/create_cliente")
    public String createCliente(@RequestParam String form_nome, @RequestParam String form_sobrenome,
            HttpServletRequest request, Model model) {

        CreateCliente createCliente = new CreateCliente();
        String insertFeedback = createCliente.createNewCliente(Clientes.class, "clientes", "cliente_nome", form_nome,
                form_sobrenome);

        // model.addAttribute("nome_digitado", form_nome);
        // model.addAttribute("sobrenome_digitado", form_sobrenome);
        model.addAttribute("insert_feedback", insertFeedback);
        return "blank";
    }

    @PostMapping("/update_cliente")
    public String updateCliente(@RequestParam String form_nome, @RequestParam String form_sobrenome,
            @RequestParam String form_id,
            HttpServletRequest request, Model model) {

        UpdateCliente createCliente = new UpdateCliente();
        String updateFeedback = createCliente.updateClienteById(Clientes.class, form_id,
                form_nome,
                form_sobrenome);

        // model.addAttribute("nome_digitado", form_nome);
        // model.addAttribute("sobrenome_digitado", form_sobrenome);
        model.addAttribute("insert_feedback", updateFeedback);
        return "blank";
    }
}
