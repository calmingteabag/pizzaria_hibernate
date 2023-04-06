package com.example.pizzaria.Services;

import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.example.pizzaria.Interfaces.PedidoProduto;
import com.example.pizzaria.Models.Pedidos;
import com.example.pizzaria.Utils.HibernateSession;

// Existem 3 categorias de produtos, então fiz dois métodos, um para
// retornar o total por categoria e outro para retornar o total.
@Service
public class CalculaValoresPedido {

    public CalculaValoresPedido() {
    };

    public Integer calculaTotaisPorTipo(int pedidoId, String productType) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        int totalTipoProduto = 0;

        Map<String, ?> produtos = pedido.getAllPedidoProduto(productType);
        for (Map.Entry<String, ?> entry : produtos.entrySet()) {
            PedidoProduto currProduto = (PedidoProduto) entry.getValue();

            int currPrice = currProduto.getProduto().getPreco();
            int currQty = currProduto.getQty();
            int totalProduto = currPrice * currQty;
            totalTipoProduto += totalProduto;
        }
        return totalTipoProduto;
    }

    public Integer calculaTotalGeral(int pedidoId, String[] productList) {
        int totalPedido = 0;

        for (int i = 0; i < productList.length; i++) {
            int totalProdutos = calculaTotaisPorTipo(pedidoId, productList[i]);
            totalPedido += totalProdutos;
        }

        return totalPedido;
    }
}
