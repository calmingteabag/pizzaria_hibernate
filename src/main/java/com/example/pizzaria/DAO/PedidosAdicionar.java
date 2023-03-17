package com.example.pizzaria.DAO;

import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

import jakarta.persistence.criteria.Selection;

public class PedidosAdicionar {

    public PedidosAdicionar() {
    };

    public String adicionarPedidosPizzas(
            String pedidoId,
            String novaPizzaQuantidade,
            String novaPizzaTable,
            String novaPizzaColumn,
            String novaPizzaColumnValue,
            String novaPizzaGetIdMethodName) {
        // inicia session e transaction
        Session session = HibernateSession.getSession();
        Transaction transaction = session.beginTransaction();
        int novaPizzaQty = Integer.parseInt(novaPizzaQuantidade);

        int id = Integer.parseInt(pedidoId);
        Pedidos pedido = session.get(Pedidos.class, id);

        List<PedidosPizzas> listPedidosPizzas = pedido.getPedidosPizzas();

        // vou colocar esse codigo de baixo em um metodo numa classe de buscas
        // como estou fazendo é ficar buscando pedaços das colunas para ir montando a
        // entidade que preciso
        // mas o esquema é reunir o metodo de buscas por entidades em uma classe só e a
        // partir dela ir
        // chamando as buscas.

        // ex, classe BuscaEntidadesPorId, classe BuscaEntidadesPorNome, etc

        // metodos: buscaPizzas, buscaBebidas, buscaSobremesas, buscaCLientes,
        // buscaPedidoPizzas, buscaPedidoBebidas, buscaPedidoSobremesas,

        // OU organizar POR entidade

        // ex, classe BuscaPizzas, classe BuscaBebidas, etc

        // metodos: buscaPizzaPorId, buscaPizzaPorNome, buscaPizzaPorPreco

        // $$$$$$$$$$$$$$$$$###################
        // segundo vi, é melhor 1 para cada entidade, ou seja
        // classe BuscaPizzas e todos os metodos de busca(id, nome, data, etc etc)

        String buscaString = String.format("SELECT * FROM %s WHERE %s = :buscaValor", novaPizzaTable,
                novaPizzaColumn);
        SelectionQuery<Pizzas> buscaPizza = session.createNativeQuery(buscaString, Pizzas.class);
        buscaPizza.setParameter("buscaValor", novaPizzaColumnValue);
        Pizzas novaPizza = buscaPizza.getSingleResult();

        // to fazendo algo bem retardado, que é pegar o nome da pizza pra inserir,
        // buscando o id dela, pra por numa query, pra retornar a nova pizza de novo...

        // BuscasGenericas busca = new BuscasGenericas(Pizzas.class);
        // int novaPizzaId = busca.getIdByColumnValue(novaPizzaTable, novaPizzaColumn,
        // novaPizzaColumnValue,
        // novaPizzaGetIdMethodName);
        // Pizzas novaPizza = session.get(Pizzas.class, novaPizzaId);

        // loop pra ver se a pizza já existe no pedido.
        // sim, troca a quantidade
        // nope, cria novo pedidopizza e insere
        for (PedidosPizzas currPedidoPizza : listPedidosPizzas) {
            Pizzas currPizza = currPedidoPizza.getPizza();
            String currPizzaNome = currPizza.getNome();

            if (currPizzaNome.equals(novaPizzaColumnValue)) {
                currPedidoPizza.setQty(novaPizzaQty);
                session.merge(currPedidoPizza);
                break;

            } else {
                PedidosPizzas novoPedidoPizza = new PedidosPizzas();
                novoPedidoPizza.setPedido(pedido);
                novoPedidoPizza.setPizza(novaPizza);
                novoPedidoPizza.setQty(novaPizzaQty);
                listPedidosPizzas.add(novoPedidoPizza);
                session.merge(pedido);
                break;
            }

        }

        // for (int i = 0; i < listPedidosPizzas.size(); i++) {
        // PedidosPizzas currPedidoPizza = listPedidosPizzas.get(i);
        // Pizzas currPizza = currPedidoPizza.getPizza();

        // if (currPizza.getNome().equals(novaPizzaColumnValue)) {
        // currPedidoPizza.setQty(novaPizzaQty);
        // session.merge(currPedidoPizza);
        // break;

        // } else {
        // PedidosPizzas novoPedidoPizza = new PedidosPizzas();
        // novoPedidoPizza.setPedido(pedido);
        // novoPedidoPizza.setPizza(novaPizza);
        // novoPedidoPizza.setQty(novaPizzaQty);
        // listPedidosPizzas.add(novoPedidoPizza);
        // session.merge(pedido);
        // break;
        // }

        // }

        // end a operacao
        transaction.commit();
        session.close();

        return String.format("Pedido ID: %s. | Alteração: Adicionado pizza %s ao Pedido.",
                pedidoId, novaPizza.getNome());
    }
}
