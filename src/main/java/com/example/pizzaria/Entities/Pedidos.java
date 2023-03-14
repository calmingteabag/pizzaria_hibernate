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

import java.util.ArrayList;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL)
    @Column(name = "pedido_Pizzas")
    private List<PedidosPizzas> pedidoPizzas;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL)
    @Column(name = "pedido_Bebidas")
    private List<PedidosBebidas> pedidoBebidas;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL)
    @Column(name = "pedido_Sobremesas")
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

    public int getPedidoId() {
        return pedidoId;
    }

    public Clientes getCliente() {
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

    public ArrayList<?> getProductNamesList(String productType) {
        ArrayList<String> nameList = new ArrayList<>();

        if (productType == "pizzas") {
            for (int i = 0; i < pedidoPizzas.size(); i++) {
                Pizzas pizza = pedidoPizzas.get(i).getPizza();
                String nome = pizza.getNome();
                nameList.add(nome);
            }
        } else if (productType == "bebidas") {
            for (int i = 0; i < pedidoBebidas.size(); i++) {
                Bebidas bebida = pedidoBebidas.get(i).getBebida();
                String nome = bebida.getNome();
                nameList.add(nome);
            }

        } else if (productType == "sobremesas") {
            for (int i = 0; i < pedidoSobremesas.size(); i++) {
                Sobremesas sobremesa = pedidoSobremesas.get(i).getSobremesa();
                String nome = sobremesa.getNome();
                nameList.add(nome);
            }
        }
        return nameList;
    }

    public ArrayList<?> getProductPriceList(String productType) {
        ArrayList<Integer> priceList = new ArrayList<>();

        if (productType == "pizzas") {
            for (int i = 0; i < pedidoPizzas.size(); i++) {
                Pizzas pizza = pedidoPizzas.get(i).getPizza();
                Integer price = pizza.getPreco();
                priceList.add(price);
            }
        } else if (productType == "bebidas") {
            for (int i = 0; i < pedidoBebidas.size(); i++) {
                Bebidas bebida = pedidoBebidas.get(i).getBebida();
                Integer price = bebida.getPreco();
                priceList.add(price);
            }

        } else if (productType == "sobremesas") {
            for (int i = 0; i < pedidoSobremesas.size(); i++) {
                Sobremesas sobremesa = pedidoSobremesas.get(i).getSobremesa();
                Integer price = sobremesa.getPreco();
                priceList.add(price);
            }
        }
        return priceList;
    }

    public ArrayList<?> getProductQtyList(String productType) {
        ArrayList<Integer> qtyList = new ArrayList<>();

        if (productType == "pizzas") {
            for (int i = 0; i < pedidoPizzas.size(); i++) {
                Integer qty = pedidoPizzas.get(i).getQty();
                qtyList.add(qty);
            }
        } else if (productType == "bebidas") {
            for (int i = 0; i < pedidoBebidas.size(); i++) {
                Integer qty = pedidoBebidas.get(i).getQty();
                qtyList.add(qty);
            }

        } else if (productType == "sobremesas") {
            for (int i = 0; i < pedidoSobremesas.size(); i++) {
                Integer qty = pedidoSobremesas.get(i).getQty();
                qtyList.add(qty);
            }
        }
        return qtyList;
    }

    public Integer getTotaisPorTipo(int pedidoId, String productType) {
        Integer totais = 0;

        if (productType == "pizzas") {
            for (int i = 0; i < pedidoPizzas.size(); i++) {
                PedidosPizzas currPedidoPizza = pedidoPizzas.get(i);
                int pizzaValor = currPedidoPizza.getPizza().getPreco();
                int quantidade = currPedidoPizza.getQty();
                totais += pizzaValor * quantidade;
            }
        } else if (productType == "bebidas") {
            for (int i = 0; i < pedidoBebidas.size(); i++) {
                PedidosBebidas currPedidoBebida = pedidoBebidas.get(i);
                int bebidaValor = currPedidoBebida.getBebida().getPreco();
                int quantidade = currPedidoBebida.getQty();
                totais += bebidaValor * quantidade;
            }
        } else if (productType == "sobremesas") {
            for (int i = 0; i < pedidoSobremesas.size(); i++) {
                PedidosSobremesas currPedidoSobremesa = pedidoSobremesas.get(i);
                int sobremesaValor = currPedidoSobremesa.getSobremesa().getPreco();
                int quantidade = currPedidoSobremesa.getQty();
                totais += sobremesaValor * quantidade;
            }
        } else {
            return totais;
        }

        return totais;
    }

}
