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
@Table(name = "pedido_sobremesa")
public class PedidosSobremesas {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pedido_sobremesa_id")
    private int pedidoSobremesaId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private Pedidos pedido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sobremesa_id")
    private Sobremesas sobremesa;

    @Column(name = "quantidade")
    private int quantidade;

    public PedidosSobremesas() {
    };

    public PedidosSobremesas(Pedidos pedido, Sobremesas sobremesa, int qty) {
        this.pedido = pedido;
        this.sobremesa = sobremesa;
        this.quantidade = qty;
    };

    public void setPedido(Pedidos newPedido) {
        this.pedido = newPedido;
    }

    public void setSobremesa(Sobremesas newSobremesa) {
        this.sobremesa = newSobremesa;
    }

    public void setQty(int newQty) {
        this.quantidade = newQty;
    }
}
