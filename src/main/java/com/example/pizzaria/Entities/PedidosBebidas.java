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

@Entity
@Table(name = "pedido_bebida")
public class PedidosBebidas {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pedido_bebida_id")
    private int pedidoBebidasId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private Pedidos pedido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bebida_id")
    private Bebidas bebida;

    @Column(name = "quantidade")
    private int quantidade;

    public PedidosBebidas() {
    };

    public PedidosBebidas(Pedidos pedido, Bebidas bebida, int qty) {
        this.pedido = pedido;
        this.bebida = bebida;
        this.quantidade = qty;
    };

    public void setPedido(Pedidos newPedido) {
        this.pedido = newPedido;
    }

    public void setBebida(Bebidas newBebida) {
        this.bebida = newBebida;
    }

    public void setQty(int newQty) {
        this.quantidade = newQty;
    }
}
