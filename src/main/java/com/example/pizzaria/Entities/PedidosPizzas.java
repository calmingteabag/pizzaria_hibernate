package com.example.pizzaria.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pedido_pizza")
public class PedidosPizzas {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pedido_bebida_id")
    private int pedidoPizzaId;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedidos pedido;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizzas pizza;

    @Column(name = "quantidade")
    private int quantidade;

    public PedidosPizzas() {
    };

    public PedidosPizzas(Pedidos pedido, Pizzas pizza, int qty) {
        this.pedido = pedido;
        this.pizza = pizza;
        this.quantidade = qty;
    };

    public void setPedido(Pedidos newPedido) {
        this.pedido = newPedido;
    }

    public void setPizza(Pizzas newPizza) {
        this.pizza = newPizza;
    }

    public void setQty(int newQty) {
        this.quantidade = newQty;
    }
}
