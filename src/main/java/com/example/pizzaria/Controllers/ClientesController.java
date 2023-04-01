package com.example.pizzaria.Controllers;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzaria.DAO.CreateCliente;
import com.example.pizzaria.DAO.RemoveCliente;
import com.example.pizzaria.DAO.UpdateCliente;
import com.example.pizzaria.Models.Clientes;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ClientesController {

    @PostMapping("/create_cliente")
    public String createCliente(@RequestParam String form_nome, @RequestParam String form_sobrenome,
            HttpServletRequest request, Model model) {

        CreateCliente createCliente = new CreateCliente();
        String insertFeedback = createCliente.createNewCliente(Clientes.class, "clientes", "cliente_nome", form_nome,
                form_sobrenome);

        model.addAttribute("insert_feedback", insertFeedback);
        return "blank";
    }

    @PostMapping("/update_cliente")
    public String updateCliente(@RequestParam String form_nome, @RequestParam String form_sobrenome,
            @RequestParam String form_id,
            HttpServletRequest request, Model model) {

        UpdateCliente updateCliente = new UpdateCliente();
        String updateFeedback = updateCliente.updateClienteById(Clientes.class, form_id,
                form_nome,
                form_sobrenome);

        model.addAttribute("insert_feedback", updateFeedback);

        return "blank";
    }

    @PostMapping("/remove_cliente")
    public String removeCliente(@RequestParam String form_id, HttpServletRequest request, Model model) {

        RemoveCliente remove = new RemoveCliente();
        String feedback = remove.removeClientById(form_id);
        model.addAttribute("insert_feedback", feedback);

        return "blank";
    }
}
