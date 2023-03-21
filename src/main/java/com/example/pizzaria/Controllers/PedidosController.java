package com.example.pizzaria.Controllers;

import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzaria.DAO.UpdatePedidos;
import com.example.pizzaria.Entities.Pedidos;
import com.example.pizzaria.Services.CreatePedido;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PedidosController {

    @PostMapping("/create_pedido")
    public String insertPedido() {

        CreatePedido newPedido = new CreatePedido();
        newPedido.testInsert();

        return "blank";
    }

    @PostMapping("/update_pedido") // generalizar pra qq produto não só pizzas
    public String addOrUpdatePedidoController(
            @RequestParam String pedido_id,
            @RequestParam String tipo_produto,
            @RequestParam String nova_quantidade,
            @RequestParam String novo_nome,
            HttpServletRequest request, Model model) {

        HashSet<String> setProdutos = new HashSet<>(Arrays.asList("pizza", "bebida", "sobremesa"));
        String tipoProduto = tipo_produto.toLowerCase();

        // check to see if pedido_id is a integer, and it existis in dB
        // if ( ) { // msg to html}

        if (setProdutos.contains(tipoProduto) == false) {

            model.addAttribute("status_message",
                    "Produto inexistente. Use \"pizzas\", \"bebidas\" ou \"sobremesas\" (sem aspas) ");
            return "teste";
        }

        UpdatePedidos update = new UpdatePedidos();
        String resposta = update.updatePedidoByName(Pedidos.class, tipo_produto, pedido_id, nova_quantidade,
                novo_nome);

        model.addAttribute("status_message", resposta);

        return "teste";
    }

    // @PostMapping("/remove_pedido_total")
    // public String deletePedidoTotal() {

    // }

    // @PostMapping("remove_items_pedido")
    // public String deleteItensPedido() {
    // }
}
