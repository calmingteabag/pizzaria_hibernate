package com.example.pizzaria.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bebidas")
public class Bebidas implements ProdutosInterface {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int bebidaId;

    @Column(name = "nome")
    private String bebidaNome;

    @Column(name = "descricao")
    private String bebidaDescricao;

    @Column(name = "preco")
    private int bebidaPreco;

    @ManyToMany(mappedBy = "pedidoBebidas", fetch = FetchType.EAGER)
    private List<Pedidos> pedidos = new ArrayList<>();

    public Bebidas() {
    };

    public Bebidas(String bebidasNome, String bebidasDescricao, int bebidasPreco) {
        this.bebidaNome = bebidasNome;
        this.bebidaDescricao = bebidasDescricao;
        this.bebidaPreco = bebidasPreco;
    }

    public void setNome(String novoNome) {
        this.bebidaNome = novoNome;
    };

    public String getNome() {
        return bebidaNome;
    };

    public void setDescricao(String novaDescricao) {
        this.bebidaDescricao = novaDescricao;
    }

    public String getDescricao() {
        return bebidaDescricao;
    }

    public void setPreco(int novoPreco) {
        this.bebidaPreco = novoPreco;
    }

    public int getPreco() {
        return bebidaPreco;
    }
}
