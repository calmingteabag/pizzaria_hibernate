package com.example.pizzaria.Controllers;

import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzaria.DAO.CreatePedido;
import com.example.pizzaria.DAO.RemovePedidos;
import com.example.pizzaria.DAO.UpdatePedidos;
// import com.example.pizzaria.DAO.RemovePedidos;
// import com.example.pizzaria.DAO.UpdatePedidos;
import com.example.pizzaria.Models.Pedidos;
import com.example.pizzaria.Services.UserInputChecker;
import com.example.pizzaria.Utils.HibernateSession;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PedidosController {

    @PostMapping("/create_pedido")
    public String insertPedido() {

        CreatePedido newPedido = new CreatePedido();
        newPedido.testInsert();
        newPedido.setPedidosTotais(1, new String[] { "pizza", "bebida", "sobremesa" });

        return "blank";
    }

    @PostMapping("/update_pedido") // generalizar pra qq produto não só pizzas
    public String addOrUpdatePedidoController(
            @RequestParam String pedido_id,
            @RequestParam String tipo_produto,
            @RequestParam String nova_quantidade,
            @RequestParam String novo_nome,
            HttpServletRequest request, Model model) {

        UserInputChecker checker = new UserInputChecker();
        String status = checker.checkInput(pedido_id, tipo_produto, nova_quantidade, novo_nome);
        if (status != "ok") {
            model.addAttribute("status_message", status);
            return "teste";
        }

        UpdatePedidos update = new UpdatePedidos();
        String resposta = update.updatePedidoByName(Pedidos.class,
                tipo_produto, pedido_id, nova_quantidade, novo_nome);

        model.addAttribute("status_message", resposta);

        return "teste";
    }

    @PostMapping("/remove_itens_pedido")
    public String removeItensPedido(@RequestParam String pedido_id,
            @RequestParam String produto_id,
            @RequestParam String nome_produto, HttpServletRequest request, Model model) {

        RemovePedidos remove = new RemovePedidos();
        String resposta = remove.removeItensPedido(pedido_id,
                produto_id, nome_produto);

        model.addAttribute("status_message", resposta);

        return "teste";
    }
}
