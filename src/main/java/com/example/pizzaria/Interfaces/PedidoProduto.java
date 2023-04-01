package com.example.pizzaria.Interfaces;

import com.example.pizzaria.Models.Pedidos;

public interface PedidoProduto {
    public int getId();

    public Pedidos getPedido();

    public void setPedido(Pedidos pedido);

    public void setProduto(Produtos produto);

    public Produtos getProduto();

    public void setQty(int newQty);

    public int getQty();

    public String getNomeProduto();

    public void setNomeProduto(String produto);

    public int getTotalProduto();
}
