package com.example.pizzaria.Entities;

public interface ProdutosInterface {
    public void setNome(String novoNome);

    public String getNome();

    public void setDescricao(String novaDescricao);

    public String getDescricao();

    public void setPreco(int novoPreco);

    public int getPreco();
}