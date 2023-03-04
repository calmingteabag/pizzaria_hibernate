package com.example.pizzaria.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;

@Entity
@Table(name = "bebidas")
public class Bebidas {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int bebidaId;

    @Column(name = "nome")
    private String bebidaNome;

    @Column(name = "descricao")
    private String bebidaDescricao;

    @Column(name = "preco")
    private int pizzaPreço;

    @ManyToMany(mappedBy = "pedidoBebidas")
    private ArrayList<Pedidos> pedidos = new ArrayList<>();

    public Bebidas() {
    };
}
