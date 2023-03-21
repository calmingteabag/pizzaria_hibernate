package com.example.pizzaria.Entities;

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

import java.time.LocalDateTime;

import com.example.pizzaria.Interfaces.PedidosProdutosInterface;

@Entity
@Table(name = "pedido_pizza")
public class PedidosPizzas implements PedidosProdutosInterface {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pedido_pizza_id")
    private int pedidoPizzaId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private Pedidos pedido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pizza_id")
    private Pizzas pizza;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "nome_pizza")
    private String nomePizza;

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

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos newPedido) {
        this.pedido = newPedido;
    }

    public Pizzas getPizza() {
        return pizza;
    }

    public void setPizza(Pizzas newPizza) {
        this.pizza = newPizza;
    }

    public int getQty() {
        return quantidade;
    }

    public void setQty(int newQty) {
        this.quantidade = newQty;
    }

    public Object getProduto() {
        return pizza;
    }

    public void setNomePizza(String nomePizza) {
        this.nomePizza = nomePizza;
    }

    public String getNomePizza() {
        return nomePizza;
    }
}
