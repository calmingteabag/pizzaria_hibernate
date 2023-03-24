package com.example.pizzaria.Models;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    public Clientes(String nome, String sobreNome) {
        this.clienteNome = nome;
        this.clienteSobrenome = sobreNome;
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

    public Map<Integer, Pedidos> getAllPedidos() {
        return clientePedidos;
    }

    public void setPedido(Integer idPedido, Pedidos pedido) {
        if (this.clientePedidos == null) {
            this.clientePedidos = new HashMap<>();
        }
        this.clientePedidos.put(idPedido, pedido);
    }

    public Pedidos getOnePedido(Integer idPedido) {
        return clientePedidos.get(idPedido);
    }

    public void removeOnePedido(Integer idPedido) {
        clientePedidos.remove(idPedido);
    }
}
