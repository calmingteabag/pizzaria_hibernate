package com.example.pizzaria.Services;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

import org.springframework.stereotype.Service;

import com.example.pizzaria.JSON.*;
import com.example.pizzaria.Models.Pedidos;
import com.example.pizzaria.Utils.HibernateSession;

@Service
public class GenerateJSONObject {

    public GenerateJSONObject() {
    }

    public JSONPedido generateJSONPedido(int pedidoId, String[] productList) {
        // productList = {"pizza", "bebida", "sobremesa", etc}
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        Map<String, Produto> mapPedido = new HashMap<String, Produto>();
        GenerateMapProdutos generator = new GenerateMapProdutos();

        for (int i = 0; i < productList.length; i++) {
            Map<String, ?> produto = pedido.getAllPedidoProduto(productList[i]);
            Map<String, Produto> produtoMap = generator.generateMap(produto);
            mapPedido.putAll(produtoMap);
        }

        JSONPedido pedidoJSON = new JSONPedido();
        InfoCliente infoCliente = new InfoCliente();
        InfoPedido infoPedido = new InfoPedido();

        infoCliente.setIdCliente(pedido.getCliente().getId());
        infoCliente.setNomeCliente(pedido.getCliente().getNome());

        infoPedido.setIdPedido(pedido.getId());
        infoPedido.setProdutos(mapPedido);
        infoPedido.setDesconto(pedido.getDesconto());
        infoPedido.setTotalParcial(pedido.getTotalParcial());
        infoPedido.setTotalFinal(pedido.getTotalFinal());

        pedidoJSON.setInfoCliente(infoCliente);
        pedidoJSON.setInfoPedido(infoPedido);

        return pedidoJSON;
    }
}
