package com.example.pizzaria.Models;

import com.example.pizzaria.Interfaces.PedidoProduto;
import com.example.pizzaria.Interfaces.Produtos;

import java.util.Map;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "bebidas")
public class Bebidas implements Produtos {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "bebida_id")
    private int bebidaId;

    @Column(name = "nome", unique = true)
    private String bebidaNome;

    @Column(name = "descricao")
    private String bebidaDescricao;

    @Column(name = "preco")
    private int bebidaPreco;

    @OneToMany(mappedBy = "bebida", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @Column(name = "pedidos_bebidas")
    private Map<String, PedidoProduto> pedidoBebida;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime horaCriada;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private LocalDateTime horaModificada;

    public Bebidas() {
    };

    public Bebidas(String bebidasNome, String bebidasDescricao, int bebidasPreco) {
        this.bebidaNome = bebidasNome;
        this.bebidaDescricao = bebidasDescricao;
        this.bebidaPreco = bebidasPreco;
    }

    public int getId() {
        return bebidaId;
    };

    public String getNome() {
        return bebidaNome;
    };

    public void setNome(String novoNome) {
        this.bebidaNome = novoNome;
    };

    public String getDescricao() {
        return bebidaDescricao;
    }

    public void setDescricao(String novaDescricao) {
        this.bebidaDescricao = novaDescricao;
    }

    public int getPreco() {
        return bebidaPreco;
    }

    public void setPreco(int novoPreco) {
        this.bebidaPreco = novoPreco;
    }

    public Map<String, PedidoProduto> getAllPedidos() {
        return pedidoBebida;
    }

    public void setAllPedidos(Map<String, PedidoProduto> newPedidos) {
        this.pedidoBebida = (Map<String, PedidoProduto>) newPedidos;
    }

    public PedidoProduto getOnePedido(String pedidoKey) {
        return pedidoBebida.get(pedidoKey);
    }

    public void setOnePedido(String pedidoKey, PedidoProduto pedidoProduto) {
        pedidoBebida.put(pedidoKey, pedidoProduto);
    }

    public void removeOnePedido(String pedidoKey) {
        pedidoBebida.remove(pedidoKey);
    }
}
