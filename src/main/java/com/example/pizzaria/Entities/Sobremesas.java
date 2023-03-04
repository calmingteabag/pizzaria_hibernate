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
@Table(name = "sobremesas")
public class Sobremesas {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int sobremesaId;

    @Column(name = "nome")
    private String sobremesaNome;

    @Column(name = "descricao")
    private String sobremesaDescricao;

    @Column(name = "preco")
    private int sobremesaPreço;

    @ManyToMany(mappedBy = "pedidoSobremesas")
    private ArrayList<Pedidos> pedidos = new ArrayList<>();

    public Sobremesas() {
    };
}
