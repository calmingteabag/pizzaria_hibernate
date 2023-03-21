package com.example.pizzaria.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.pizzaria.Interfaces.Produtos;

import java.util.List;

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
    private List<PedidosPizzas> pedidoPizza;

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

    public void setNome(String novoNome) {
        this.pizzaNome = novoNome;
    };

    public String getNome() {
        return pizzaNome;
    };

    public void setDescricao(String novaDescricao) {
        this.pizzaDescricao = novaDescricao;
    }

    public String getDescricao() {
        return pizzaDescricao;
    }

    public void setPreco(int novoPreco) {
        this.pizzaPreco = novoPreco;
    }

    public int getPreco() {
        return pizzaPreco;
    }

    public List<PedidosPizzas> getPedidosPizzas() {
        return pedidoPizza;
    };

    public void setPedidosPizzas(List<PedidosPizzas> newPedidosPizzas) {
        this.pedidoPizza = newPedidosPizzas;
    }
}
