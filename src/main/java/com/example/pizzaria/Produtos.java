package com.example.pizzaria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "produtos")
public class Produtos {

    private int id;
    private String nome;
    private int preco;
    private String descricao;

    public Produtos() {
    };

    public Produtos(String nome, int preco, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nome_produto", nullable = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String novo_nome) {
        this.nome = novo_nome;
    }

    @Column(name = "preco_produto", nullable = true)
    public int getPreco() {
        return preco;
    }

    public void setPreco(int novo_preco) {
        this.preco = novo_preco;
    }

    @Column(name = "descricao_produto", nullable = true)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String nova_descricao) {
        this.descricao = nova_descricao;
    }
}
