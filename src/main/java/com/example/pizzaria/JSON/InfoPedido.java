package com.example.pizzaria.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoPedido {

    @JsonProperty("produto_nome")
    private Produto produtoNome;

    @JsonProperty("total_parcial")
    private Integer totalParcial;

    @JsonProperty("descontos")
    private Integer descontos;

    @JsonProperty("total_pedido")
    private Integer totalPedido;

    public InfoPedido(Produto newprodutoNome,
            Integer newTotalParcial, Integer newDesconto, Integer newTotalPedido) {
        this.produtoNome = newprodutoNome;
        this.totalParcial = newTotalParcial;
        this.descontos = newDesconto;
        this.totalPedido = newTotalPedido;
    };

    public Produto getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(Produto jsonProdutosDetalhes) {
        this.produtoNome = jsonProdutosDetalhes;
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

    public Integer getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Integer newTotalPedido) {
        this.totalPedido = newTotalPedido;
    }
}