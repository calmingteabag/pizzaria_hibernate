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
@Table(name = "pedido_sobremesa")
public class PedidosSobremesas implements PedidoProduto {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pedido_sobremesa_id")
    private int pedidoSobremesaId;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "pedido_id")
    private Pedidos pedido;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "sobremesa_id")
    private Sobremesas sobremesa;

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

    public PedidosSobremesas() {
    };

    public PedidosSobremesas(Pedidos pedido, Sobremesas sobremesa, int qty) {
        this.pedido = pedido;
        this.sobremesa = sobremesa;
        this.quantidade = qty;
    };

    public PedidosSobremesas(Pedidos pedido, Sobremesas sobremesa, int qty,
            String nomeProduto, String mapKey, int totalProduto) {
        this.pedido = pedido;
        this.sobremesa = sobremesa;
        this.quantidade = qty;
        this.nomeProduto = nomeProduto;
        this.mapKey = mapKey;
        this.totalProduto = totalProduto;
    }

    public int getId() {
        return pedidoSobremesaId;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos newPedido) {
        this.pedido = newPedido;
    }

    public void setProduto(Produtos produto) {
        this.sobremesa = (Sobremesas) produto;
    };

    public Produtos getProduto() {
        return sobremesa;
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
