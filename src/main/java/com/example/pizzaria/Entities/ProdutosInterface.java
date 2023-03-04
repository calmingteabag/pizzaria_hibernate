package com.example.pizzaria.Entities;

public interface ProdutosInterface {
    public void setNome(String novoNome);

    public void setDescricao(String novaDescricao);

    public void setPreco(String novoPreco);

    public String getNome();

    public String getDescricao();

    public int getPreco();
}