package com.example.pizzaria.DAO;

import com.example.pizzaria.Models.*;
import com.example.pizzaria.Utils.HibernateSession;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdatePedidos extends BuscaProdutos {

    public UpdatePedidos() {
    };

    public String updatePedidoByName(
            Class<? extends Pedidos> classPedidos,
            String tipoProduto,
            String pedidoId,
            String novaQuantidade,
            String novoProduto) {
        Session session = HibernateSession.getSession();
        int intPedido = Integer.parseInt(pedidoId);
        int intNovaQty = Integer.parseInt(novaQuantidade);
        Pedidos pedido = (Pedidos) session.get(classPedidos, intPedido);
        Transaction transaction = session.beginTransaction();

        switch (tipoProduto) {
            case "pizza":
                Pizzas novaPizza = (Pizzas) buscaProdutoPorNome("pizzas",
                        "nome", novoProduto, Pizzas.class);
                String novaPizzaNome = novaPizza.getNome();
                Map<String, ?> pedidosPizzas = pedido.getAllPedidoProduto("pizza");

                if (pedidosPizzas.containsKey(novoProduto)) { // pizza already exists, change qty.
                    PedidosPizzas currPedidoPizza = (PedidosPizzas) pedidosPizzas.get(novoProduto);
                    currPedidoPizza.setQty(intNovaQty);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format(
                            "Pedido ID: %s. | Alteração: Pizza já existente. Quantidade das pizzas %s foi alterada para %s",
                            pedidoId, novaPizza.getNome(), intNovaQty);
                } else {
                    PedidosPizzas insert = new PedidosPizzas(pedido, novaPizza, intNovaQty);
                    insert.setNomeProduto(novaPizzaNome);
                    pedido.setPedidoProduto(novaPizzaNome, insert);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format("Pedido ID: %s. | Alteração: Adicionado %s pizzas de %s",
                            pedido.getPedidoId(), novaQuantidade, novaPizza.getNome());
                }

            case "bebida":
                Bebidas novaBebida = (Bebidas) buscaProdutoPorNome("bebidas",
                        "nome", novoProduto, Bebidas.class);
                String novaBebidaNome = novaBebida.getNome();
                Map<String, ?> pedidosBebidas = pedido.getAllPedidoProduto("bebida");

                if (pedidosBebidas.containsKey(novoProduto)) {
                    PedidosBebidas currPedidoBebida = (PedidosBebidas) pedidosBebidas.get(novoProduto);
                    currPedidoBebida.setQty(intNovaQty);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format(
                            "Pedido ID: %s. | Alteração: Pizza já existente. Quantidade das pizzas %s foi alterada para %s",
                            pedidoId, novaBebidaNome, intNovaQty);
                } else {
                    PedidosBebidas insert = new PedidosBebidas(pedido, novaBebida, intNovaQty);
                    insert.setNomeProduto(novaBebidaNome);
                    pedido.setPedidoProduto(novaBebidaNome, insert);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format("Pedido ID: %s. | Alteração: Adicionado %s pizzas de %s",
                            pedidoId, novaQuantidade, novaBebidaNome);
                }
            case "sobremesa":
                Sobremesas novaSobremesa = (Sobremesas) buscaProdutoPorNome("bebidas",
                        "nome", novoProduto, Sobremesas.class);
                String novaSobremesaNome = novaSobremesa.getNome();
                Map<String, ?> pedidosSobremesas = pedido.getAllPedidoProduto("sobremesa");

                if (pedidosSobremesas.containsKey(novoProduto)) {
                    PedidosSobremesas currPedidoSobremesa = (PedidosSobremesas) pedidosSobremesas.get(novoProduto);
                    currPedidoSobremesa.setQty(intNovaQty);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format(
                            "Pedido ID: %s. | Alteração: Pizza já existente. Quantidade das pizzas %s foi alterada para %s",
                            pedidoId, novaSobremesaNome, intNovaQty);
                } else {
                    PedidosSobremesas insert = new PedidosSobremesas(pedido, novaSobremesa, intNovaQty);
                    insert.setNomeProduto(novaSobremesaNome);
                    pedido.setPedidoProduto(novaSobremesaNome, insert);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format("Pedido ID: %s. | Alteração: Adicionado %s pizzas de %s",
                            pedidoId, novaQuantidade, novaSobremesaNome);
                }
            default:
                return "aaa";
        }
    }
}
