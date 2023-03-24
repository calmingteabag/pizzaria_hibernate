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
@Table(name = "pizzas")
public class Pizzas implements Produtos {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pizza_id")
    private int pizzaId;

    @Column(name = "nome")
    private String pizzaNome;

    @Column(name = "descricao", unique = true)
    private String pizzaDescricao;

    @Column(name = "preco")
    private int pizzaPreco;

    @OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @Column(name = "pedidos_pizzas")
    private Map<String, PedidoProduto> pedidoPizza;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime horaCriada;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private LocalDateTime horaModificada;

    public Pizzas() {
    };

    public Pizzas(String pizzaNome, String pizzaDescricao, int pizzaPreco) {
        this.pizzaNome = pizzaNome;
        this.pizzaDescricao = pizzaDescricao;
        this.pizzaPreco = pizzaPreco;
    }

    public int getId() {
        return pizzaId;
    };

    public String getNome() {
        return pizzaNome;
    };

    public void setNome(String novoNome) {
        this.pizzaNome = novoNome;
    };

    public String getDescricao() {
        return pizzaDescricao;
    }

    public void setDescricao(String novaDescricao) {
        this.pizzaDescricao = novaDescricao;
    }

    public int getPreco() {
        return pizzaPreco;
    }

    public void setPreco(int novoPreco) {
        this.pizzaPreco = novoPreco;
    }

    public Map<String, PedidoProduto> getAllPedidos() {
        return pedidoPizza;
    }

    public void setAllPedidos(Map<String, PedidoProduto> newPedidos) {
        this.pedidoPizza = (Map<String, PedidoProduto>) newPedidos;
    }

    public PedidoProduto getOnePedido(String pedidoKey) {
        return pedidoPizza.get(pedidoKey);
    }

    public void setOnePedido(String pedidoKey, PedidoProduto pedidoProduto) {
        pedidoPizza.put(pedidoKey, pedidoProduto);
    }

    public void removeOnePedido(String pedidoKey) {
        pedidoPizza.remove(pedidoKey);
    }

}
