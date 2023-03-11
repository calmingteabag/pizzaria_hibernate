package com.example.pizzaria.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "cliente_id")
    private int clienteId;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Pedidos> clientePedidos = new ArrayList<>();

    @Column(name = "cliente_nome", unique = true, nullable = false)
    private String clienteNome;

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

    public List<Pedidos> getClientePedidos() {
        return clientePedidos;
    }

}
