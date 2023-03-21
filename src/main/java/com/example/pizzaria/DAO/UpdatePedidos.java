package com.example.pizzaria.DAO;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

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
                Map<String, PedidosPizzas> pedidosPizzas = pedido.getAllPedidosPizzas();

                if (pedidosPizzas.containsKey(novoProduto)) { // pizza already exists, change qty.
                    PedidosPizzas currPedidoPizza = pedidosPizzas.get(novoProduto);
                    currPedidoPizza.setQty(intNovaQty);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format(
                            "Pedido ID: %s. | Alteração: Pizza já existente. Quantidade das pizzas %s foi alterada para %s",
                            pedidoId, novaPizza.getNome(), intNovaQty);
                } else { // pizza doesn't exists, create new, add to pedido.
                    PedidosPizzas insert = new PedidosPizzas(pedido, novaPizza, intNovaQty);
                    insert.setNomePizza(novaPizzaNome);
                    pedidosPizzas.put(novaPizzaNome, insert);

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
                Map<String, PedidosBebidas> pedidosBebidas = pedido.getAllPedidosBebidas();

                if (pedidosBebidas.containsKey(novoProduto)) { // pizza already exists, change qty.
                    PedidosBebidas currPedidoBebida = pedidosBebidas.get(novoProduto);
                    currPedidoBebida.setQty(intNovaQty);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format(
                            "Pedido ID: %s. | Alteração: Pizza já existente. Quantidade das pizzas %s foi alterada para %s",
                            pedidoId, novaBebidaNome, intNovaQty);
                } else { // pizza doesn't exists, create new, add to pedido.
                    PedidosBebidas insert = new PedidosBebidas(pedido, novaBebida, intNovaQty);
                    insert.setNomeBebida(novaBebidaNome);
                    pedidosBebidas.put(novaBebidaNome, insert);

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
                Map<String, PedidosSobremesas> pedidosSobremesas = pedido.getAllPedidosSobremesas();

                if (pedidosSobremesas.containsKey(novoProduto)) { // pizza already exists, change qty.
                    PedidosSobremesas currPedidoSobremesa = pedidosSobremesas.get(novoProduto);
                    currPedidoSobremesa.setQty(intNovaQty);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format(
                            "Pedido ID: %s. | Alteração: Pizza já existente. Quantidade das pizzas %s foi alterada para %s",
                            pedidoId, novaSobremesaNome, intNovaQty);
                } else { // pizza doesn't exists, create new, add to pedido.
                    PedidosSobremesas insert = new PedidosSobremesas(pedido, novaSobremesa, intNovaQty);
                    insert.setNomeSobremesa(novaSobremesaNome);
                    pedidosSobremesas.put(novaSobremesaNome, insert);

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
