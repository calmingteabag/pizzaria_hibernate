package com.example.pizzaria.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

import com.example.pizzaria.JSON.*;

public class RetrievePedido {

    public RetrievePedido() {
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

    public void generateJSONPedido(int pedidoId) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        // nome cliente
        String nomeCliente = getClienteNome(pedidoId);

        // total geral
        Integer totalGeral = getTotalGeral(pedidoId);

        // Pizzas
        List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();
        ArrayList<?> insertPizzas = getPedidosNames(pedidoId, "pizzas");
        ArrayList<?> insertQuantidadesPizzas = getPrecoUnitario(pedidoId, "pizzas");
        ArrayList<?> insertPrecoUnitarioPizzas = getPedidosPrecos(pedidoId, "pizzas");
        Integer totalProdutoPizzas = getTotaisPorTipo(pedidoId, "pizzas");

        JSONPedidosDetalhes detalhesPizzas = new JSONPedidosDetalhes(totalProdutoPizzas, insertQuantidadesPizzas,
                insertPizzas,
                insertPrecoUnitarioPizzas);

        // Bebidas
        List<PedidosBebidas> pedidosBebidas = pedido.getPedidosBebidas();
        ArrayList<?> insertBebidas = getPedidosNames(pedidoId, "bebidas");
        ArrayList<?> insertQuantidadesBebidas = getPrecoUnitario(pedidoId, "bebidas");
        ArrayList<?> insertPrecoUnitarioBebidas = getPedidosPrecos(pedidoId, "bebidas");
        Integer totalProdutoBebidas = getTotaisPorTipo(pedidoId, "bebidas");

        JSONPedidosDetalhes detalhesBebidas = new JSONPedidosDetalhes(totalProdutoBebidas, insertQuantidadesBebidas,
                insertBebidas,
                insertPrecoUnitarioBebidas);

        // Sobremesas
        List<PedidosSobremesas> pedidosSobremesas = pedido.getPedidosSobremesas();
        ArrayList<?> insertSobremesas = getPedidosNames(pedidoId, "sobremesas");
        ArrayList<?> insertQuantidadesSobremesas = getPrecoUnitario(pedidoId, "sobremesas");
        ArrayList<?> insertPrecoUnitarioSobremesas = getPedidosPrecos(pedidoId, "sobremesas");
        Integer totalProdutoSobremesas = getTotaisPorTipo(pedidoId, "sobremesas");

        JSONPedidosDetalhes detalhesSobremesas = new JSONPedidosDetalhes(totalProdutoSobremesas,
                insertQuantidadesSobremesas, insertSobremesas,
                insertPrecoUnitarioSobremesas);

        JSONPedidosProdutos produtos = new JSONPedidosProdutos(detalhesPizzas, detalhesBebidas, detalhesSobremesas);

        JSONPedido jsonPedido = new JSONPedido(nomeCliente, produtos, totalGeral);

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

    public Integer getTotaisPorTipo(int pedidoId, String productType) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        Integer totais = 0;

        if (productType == "pizzas") {
            List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();

            for (int i = 0; i < pedidosPizzas.size(); i++) {
                PedidosPizzas currPedidoPizza = pedidosPizzas.get(i);
                int pizzaValor = currPedidoPizza.getPizza().getPreco();
                int quantidade = currPedidoPizza.getQty();
                totais += pizzaValor * quantidade;
            }
        } else if (productType == "bebidas") {
            List<PedidosBebidas> pedidosBebidas = pedido.getPedidosBebidas();

            for (int i = 0; i < pedidosBebidas.size(); i++) {
                PedidosBebidas currPedidoBebida = pedidosBebidas.get(i);
                int bebidaValor = currPedidoBebida.getBebida().getPreco();
                int quantidade = currPedidoBebida.getQty();
                totais += bebidaValor * quantidade;
            }
        } else if (productType == "sobremesas") {
            List<PedidosSobremesas> pedidosSobremesas = pedido.getPedidosSobremesas();

            for (int i = 0; i < pedidosSobremesas.size(); i++) {
                PedidosSobremesas currPedidoSobremesa = pedidosSobremesas.get(i);
                int sobremesaValor = currPedidoSobremesa.getSobremesa().getPreco();
                int quantidade = currPedidoSobremesa.getQty();
                totais += sobremesaValor * quantidade;
            }
        } else {
            return totais;
        }

        return totais;
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

    public Integer getTotalGeral(int pedidoId) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        Integer totais = 0;

        List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();

        for (int i = 0; i < pedidosPizzas.size(); i++) {
            PedidosPizzas currPedidoPizza = pedidosPizzas.get(i);
            int pizzaValor = currPedidoPizza.getPizza().getPreco();
            int quantidade = currPedidoPizza.getQty();
            totais += pizzaValor * quantidade;
        }

        List<PedidosBebidas> pedidosBebidas = pedido.getPedidosBebidas();

        for (int i = 0; i < pedidosBebidas.size(); i++) {
            PedidosBebidas currPedidoBebida = pedidosBebidas.get(i);
            int bebidaValor = currPedidoBebida.getBebida().getPreco();
            int quantidade = currPedidoBebida.getQty();
            totais += bebidaValor * quantidade;
        }

        List<PedidosSobremesas> pedidosSobremesas = pedido.getPedidosSobremesas();

        for (int i = 0; i < pedidosSobremesas.size(); i++) {
            PedidosSobremesas currPedidoSobremesa = pedidosSobremesas.get(i);
            int sobremesaValor = currPedidoSobremesa.getSobremesa().getPreco();
            int quantidade = currPedidoSobremesa.getQty();
            totais += sobremesaValor * quantidade;
        }
        return totais;
    }
};
