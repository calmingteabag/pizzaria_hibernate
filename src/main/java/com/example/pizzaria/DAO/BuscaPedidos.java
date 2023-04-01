package com.example.pizzaria.DAO;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import com.example.pizzaria.Models.*;
import com.example.pizzaria.Utils.HibernateSession;

public class BuscaPedidos {

    public BuscaPedidos() {
    }

    public Pedidos buscaPedidosPorId(int idPedido, Class<Pedidos> classePedido) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(classePedido, idPedido);
        session.close();

        return pedido;
    }

    public Pedidos buscaPedidosPorNome(String nomeTabela, String nomeColuna,
            String nomePedido, Class<Pedidos> classePedido) {
        Session session = HibernateSession.getSession();
        String buscaString = String.format("SELECT * FROM %s WHERE %s = :buscaValorPedido", nomeTabela,
                nomeColuna);

        SelectionQuery<Pedidos> buscaPedidos = session.createNativeQuery(buscaString, classePedido);
        buscaPedidos.setParameter("buscaValorPedido", nomePedido);
        Pedidos pedido = buscaPedidos.getSingleResult();

        return pedido;
    }

}
