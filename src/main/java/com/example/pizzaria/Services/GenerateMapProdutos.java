package com.example.pizzaria.Services;

import java.util.HashMap;
import java.util.Map;

import com.example.pizzaria.Interfaces.PedidoProduto;
import com.example.pizzaria.JSON.Produto;

public class GenerateMapProdutos {

    public GenerateMapProdutos() {
    }

    public Map<String, Produto> generateMap(Map<String, ?> mapProduto) {

        Map<String, Produto> map = new HashMap<>();

        for (Map.Entry<String, ?> entry : mapProduto.entrySet()) {
            PedidoProduto pedidoPizza = (PedidoProduto) entry.getValue();
            String nome = pedidoPizza.getNomeProduto();

            String descricao = pedidoPizza.getProduto().getDescricao();
            int quantidade = pedidoPizza.getQty();
            int valorUnitario = pedidoPizza.getProduto().getPreco();
            int totalProduto = pedidoPizza.getTotalProduto();

            Produto currProduto = new Produto(descricao, quantidade, valorUnitario, totalProduto);

            map.put(nome, currProduto);
        }

        return map;
    }
}