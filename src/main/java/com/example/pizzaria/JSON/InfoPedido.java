package com.example.pizzaria.JSON;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoPedido {

    @JsonProperty("id_pedido")
    private int idPedido;

    @JsonProperty("produtos")
    private Map<String, Produto> produtos;

    @JsonProperty("total_parcial")
    private Integer totalParcial;

    @JsonProperty("descontos")
    private Integer descontos;

    @JsonProperty("total_final")
    private Integer totalFinal;

    public InfoPedido() {
    }

    public InfoPedido(Map<String, Produto> newProdutos,
            Integer newTotalParcial, Integer newDesconto, Integer newTotalFinal) {
        this.produtos = newProdutos;
        this.totalParcial = newTotalParcial;
        this.descontos = newDesconto;
        this.totalFinal = newTotalFinal;
    };

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Map<String, Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Map<String, Produto> newProdutos) {
        this.produtos = newProdutos;
    }

    public Integer getTotalParcial() {
        return totalParcial;
    }

    public void setTotalParcial(Integer newTotalParcial) {
        this.totalParcial = newTotalParcial;
    }

    public Integer getDesconto() {
        return descontos;
    }

    public void setDesconto(Integer newDesconto) {
        this.descontos = newDesconto;
    }

    public Integer getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(Integer newTotalFinal) {
        this.totalFinal = newTotalFinal;
    }
}