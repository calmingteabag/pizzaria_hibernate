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
    @MapKey(name = "mapKey")
    private Map<String, PedidosPizzas> pedidoPizzas;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = { CascadeType.MERGE,
            CascadeType.PERSIST })
    @Column(name = "pedido_Bebidas")
    @MapKey(name = "mapKey")
    private Map<String, PedidosBebidas> pedidoBebidas;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = { CascadeType.MERGE,
            CascadeType.PERSIST })
    @Column(name = "pedido_Sobremesas")
    @MapKey(name = "mapKey")
    private Map<String, PedidosSobremesas> pedidoSobremesas;

    @Column(name = "total_pizzas")
    private int totalPedidosPizzas;

    @Column(name = "total_bebidas")
    private int totalPedidosBebidas;

    @Column(name = "total_sobremesas")
    private int totalPedidosSobremesas;

    @Column(name = "total_parcial")
    private int totalParcial;

    @Column(name = "desconto")
    private int desconto;

    @Column(name = "total_final")
    private int totalFinal;

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

    public int getId() {
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
            System.out.println("O valor de pedidoProdutoKey é: " + pedidoProdutoKey);
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

    public PedidoProduto getOnePedidoProduto(String pedidoProdutoKey, String tipoProduto) {

        switch (tipoProduto) {
            case "pizza":
                return (PedidoProduto) pedidoPizzas.get(pedidoProdutoKey);

            case "bebida":
                return (PedidoProduto) pedidoBebidas.get(pedidoProdutoKey);

            case "sobremesa":
                return (PedidoProduto) pedidoSobremesas.get(pedidoProdutoKey);

            default:
                return null;
        }
    }

    public void removeOnePedidoProduto(String pedidoProdutoKey, String tipoProduto) {

        switch (tipoProduto) {
            case "pizza":
                pedidoPizzas.remove(pedidoProdutoKey);

            case "bebida":
                pedidoBebidas.remove(pedidoProdutoKey);

            case "sobremesa":
                pedidoSobremesas.remove(pedidoProdutoKey);
        }
    }

    public int getTotalPedidos(String tipoProduto) {

        switch (tipoProduto) {
            case "pizza":
                return totalPedidosPizzas;

            case "bebida":
                return totalPedidosBebidas;

            case "sobremesa":
                return totalPedidosSobremesas;

            default:
                return 0;
        }
    }

    public void setTotalPedidos(String tipoProduto, int newTotal) {

        switch (tipoProduto) {
            case "pizza":
                this.totalPedidosPizzas = newTotal;

            case "bebida":
                this.totalPedidosBebidas = newTotal;

            case "sobremesa":
                this.totalPedidosSobremesas = newTotal;
        }
    }

    public int getTotalParcial() {
        return totalParcial;
    }

    public void setTotalParcial(int newTotalParcial) {
        this.totalParcial = newTotalParcial;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int newDesconto) {
        this.desconto = newDesconto;
    }

    public int getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(int newTotal) {
        this.totalFinal = newTotal;
    }

}
