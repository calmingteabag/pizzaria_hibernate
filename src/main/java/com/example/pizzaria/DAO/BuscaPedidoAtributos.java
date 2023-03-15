package com.example.pizzaria.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

public class BuscaPedidoAtributos {

    public BuscaPedidoAtributos() {
    }

    public Pedidos getPedido(int pedidoId) {
        // should return Pedidos with all its children
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);

        session.close();
        return pedido;
    }

    public String getClienteNome(int pedidoId) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);

        Clientes cliente = pedido.getCliente();
        String clientNome = cliente.getNome() + " " + cliente.getSobrenome();

        return clientNome;
    }

    public ArrayList<?> getPedidosPizzasNames(int pedidoId) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        ArrayList<String> pizzasNames = new ArrayList<>();

        List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();

        for (int i = 0; i < pedidosPizzas.size(); i++) {
            PedidosPizzas currPedidoPizza = pedidosPizzas.get(0);
            String pizzaName = currPedidoPizza.getPizza().getNome();
            pizzasNames.add(pizzaName);
        }

        return pizzasNames;
    }

    public ArrayList<?> getPedidosNames(int pedidoId, String productType) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        ArrayList<String> names = new ArrayList<>();

        if (productType == "pizzas") {
            List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();

            for (int i = 0; i < pedidosPizzas.size(); i++) {
                PedidosPizzas currPedidoPizza = pedidosPizzas.get(i);
                String pizzaName = currPedidoPizza.getPizza().getNome();
                names.add(pizzaName);
            }
        } else if (productType == "bebidas") {
            List<PedidosBebidas> pedidosBebidas = pedido.getPedidosBebidas();

            for (int i = 0; i < pedidosBebidas.size(); i++) {
                PedidosBebidas currPedidoBebida = pedidosBebidas.get(i);
                String bebidaName = currPedidoBebida.getBebida().getNome();
                names.add(bebidaName);
            }
        } else if (productType == "sobremesas") {
            List<PedidosSobremesas> pedidosSobremesas = pedido.getPedidosSobremesas();

            for (int i = 0; i < pedidosSobremesas.size(); i++) {
                PedidosSobremesas currPedidoSobremesa = pedidosSobremesas.get(i);
                String bebidaName = currPedidoSobremesa.getSobremesa().getNome();
                names.add(bebidaName);
            }
        } else {
            return names;
        }

        return names;
    }

    public ArrayList<?> getPedidosPrecos(int pedidoId, String productType) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        ArrayList<Integer> names = new ArrayList<>();

        if (productType == "pizzas") {
            List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();

            for (int i = 0; i < pedidosPizzas.size(); i++) {
                PedidosPizzas currPedidoPizza = pedidosPizzas.get(i);
                int pizzaName = currPedidoPizza.getPizza().getPreco();
                names.add(pizzaName);
            }
        } else if (productType == "bebidas") {
            List<PedidosBebidas> pedidosBebidas = pedido.getPedidosBebidas();

            for (int i = 0; i < pedidosBebidas.size(); i++) {
                PedidosBebidas currPedidoBebida = pedidosBebidas.get(i);
                int bebidaName = currPedidoBebida.getBebida().getPreco();
                names.add(bebidaName);
            }
        } else if (productType == "sobremesas") {
            List<PedidosSobremesas> pedidosSobremesas = pedido.getPedidosSobremesas();

            for (int i = 0; i < pedidosSobremesas.size(); i++) {
                PedidosSobremesas currPedidoSobremesa = pedidosSobremesas.get(i);
                int sobremesaName = currPedidoSobremesa.getSobremesa().getPreco();
                names.add(sobremesaName);
            }
        } else {
            return names;
        }

        return names;
    }

    public ArrayList<?> getPrecoUnitario(int pedidoId, String productType) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        ArrayList<Integer> valoresUnitarios = new ArrayList<>();

        if (productType == "pizzas") {
            List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();

            for (int i = 0; i < pedidosPizzas.size(); i++) {
                PedidosPizzas currPedidoPizza = pedidosPizzas.get(i);
                int quantidade = currPedidoPizza.getQty();
                valoresUnitarios.add(quantidade);
            }
        } else if (productType == "bebidas") {
            List<PedidosBebidas> pedidosBebidas = pedido.getPedidosBebidas();

            for (int i = 0; i < pedidosBebidas.size(); i++) {
                PedidosBebidas currPedidoBebida = pedidosBebidas.get(i);
                int quantidade = currPedidoBebida.getQty();
                valoresUnitarios.add(quantidade);
            }
        } else if (productType == "sobremesas") {
            List<PedidosSobremesas> pedidosSobremesas = pedido.getPedidosSobremesas();

            for (int i = 0; i < pedidosSobremesas.size(); i++) {
                PedidosSobremesas currPedidoSobremesa = pedidosSobremesas.get(i);
                int quantidade = currPedidoSobremesa.getQty();
                valoresUnitarios.add(quantidade);
            }
        } else {
            return valoresUnitarios;
        }

        return valoresUnitarios;
    }

};
