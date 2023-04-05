package com.example.pizzaria.Interfaces;

public interface Produtos {
    public int getId();

    public String getNome();

    public void setNome(String novoNome);

    public String getDescricao();

    public void setDescricao(String novaDescricao);

    public int getPreco();

    public void setPreco(int novoPreco);

    public void setPedidoProduto(String pedidoProdutoKey, PedidoProduto pedidoProduto);

    public PedidoProduto getOnePedidoProduto(String pedidoKey);

}