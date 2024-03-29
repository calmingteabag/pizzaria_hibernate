package com.example.pizzaria.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Produto {

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("quantidade")
    private Integer quantidade;

    @JsonProperty("valor_unitario")
    private Integer valorUnitario;

    @JsonProperty("total_produto")
    private Integer totalProduto;

    public Produto(String newDescricao, Integer newQty,
            Integer newValorUnitario, Integer newTotalProduto) {
        this.descricao = newDescricao;
        this.quantidade = newQty;
        this.valorUnitario = newValorUnitario;
        this.totalProduto = newTotalProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String newDescricao) {
        this.descricao = newDescricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer newQuantidade) {
        this.quantidade = newQuantidade;
    }

    public Integer getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Integer newValorUnitario) {
        this.valorUnitario = newValorUnitario;
    }

    public Integer getTotalProduto() {
        return totalProduto;
    }

    public void setTotalProduto(Integer newTotalProduto) {
        this.totalProduto = newTotalProduto;
    }
}
