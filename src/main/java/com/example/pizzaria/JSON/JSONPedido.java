package com.example.pizzaria.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONPedido {

    @JsonProperty("nome_cliente")
    private String nomeCliente;

    @JsonProperty("pedido")
    private JSONPedidosProdutos pedidos;

    @JsonProperty("total")
    private Integer totalPedido;

    public JSONPedido(String nome, JSONPedidosProdutos jsonPedidosProdutos, Integer total) {
        this.nomeCliente = nome;
        this.pedidos = jsonPedidosProdutos;
        this.totalPedido = total;
    }
}
