package com.example.pizzaria.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.pizzaria.Interfaces.PedidoProduto;
import com.example.pizzaria.Interfaces.Produtos;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedido_pizza")
public class PedidosPizzas implements PedidoProduto {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pedido_pizza_id")
    private int pedidoPizzaId;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "pedido_id")
    private Pedidos pedido;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "pizza_id")
    private Pizzas pizza;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "map_key")
    private String mapKey;

    @Column(name = "total_produto")
    private int totalProduto;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime horaCriada;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private LocalDateTime horaModificada;

    public PedidosPizzas() {
    };

    public PedidosPizzas(Pedidos pedido, Pizzas pizza, int qty) {
        this.pedido = pedido;
        this.pizza = pizza;
        this.quantidade = qty;
    };

    public PedidosPizzas(Pedidos pedido, Pizzas pizza, int qty,
            String nomeProduto, String mapKey, int totalProduto) {
        this.pedido = pedido;
        this.pizza = pizza;
        this.quantidade = qty;
        this.nomeProduto = nomeProduto;
        this.mapKey = mapKey;
        this.totalProduto = totalProduto;
    }

    public int getId() {
        return pedidoPizzaId;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public void setProduto(Produtos produto) {
        this.pizza = (Pizzas) produto;
    };

    public Produtos getProduto() {
        return pizza;
    }

    public int getQty() {
        return quantidade;
    }

    public void setQty(int newQty) {
        this.quantidade = newQty;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String produto) {
        this.nomeProduto = produto;
    }

    public int getTotalProduto() {
        return totalProduto;
    }

    public void setTotalProduto(int newTotalProduto) {
        this.totalProduto = newTotalProduto;
    }

    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

}
