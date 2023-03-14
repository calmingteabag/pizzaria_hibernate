package com.example.pizzaria.JSON;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONPedidosDetalhes {
    @JsonProperty("total")
    private Integer produtosTotais;

    @JsonProperty("produtos")
    private ArrayList<?> nomesProdutos;

    @JsonProperty("quantidades")
    private ArrayList<?> quantidadeProdutos;

    @JsonProperty("preço_unitario")
    private ArrayList<?> precoUnitarioProdutos;

    public JSONPedidosDetalhes(Integer totalProdutos, ArrayList<?> quantidadeProdutos,
            ArrayList<?> insertPizzas, ArrayList<?> insertPrecoUnitario) {
        this.produtosTotais = totalProdutos;
        this.nomesProdutos = insertPizzas;
        this.quantidadeProdutos = quantidadeProdutos;
        this.precoUnitarioProdutos = insertPrecoUnitario;
    }
}
