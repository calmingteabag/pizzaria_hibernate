package com.example.pizzaria.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int clienteId;

    @Column(name = "clienteNome", unique = true)
    private String clienteNome;

    @OneToMany(mappedBy = "clientes", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Pedidos> clientePedidos = new ArrayList<>();

    public Clientes() {
    };

    public Clientes(String nome) {
        this.clienteNome = nome;
    }

    public int getId() {
        return clienteId;
    }

    public String getNome() {
        return clienteNome;
    }

    public void setNome(String novo_nome) {
        this.clienteNome = novo_nome;
    }

    public ArrayList<Pedidos> getClientePedidos() {
        return clientePedidos;
    }

}
