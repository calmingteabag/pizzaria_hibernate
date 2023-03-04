package com.example.pizzaria.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int pedidoId;

    @Column(name = "total_pedido")
    private int pedidoTotal;

    @ManyToOne
    private Clientes clientes;

    @ManyToMany
    @JoinTable(name = "pedido_pizza", joinColumns = @JoinColumn(name = "pedidoId"), inverseJoinColumns = @JoinColumn(name = "pizzaId"))
    private List<Pizzas> pedidoPizzas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "pedido_bebida", joinColumns = @JoinColumn(name = "pedidoId"), inverseJoinColumns = @JoinColumn(name = "bebidaId"))
    private ArrayList<Bebidas> pedidoBebidas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "pedido_sobremesa", joinColumns = @JoinColumn(name = "pedidoId"), inverseJoinColumns = @JoinColumn(name = "sobremesaId"))
    private ArrayList<Sobremesas> pedidoSobremesas = new ArrayList<>();

    public Pedidos() {
    };

}
