package com.example.pizzaria.DAO;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import com.example.pizzaria.Interfaces.PedidoProduto;
import com.example.pizzaria.Models.Pedidos;
import com.example.pizzaria.Models.PedidosBebidas;
import com.example.pizzaria.Models.PedidosPizzas;
import com.example.pizzaria.Models.PedidosSobremesas;
import com.example.pizzaria.Utils.HibernateSession;

public class RemovePedidos {

    public RemovePedidos() {
    };

    public String removeItensPedido(String pedidoId, String produtoId, String tipoProduto) {

        Session session = HibernateSession.getSession();
        Transaction transaction = session.beginTransaction();
        ProdutoHashMap keyGen = new ProdutoHashMap();
        Pedidos pedido = session.get(Pedidos.class, Integer.parseInt(pedidoId));

        switch (tipoProduto) {

            case "pizza":

                Map<String, ?> pedidoPizzas = pedido.getAllPedidoProduto(tipoProduto);
                String hashPizza = keyGen.generateKeybyId(pedidoId, produtoId);

                PedidoProduto pedidoPizza = (PedidoProduto) pedidoPizzas.get(hashPizza);
                String nomePizza = pedidoPizza.getNomeProduto();

                String removePizza = "DELETE FROM pedido_pizza WHERE pedido_id = :buscaValor1 AND pizza_id = :buscaValor2";
                SelectionQuery<PedidosPizzas> queryPizza = session.createNativeQuery(removePizza, PedidosPizzas.class);
                queryPizza.setParameter("buscaValor1", Integer.parseInt(pedidoId));
                queryPizza.setParameter("buscaValor2", Integer.parseInt(produtoId));
                ((Query<PedidosPizzas>) queryPizza).executeUpdate();

                session.clear();
                session.flush();

                transaction.commit();
                session.close();

                return String.format(" Pizza %s removida do pedido %s", nomePizza, pedidoId);

            case "bebida":

                Map<String, ?> pedidosBebidas = pedido.getAllPedidoProduto(tipoProduto);
                String hashBebida = keyGen.generateKeybyId(pedidoId, produtoId);

                PedidoProduto pedidoBebida = (PedidoProduto) pedidosBebidas.get(hashBebida);
                String nomeBebida = pedidoBebida.getNomeProduto();

                String removeBebida = "DELETE FROM pedido_bebida WHERE pedido_id = :buscaValor1 AND bebida_id = :buscaValor2";
                SelectionQuery<PedidosBebidas> queryBebida = session.createNativeQuery(removeBebida,
                        PedidosBebidas.class);
                queryBebida.setParameter("buscaValor1", Integer.parseInt(pedidoId));
                queryBebida.setParameter("buscaValor2", Integer.parseInt(produtoId));
                ((Query<PedidosBebidas>) queryBebida).executeUpdate();

                session.clear();
                session.flush();

                transaction.commit();
                session.close();

                return String.format(" Bebida %s removida do pedido %s", nomeBebida,
                        pedidoId);

            case "sobremesa":

                Map<String, ?> pedidosSobremesas = pedido.getAllPedidoProduto(tipoProduto);
                String hashSobremesa = keyGen.generateKeybyId(pedidoId, produtoId);

                PedidoProduto pedidoSobremesa = (PedidoProduto) pedidosSobremesas.get(hashSobremesa);
                String nomeSobremesa = pedidoSobremesa.getNomeProduto();

                String removeSobremesa = "DELETE FROM pedido_sobremesa WHERE pedido_id = :buscaValor1 AND sobremesa_id = :buscaValor2";
                SelectionQuery<PedidosSobremesas> querySobremesa = session.createNativeQuery(removeSobremesa,
                        PedidosSobremesas.class);
                querySobremesa.setParameter("buscaValor1", Integer.parseInt(pedidoId));
                querySobremesa.setParameter("buscaValor2", Integer.parseInt(produtoId));
                ((Query<PedidosSobremesas>) querySobremesa).executeUpdate();

                transaction.commit();
                session.close();

                return String.format(" Bebida %s removida do pedido %s", nomeSobremesa,
                        pedidoId);

            default:
                return "Reeeee";
        }
    }
}
