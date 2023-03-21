package com.example.pizzaria.Entities;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL)
    @Column(name = "pedido_Pizzas")
    @MapKey(name = "nomePizza")
    private Map<String, PedidosPizzas> pedidoPizzas;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL)
    @Column(name = "pedido_Bebidas")
    @MapKey(name = "nomeBebida")
    private Map<String, PedidosBebidas> pedidoBebidas;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL)
    @Column(name = "pedido_Sobremesas")
    @MapKey(name = "nomeSobremesa")
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

    public Clientes getCliente() {
        return cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    // public <T> T getAttribute(Strint atributoNome) {
    // Field field = getClass().getDeclaredField(attributeNome);
    // field.setAccessible(true);
    // return (T) field.get(this);
    // }

    public PedidosPizzas getOnePedidosPizzas(String pizzaNomeKey) {
        return pedidoPizzas.get(pizzaNomeKey);
    }

    public PedidosBebidas getOnePedidosBebidas(String bebidaNomeKey) {
        return pedidoBebidas.get(bebidaNomeKey);
    }

    public PedidosSobremesas getOnePedidosobremesas(String sobremesaNomeKey) {
        return pedidoSobremesas.get(sobremesaNomeKey);
    }

    // generalizing getOnePedidos_"Tipo"_()
    public <T> T getOneProduto(String nomePedido, Map<String, T> produtoTipo) {
        return produtoTipo.get(nomePedido);
    }

    /*
     * Example usage:
     * 
     * Pedidos pedido = session.get(Pedidos.class, 1)
     * Map<String, PedidosPizzas> mapPizzas = pedido.getAllPedidosPizzas()
     * PedidosPizzas pp = pedido.getOneProduto("marguerita", )
     * 
     * Vc tem que passar o pedidoPizzas (o hashmap inteiro) como parametro pro
     * metodo, pra ai ele pegar o que precisa. Não sei se é o melhor jeito, mas
     * melhor que criar um metodo para cada pedido______ pra poder extrair o
     * pedido____ especifico que preciso
     */

    // generalizing getAllPeidos_"tipo"__()
    public <T> Map<String, T> getAllProdutosByType(Map<String, T> produtoTipo) {
        return produtoTipo;
    }

    public Map<String, PedidosPizzas> getAllPedidosPizzas() {
        return pedidoPizzas;
    }

    public Map<String, PedidosBebidas> getAllPedidosBebidas() {
        return pedidoBebidas;
    };

    public Map<String, PedidosSobremesas> getAllPedidosSobremesas() {
        return pedidoSobremesas;
    };

    public void setClientes(Clientes newCliente) {
        this.cliente = newCliente;
    }

    public void setNomeCliente(String newNomeCliente) {
        this.nomeCliente = newNomeCliente;
    }

    // generalizing setPedidos
    // public <T extends Pedidos> void setPedidos(String pedidoKey, Map<String, T>
    // pedidosTipo) {
    // T.put(pedidoKey, pedidosTipo);
    // }

    public void setPedidosPizzas(String nomePizzaKey, PedidosPizzas pedidosPizzas) {
        if (this.pedidoPizzas == null) {
            this.pedidoPizzas = new HashMap<>();
        }
        pedidoPizzas.put(nomePizzaKey, pedidosPizzas);
    }

    public void setPedidosBebidas(String nomeBebidaKey, PedidosBebidas pedidosBebidas) {
        if (this.pedidoBebidas == null) {
            this.pedidoBebidas = new HashMap<>();
        }
        pedidoBebidas.put(nomeBebidaKey, pedidosBebidas);
    }

    public void setPedidosSobremesas(String nomeSobremesaKey, PedidosSobremesas pedidosSobremesas) {
        if (this.pedidoSobremesas == null) {
            this.pedidoSobremesas = new HashMap<>();
        }
        pedidoSobremesas.put(nomeSobremesaKey, pedidosSobremesas);
    }

}
