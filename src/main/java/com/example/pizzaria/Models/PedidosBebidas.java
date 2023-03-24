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

import java.time.LocalDateTime;

import com.example.pizzaria.Interfaces.PedidoProduto;
import com.example.pizzaria.Interfaces.Produtos;

@Entity
@Table(name = "pedido_bebida")
public class PedidosBebidas implements PedidoProduto {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pedido_bebida_id")
    private int pedidoBebidasId;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "pedido_id")
    private Pedidos pedido;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "bebida_id")
    private Bebidas bebida;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime horaCriada;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private LocalDateTime horaModificada;

    public PedidosBebidas() {
    };

    public PedidosBebidas(Pedidos pedido, Bebidas bebida, int qty) {
        this.pedido = pedido;
        this.bebida = bebida;
        this.quantidade = qty;
    };

    public int getId() {
        return pedidoBebidasId;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos newPedido) {
        this.pedido = newPedido;
    }

    public Produtos getProduto() {
        return bebida;
    }

    public void setProduto(Produtos produto) {
        this.bebida = (Bebidas) produto;
    };

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
}
