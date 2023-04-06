package com.example.pizzaria.Controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzaria.DAO.CreatePedido;
import com.example.pizzaria.DAO.RemovePedidos;
import com.example.pizzaria.DAO.UpdatePedidos;
import com.example.pizzaria.Models.Bebidas;
import com.example.pizzaria.Models.Pedidos;
import com.example.pizzaria.Models.Pizzas;
import com.example.pizzaria.Services.AddProductToPedido;
import com.example.pizzaria.Services.UserInputChecker;

import jakarta.servlet.http.HttpServletRequest;
// Notei que fundamentalmente add_itens_pedido e update_pedido teoricamente fazem
// a mesma coisa. A diferença é que a idéia original do primeiro é ir montando o
// pedido aos poucos (como um cliente que vai adicionando os itens na cesta de
// compras) e o último, seria algo como ter um pedido pronto e modificá-lo antes
// de finalizar a compra.

@Controller
public class PedidosController {

    @PostMapping("/create_pedido")
    public String createPedido(@RequestParam String id_cliente, Model model) {

        CreatePedido newPedido = new CreatePedido();
        String status = newPedido.createNewPedido(id_cliente);

        model.addAttribute("status_message", status);
        return "teste";
    }

    @PostMapping("/add_itens_pedido")
    public String addItensPedido(@RequestParam String tipo_produto,
            @RequestParam String produto, @RequestParam String quantidade,
            @RequestParam String id_cliente, @RequestParam String id_pedido,
            Model model) {

        AddProductToPedido addProduct = new AddProductToPedido();
        String respostaHtml = "";

        switch (tipo_produto) {
            case "pizza":
                respostaHtml = addProduct.addProduct("pizzas", "nome", produto,
                        Pizzas.class, id_pedido, id_cliente,
                        quantidade, tipo_produto);
                break;

            case "bebida":
                respostaHtml = addProduct.addProduct("bebidas", "nome", produto,
                        Bebidas.class, id_pedido, id_cliente,
                        quantidade, tipo_produto);
                break;
            case "sobremesa":
                respostaHtml = addProduct.addProduct("sobremesas", "nome", produto,
                        Bebidas.class, id_pedido, id_cliente,
                        quantidade, tipo_produto);
                break;
        }

        model.addAttribute("status_message", respostaHtml);
        return "teste";
    }

    @PostMapping("/update_pedido")
    public String updatePedido(
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
