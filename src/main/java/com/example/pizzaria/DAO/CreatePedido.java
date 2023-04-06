package com.example.pizzaria.DAO;

import com.example.pizzaria.Models.*;
import com.example.pizzaria.Services.CalculaValoresPedido;
import com.example.pizzaria.Utils.HibernateSession;

import jakarta.persistence.EntityNotFoundException;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CreatePedido {

    public CreatePedido() {
    };

    public String createNewPedido(String clienteId) {
        Session session = HibernateSession.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Clientes cliente = session.get(Clientes.class, clienteId);

            Pedidos newPedido = new Pedidos(cliente);
            newPedido.setNomeCliente(cliente.getNome());
            session.persist(newPedido);
            int pedidoId = newPedido.getId();
            cliente.setPedido(pedidoId, newPedido);
            transaction.commit();

            return String.format("Pedido id %s criado com sucesso", pedidoId);

        } catch (EntityNotFoundException exception) {
            return "Cliente Inexistente";
        }
    }

    public void setPedidosTotais(int pedidoId, String[] productList) {

        Session session = HibernateSession.getSession();
        Transaction transaction = session.beginTransaction();

        Pedidos pedido = session.get(Pedidos.class, pedidoId);
        CalculaValoresPedido calc = new CalculaValoresPedido();

        for (int i = 0; i < productList.length; i++) {
            int currProdutoTotal = calc.calculaTotaisPorTipo(pedidoId, productList[i]);
            pedido.setTotalPedidos(productList[i], currProdutoTotal);
        }

        int totalParcial = calc.calculaTotalGeral(pedidoId, productList);
        pedido.setTotalParcial(totalParcial);
        pedido.setTotalFinal(totalParcial - pedido.getDesconto());

        session.merge(pedido);
        transaction.commit();

    }
}
