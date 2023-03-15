package com.example.pizzaria.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzaria.DAO.BuscaPedidoAtributos;
import com.example.pizzaria.JSON.*;

@Service
public class GenerateJSONObject {

    @Autowired
    private BuscaPedidoAtributos buscaAtributos;

    @Autowired
    private CalculaValoresPedido calculoPedido;

    public GenerateJSONObject() {
    }

    public JSONPedido generateJSONPedido(int pedidoId) {
        // nome cliente
        String nomeCliente = buscaAtributos.getClienteNome(pedidoId);

        // total geral
        Integer totalGeral = calculoPedido.calculaTotalGeral(pedidoId);

        // Pizzas
        // List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();
        ArrayList<?> insertPizzas = buscaAtributos.getPedidosNames(pedidoId, "pizzas");
        ArrayList<?> insertQuantidadesPizzas = buscaAtributos.getPrecoUnitario(pedidoId, "pizzas");
        ArrayList<?> insertPrecoUnitarioPizzas = buscaAtributos.getPedidosPrecos(pedidoId, "pizzas");
        Integer totalProdutoPizzas = calculoPedido.calculaTotaisPorTipo(pedidoId, "pizzas");

        JSONPedidosDetalhes detalhesPizzas = new JSONPedidosDetalhes(totalProdutoPizzas,
                insertQuantidadesPizzas,
                insertPizzas,
                insertPrecoUnitarioPizzas);

        // Bebidas
        // List<PedidosBebidas> pedidosBebidas = pedido.getPedidosBebidas();
        ArrayList<?> insertBebidas = buscaAtributos.getPedidosNames(pedidoId, "bebidas");
        ArrayList<?> insertQuantidadesBebidas = buscaAtributos.getPrecoUnitario(pedidoId, "bebidas");
        ArrayList<?> insertPrecoUnitarioBebidas = buscaAtributos.getPedidosPrecos(pedidoId, "bebidas");
        Integer totalProdutoBebidas = calculoPedido.calculaTotaisPorTipo(pedidoId, "bebidas");

        JSONPedidosDetalhes detalhesBebidas = new JSONPedidosDetalhes(totalProdutoBebidas,
                insertQuantidadesBebidas,
                insertBebidas,
                insertPrecoUnitarioBebidas);

        // Sobremesas
        // List<PedidosSobremesas> pedidosSobremesas = pedido.getPedidosSobremesas();
        ArrayList<?> insertSobremesas = buscaAtributos.getPedidosNames(pedidoId, "sobremesas");
        ArrayList<?> insertQuantidadesSobremesas = buscaAtributos.getPrecoUnitario(pedidoId, "sobremesas");
        ArrayList<?> insertPrecoUnitarioSobremesas = buscaAtributos.getPedidosPrecos(pedidoId, "sobremesas");
        Integer totalProdutoSobremesas = calculoPedido.calculaTotaisPorTipo(pedidoId, "sobremesas");

        JSONPedidosDetalhes detalhesSobremesas = new JSONPedidosDetalhes(totalProdutoSobremesas,
                insertQuantidadesSobremesas, insertSobremesas,
                insertPrecoUnitarioSobremesas);

        JSONPedidosProdutos produtos = new JSONPedidosProdutos(detalhesPizzas, detalhesBebidas,
                detalhesSobremesas);

        JSONPedido jsonPedido = new JSONPedido(nomeCliente, produtos, totalGeral);

        return jsonPedido;
    }
}
