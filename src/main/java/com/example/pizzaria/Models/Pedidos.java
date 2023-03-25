package com.example.pizzaria.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.pizzaria.Interfaces.PedidoProduto;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pedido_id")
    private int pedidoId;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @ManyToOne(fetch = FetchType.EAGER)
    private Clientes cliente;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = { CascadeType.MERGE,
            CascadeType.PERSIST })
    @Column(name = "pedido_Pizzas")
    @MapKey(name = "nomeProduto")
    private Map<String, PedidosPizzas> pedidoPizzas;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @Column(name = "pedido_Bebidas")
    @MapKey(name = "nomeProduto")
    private Map<String, PedidosBebidas> pedidoBebidas;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @Column(name = "pedido_Sobremesas")
    @MapKey(name = "nomeProduto")
    private Map<String, PedidosSobremesas> pedidoSobremesas;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime horaCriada;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private LocalDateTime horaModificada;

    public Pedidos() {
    };

    public Pedidos(Clientes newCliente, Map<String, PedidosPizzas> pizzas, Map<String, PedidosBebidas> bebidas,
            Map<String, PedidosSobremesas> sobremesas) {
        this.cliente = newCliente;
        this.pedidoPizzas = pizzas;
        this.pedidoBebidas = bebidas;
        this.pedidoSobremesas = sobremesas;
    }

    public Pedidos(Clientes newCliente) {
        this.cliente = newCliente;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Map<String, ? extends PedidoProduto> getAllPedidoProduto(String tipoProduto) {
        switch (tipoProduto) {
            case "pizza":
                return pedidoPizzas;

            case "bebida":
                return pedidoBebidas;

            case "sobremesa":
                return pedidoSobremesas;
            default:
                return new HashMap<>();
        }
    }

    public void setPedidoProduto(String pedidoProdutoKey, PedidoProduto pedidoProduto) {
        if (pedidoProduto instanceof PedidosPizzas) {
            if (this.pedidoPizzas == null) {
                this.pedidoPizzas = new HashMap<>();
            }
            pedidoPizzas.put(pedidoProdutoKey, (PedidosPizzas) pedidoProduto);

        } else if (pedidoProduto instanceof PedidosBebidas) {
            if (this.pedidoBebidas == null) {
                this.pedidoBebidas = new HashMap<>();
            }
            pedidoBebidas.put(pedidoProdutoKey, (PedidosBebidas) pedidoProduto);
        } else {
            if (this.pedidoSobremesas == null) {
                this.pedidoSobremesas = new HashMap<>();
            }
            pedidoSobremesas.put(pedidoProdutoKey, (PedidosSobremesas) pedidoProduto);
        }
    }

    public PedidoProduto getOnePedidoProduto(String pedidoProdutoKey, PedidoProduto pedidoProduto) {
        if (pedidoProduto instanceof PedidosPizzas) {
            return (PedidoProduto) pedidoPizzas.get(pedidoProdutoKey);
        } else if (pedidoProduto instanceof PedidosBebidas) {
            return (PedidoProduto) pedidoBebidas.get(pedidoProdutoKey);
        } else {
            return (PedidoProduto) pedidoSobremesas.get(pedidoProdutoKey);
        }
    }

    public void removeOnePedidoProduto(String pedidoProdutoKey, PedidoProduto pedidoProduto) {
        if (pedidoProduto instanceof PedidosPizzas) {
            pedidoPizzas.remove(pedidoProdutoKey);
        } else if (pedidoProduto instanceof PedidosBebidas) {
            pedidoBebidas.remove(pedidoProdutoKey);
        } else {
            pedidoSobremesas.remove(pedidoProdutoKey);
        }
    }
}
