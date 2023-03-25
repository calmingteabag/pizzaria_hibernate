package com.example.pizzaria.Controllers;

import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzaria.DAO.CreatePedido;
import com.example.pizzaria.DAO.RemovePedidos;
import com.example.pizzaria.DAO.UpdatePedidos;
// import com.example.pizzaria.DAO.RemovePedidos;
// import com.example.pizzaria.DAO.UpdatePedidos;
import com.example.pizzaria.Models.Pedidos;

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

        HashSet<String> setProdutos = new HashSet<>(Arrays.asList("pizza", "bebida",
                "sobremesa"));
        String tipoProduto = tipo_produto.toLowerCase();

        if (setProdutos.contains(tipoProduto) == false) {

            model.addAttribute("status_message",
                    "Produto inexistente. Use \"pizzas\", \"bebidas\" ou \"sobremesas\" (semaspas) ");
            return "teste";
        }

        UpdatePedidos update = new UpdatePedidos();
        String resposta = update.updatePedidoByName(Pedidos.class, tipo_produto,
                pedido_id, nova_quantidade,
                novo_nome);

        model.addAttribute("status_message", resposta);

        return "teste";
    }

    // @PostMapping("/remove_pedido_total")
    // public String deletePedidoTotal() {

    // }

    @PostMapping("/remove_itens_pedido")
    public String deleteItensPedido(@RequestParam String pedido_id,
            @RequestParam String tipo_produto,
            @RequestParam String nome_produto, HttpServletRequest request, Model model) {

        RemovePedidos remove = new RemovePedidos();
        String resposta = remove.removeItensPedido(tipo_produto, pedido_id,
                nome_produto);

        model.addAttribute("status_message", resposta);

        return "teste";
    }
}
