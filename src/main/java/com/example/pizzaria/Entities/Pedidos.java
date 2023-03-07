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
import java.util.List;

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
    private Clientes cliente;

    @ManyToMany
    @JoinTable(name = "pedido_pizza", joinColumns = @JoinColumn(name = "pedidoId"), inverseJoinColumns = @JoinColumn(name = "pizzaId"))
    private List<Pizzas> pedidoPizzas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "pedido_bebida", joinColumns = @JoinColumn(name = "pedidoId"), inverseJoinColumns = @JoinColumn(name = "bebidaId"))
    private List<Bebidas> pedidoBebidas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "pedido_sobremesa", joinColumns = @JoinColumn(name = "pedidoId"), inverseJoinColumns = @JoinColumn(name = "sobremesaId"))
    private List<Sobremesas> pedidoSobremesas = new ArrayList<>();

    public Pedidos() {
    };

    public Pedidos(int pedidoTotal, Clientes newCliente, List<Pizzas> pizzas, List<Bebidas> bebidas,
            List<Sobremesas> sobremesas) {
        this.pedidoTotal = pedidoTotal;
        this.cliente = newCliente;
        this.pedidoPizzas = pizzas;
        this.pedidoBebidas = bebidas;
        this.pedidoSobremesas = sobremesas;
    }

    public List<Pizzas> getPizzas() {
        return pedidoPizzas;
    };

    public List<Bebidas> getBebidas() {
        return pedidoBebidas;
    };

    public List<Sobremesas> getSobremesas() {
        return pedidoSobremesas;
    };

    public int getPizzaTotals() {
        int totals = 0;
        if (pedidoPizzas.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < pedidoPizzas.size(); i++) {
                Pizzas currentPizza = this.getPizzas().get(i);
                int currPrice = currentPizza.getPreco();
                totals += currPrice;
            }
            return totals;
        }
    }

    public int getBebidaTotals() {
        int totals = 0;
        if (pedidoBebidas.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < pedidoBebidas.size(); i++) {
                Bebidas currentBebida = this.getBebidas().get(i);
                int currPrice = currentBebida.getPreco();
                totals += currPrice;
            }
            return totals;
        }
    }

    public int getSobremesaTotals() {
        int totals = 0;
        if (pedidoSobremesas.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < pedidoSobremesas.size(); i++) {
                Sobremesas currentSobremesa = this.getSobremesas().get(i);
                int currPrice = currentSobremesa.getPreco();
                totals += currPrice;
            }
            return totals;
        }
    }

}
