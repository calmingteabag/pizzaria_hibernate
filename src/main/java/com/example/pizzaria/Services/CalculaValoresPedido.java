package com.example.pizzaria.Services;

import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.example.pizzaria.Models.Pedidos;
import com.example.pizzaria.Models.PedidosBebidas;
import com.example.pizzaria.Models.PedidosPizzas;
import com.example.pizzaria.Models.PedidosSobremesas;
import com.example.pizzaria.Utils.HibernateSession;

@Service
public class CalculaValoresPedido {

    public CalculaValoresPedido() {
    };

    public Integer calculaTotaisPorTipo(int pedidoId, String productType) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        int totalTipoProduto = 0;

        if (productType == "pizzas") {
            Map<String, ?> pedidosPizzas = pedido.getAllPedidoProduto(productType);

            for (Map.Entry<String, ?> entry : pedidosPizzas.entrySet()) {
                PedidosPizzas currPedidoPizza = (PedidosPizzas) entry.getValue();

                int currPrice = currPedidoPizza.getProduto().getPreco();
                int currQty = currPedidoPizza.getQty();
                int totalProduto = currPrice * currQty;

                totalTipoProduto += totalProduto;
            }
            return totalTipoProduto;

        } else if (productType == "bebidas") {
            Map<String, ?> pedidosBebidas = pedido.getAllPedidoProduto(productType);

            for (Map.Entry<String, ?> entry : pedidosBebidas.entrySet()) {
                PedidosBebidas currPedidoBebida = (PedidosBebidas) entry.getValue();

                int currPrice = currPedidoBebida.getProduto().getPreco();
                int currQty = currPedidoBebida.getQty();
                int totalProduto = currPrice * currQty;

                totalTipoProduto += totalProduto;
            }

            return totalTipoProduto;

        } else if (productType == "sobremesas") {
            Map<String, ?> pedidosSobremesas = pedido.getAllPedidoProduto(productType);

            for (Map.Entry<String, ?> entry : pedidosSobremesas.entrySet()) {
                PedidosSobremesas currPedidoSobremesa = (PedidosSobremesas) entry.getValue();

                int currPrice = currPedidoSobremesa.getProduto().getPreco();
                int currQty = currPedidoSobremesa.getQty();
                int totalProduto = currPrice * currQty;

                totalTipoProduto += totalProduto;
            }

            return totalTipoProduto;
        } else {
            return totalTipoProduto;
        }

    }

    public Integer calculaTotalGeral(int pedidoId) {
        int totalPedido = 0;

        int totalPizzas = calculaTotaisPorTipo(pedidoId, "pizza");
        int totalBebidas = calculaTotaisPorTipo(pedidoId, "bebida");
        int totalSobremesas = calculaTotaisPorTipo(pedidoId, "sobremesa");

        totalPedido = totalPizzas + totalBebidas + totalSobremesas;

        return totalPedido;
    }
}
