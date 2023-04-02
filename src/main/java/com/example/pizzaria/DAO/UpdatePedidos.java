package com.example.pizzaria.DAO;

import com.example.pizzaria.Models.*;
import com.example.pizzaria.Services.ProdutoHashMap;
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
                ProdutoHashMap hashPizza = new ProdutoHashMap();
                Pizzas novaPizza = (Pizzas) buscaProdutoPorNome("pizzas",
                        "nome", novoProduto, Pizzas.class);
                String novaPizzaNome = novaPizza.getNome();
                String pizzaKey = hashPizza.generateKeyByObject(pedido, novaPizza);
                Map<String, ?> pedidosPizzas = pedido.getAllPedidoProduto("pizza");

                if (pedidosPizzas.containsKey(pizzaKey)) { // pizza already exists, change qty.
                    PedidosPizzas currPedidoPizza = (PedidosPizzas) pedidosPizzas.get(pizzaKey);
                    currPedidoPizza.setQty(intNovaQty);

                    session.merge(currPedidoPizza);
                    transaction.commit();
                    session.close();

                    return String.format(
                            "Pedido ID: %s. | Alteração: Pizza já existente. Quantidade das pizzas %s foi alterada para %s",
                            pedidoId, novaPizza.getNome(), intNovaQty);
                } else {
                    PedidosPizzas insert = new PedidosPizzas(pedido, novaPizza, intNovaQty);
                    insert.setNomeProduto(novaPizzaNome);
                    pedido.setPedidoProduto(pizzaKey, insert);
                    novaPizza.setPedidoProduto(pizzaKey, insert);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format("Pedido ID: %s. | Alteração: Adicionado %s pizzas de %s",
                            pedidoId, novaQuantidade, novaPizza.getNome());
                }

            case "bebida":
                ProdutoHashMap hashBebida = new ProdutoHashMap();
                Bebidas novaBebida = (Bebidas) buscaProdutoPorNome("bebidas",
                        "nome", novoProduto, Bebidas.class);
                String novaBebidaNome = novaBebida.getNome();
                Map<String, ?> pedidosBebidas = pedido.getAllPedidoProduto("bebida");
                String bebidaKey = hashBebida.generateKeyByObject(pedido, novaBebida);

                if (pedidosBebidas.containsKey(bebidaKey)) {
                    PedidosBebidas currPedidoBebida = (PedidosBebidas) pedidosBebidas.get(bebidaKey);
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
                    pedido.setPedidoProduto(bebidaKey, insert);
                    novaBebida.setPedidoProduto(bebidaKey, insert);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    return String.format("Pedido ID: %s. | Alteração: Adicionado %s pizzas de %s",
                            pedidoId, novaQuantidade, novaBebidaNome);
                }
            case "sobremesa":
                ProdutoHashMap hashSobremesa = new ProdutoHashMap();
                Sobremesas novaSobremesa = (Sobremesas) buscaProdutoPorNome("bebidas",
                        "nome", novoProduto, Sobremesas.class);
                String novaSobremesaNome = novaSobremesa.getNome();
                Map<String, ?> pedidosSobremesas = pedido.getAllPedidoProduto("sobremesa");
                String sobremesaKey = hashSobremesa.generateKeyByObject(pedido, novaSobremesa);

                if (pedidosSobremesas.containsKey(sobremesaKey)) {
                    PedidosSobremesas currPedidoSobremesa = (PedidosSobremesas) pedidosSobremesas.get(sobremesaKey);
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
                    pedido.setPedidoProduto(sobremesaKey, insert);
                    novaSobremesa.setPedidoProduto(sobremesaKey, insert);

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
