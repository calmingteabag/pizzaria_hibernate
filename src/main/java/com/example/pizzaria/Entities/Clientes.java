package com.example.pizzaria.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "cliente_id")
    private int clienteId;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @MapKey(name = "pedidoId")
    private Map<Integer, Pedidos> clientePedidos = new HashMap<>();

    @Column(name = "cliente_nome", nullable = false)
    private String clienteNome;

    @Column(name = "cliente_sobrenome")
    private String clienteSobrenome;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime horaCriada;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private LocalDateTime horaModificada;

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

    public void setNome(String novoNome) {
        this.clienteNome = novoNome;
    }

    public String getSobrenome() {
        return clienteSobrenome;
    }

    public void setSobrenome(String novoSobrenome) {
        this.clienteSobrenome = novoSobrenome;
    }

    // public List<Pedidos> getClientePedidos() {
    // return clientePedidos;
    // }

    // public Map<Integer, Pedidos> getClientePedidos() {
    // return clientePedidos;
    // }

    public Pedidos getSpecificPedido(Integer pedidoId) {
        return clientePedidos.get(pedidoId);
    }

    public Pedidos getSpecificPedidoFromList(int pedidoId) {
        for (int i = 0; i < clientePedidos.size(); i++) {
            if (clientePedidos.get(i).getPedidoId() == pedidoId) {
                return clientePedidos.get(i);
            }
        }
        return null;
    }

}
