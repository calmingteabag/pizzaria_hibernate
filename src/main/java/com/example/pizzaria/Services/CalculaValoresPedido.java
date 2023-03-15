package com.example.pizzaria.Services;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

@Service
public class CalculaValoresPedido {

    public CalculaValoresPedido() {
    };

    public Integer calculaTotaisPorTipo(int pedidoId, String productType) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        int totais = 0;

        if (productType == "pizzas") {
            List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();

            for (int i = 0; i < pedidosPizzas.size(); i++) {
                PedidosPizzas currPedidoPizza = pedidosPizzas.get(i);
                int pizzaValor = currPedidoPizza.getPizza().getPreco();
                int quantidade = currPedidoPizza.getQty();
                totais += pizzaValor * quantidade;
            }
        } else if (productType == "bebidas") {
            List<PedidosBebidas> pedidosBebidas = pedido.getPedidosBebidas();

            for (int i = 0; i < pedidosBebidas.size(); i++) {
                PedidosBebidas currPedidoBebida = pedidosBebidas.get(i);
                int bebidaValor = currPedidoBebida.getBebida().getPreco();
                int quantidade = currPedidoBebida.getQty();
                totais += bebidaValor * quantidade;
            }
        } else if (productType == "sobremesas") {
            List<PedidosSobremesas> pedidosSobremesas = pedido.getPedidosSobremesas();

            for (int i = 0; i < pedidosSobremesas.size(); i++) {
                PedidosSobremesas currPedidoSobremesa = pedidosSobremesas.get(i);
                int sobremesaValor = currPedidoSobremesa.getSobremesa().getPreco();
                int quantidade = currPedidoSobremesa.getQty();
                totais += sobremesaValor * quantidade;
            }
        } else {
            return totais;
        }

        return totais;
    }

    public Integer calculaTotalGeral(int pedidoId) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        int totais = 0;

        List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();

        for (int i = 0; i < pedidosPizzas.size(); i++) {
            PedidosPizzas currPedidoPizza = pedidosPizzas.get(i);
            int pizzaValor = currPedidoPizza.getPizza().getPreco();
            int quantidade = currPedidoPizza.getQty();
            totais += pizzaValor * quantidade;
        }

        List<PedidosBebidas> pedidosBebidas = pedido.getPedidosBebidas();

        for (int i = 0; i < pedidosBebidas.size(); i++) {
            PedidosBebidas currPedidoBebida = pedidosBebidas.get(i);
            int bebidaValor = currPedidoBebida.getBebida().getPreco();
            int quantidade = currPedidoBebida.getQty();
            totais += bebidaValor * quantidade;
        }

        List<PedidosSobremesas> pedidosSobremesas = pedido.getPedidosSobremesas();

        for (int i = 0; i < pedidosSobremesas.size(); i++) {
            PedidosSobremesas currPedidoSobremesa = pedidosSobremesas.get(i);
            int sobremesaValor = currPedidoSobremesa.getSobremesa().getPreco();
            int quantidade = currPedidoSobremesa.getQty();
            totais += sobremesaValor * quantidade;
        }
        return totais;
    }
}
