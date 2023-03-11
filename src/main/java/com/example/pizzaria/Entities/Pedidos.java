package com.example.pizzaria.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pedido_id")
    private int pedidoId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Clientes cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidosPizzas> pedidoPizzas;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidosBebidas> pedidoBebidas;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidosSobremesas> pedidoSobremesas;

    public Pedidos() {
    };

    public Pedidos(Clientes newCliente, List<PedidosPizzas> pizzas, List<PedidosBebidas> bebidas,
            List<PedidosSobremesas> sobremesas) {
        this.cliente = newCliente;
        this.pedidoPizzas = pizzas;
        this.pedidoBebidas = bebidas;
        this.pedidoSobremesas = sobremesas;
    }

    public Pedidos(Clientes newCliente) {
        this.cliente = newCliente;
    }

    public Clientes getClientes() {
        return cliente;
    }

    public void setClientes(Clientes newCliente) {
        this.cliente = newCliente;
    }

    public List<PedidosPizzas> getPedidosPizzas() {
        return pedidoPizzas;
    };

    public void setPedidosPizzas(List<PedidosPizzas> newPedidosPizzas) {
        this.pedidoPizzas = newPedidosPizzas;
    }

    public List<PedidosBebidas> getPedidosBebidas() {
        return pedidoBebidas;
    };

    public void setPedidosBebidas(List<PedidosBebidas> newPedidosBebidas) {
        this.pedidoBebidas = newPedidosBebidas;
    }

    public List<PedidosSobremesas> getPedidosSobremesas() {
        return pedidoSobremesas;
    };

    public void setPedidosSobremesas(List<PedidosSobremesas> newPedidosSobremesas) {
        this.pedidoSobremesas = newPedidosSobremesas;
    }

    // public int getPizzaTotals() {
    // int totals = 0;
    // if (pedidoPizzas.isEmpty()) {
    // return 0;
    // } else {

    // for (int i = 0; i < pedidoPizzas.size(); i++) {

    // Pizzas currentPizza = (Pizzas) this.pedidoPizzas.keySet().toArray()[i];
    // PedidosDetalhes currentDetalhes = this.pedidoPizzas.get(currentPizza);

    // int singlePrice = currentPizza.getPreco();
    // int orderQty = currentDetalhes.getQty();

    // totals += singlePrice * orderQty;
    // }

    // return totals;
    // }
    // }

    // public int getBebidaTotals() {
    // int totals = 0;
    // if (pedidoBebidas.isEmpty()) {
    // return 0;
    // } else {
    // for (int i = 0; i < pedidoBebidas.size(); i++) {
    // Bebidas currentBebida = this.getBebidas().get(i);
    // int currPrice = currentBebida.getPreco();
    // totals += currPrice;
    // }
    // return totals;
    // }
    // }

    // public int getSobremesaTotals() {
    // int totals = 0;
    // if (pedidoSobremesas.isEmpty()) {
    // return 0;
    // } else {
    // for (int i = 0; i < pedidoSobremesas.size(); i++) {
    // Sobremesas currentSobremesa = this.getSobremesas().get(i);
    // int currPrice = currentSobremesa.getPreco();
    // totals += currPrice;
    // }
    // return totals;
    // }
    // }
}
