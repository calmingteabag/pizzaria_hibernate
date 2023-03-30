package com.example.pizzaria.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pedido {

    @JsonProperty("nome_cliente")
    private String nomeCliente;

    @JsonProperty("info_pedido")
    private InfoPedido infoPedido;

    public Pedido(String nome, InfoPedido pedido, Integer total) {
        this.nomeCliente = nome;
        this.infoPedido = pedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nome) {
        this.nomeCliente = nome;
    }

    public InfoPedido getInfoPedido() {
        return infoPedido;
    }

    public void setInfoPedid(InfoPedido newInfoPedido) {
        this.infoPedido = newInfoPedido;
    }
}
