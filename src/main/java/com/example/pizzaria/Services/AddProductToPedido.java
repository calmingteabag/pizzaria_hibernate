package com.example.pizzaria.Services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.pizzaria.DAO.BuscaProdutos;
import com.example.pizzaria.DAO.CreatePedido;
import com.example.pizzaria.Interfaces.Produtos;
import com.example.pizzaria.Models.*;
import com.example.pizzaria.Utils.HibernateSession;

public class AddProductToPedido {

    public AddProductToPedido() {
    };

    public String addProduct(String nomeTabela, String nomeColuna,
            String nomeProduto, Class<? extends Produtos> classeProduto,
            String idPedido, String idCliente, String quantidadePedido,
            String tipoProduto) {

        Session session = HibernateSession.getSession();
        Transaction transaction = session.beginTransaction();
        ProdutoHashMap hashGen = new ProdutoHashMap();
        CreatePedido create = new CreatePedido();
        BuscaProdutos busca = new BuscaProdutos();
        String[] produtos = { "pizza", "bebida", "sobremesa" };

        Pedidos pedido = session.get(Pedidos.class, Integer.parseInt(idPedido));

        // Checa se o cliente e o pedido existem separadamente
        try {
            session.get(Clientes.class, Integer.parseInt(idCliente));
            session.get(Pedidos.class, Integer.parseInt(idPedido));
        } catch (Exception e) {
            return "Cliente e/ou pedido inexistente";
        }

        // Checa o pedido existe para o cliente específico
        Clientes cliente = session.get(Clientes.class, Integer.parseInt(idCliente));
        if (cliente.getOnePedido(Integer.parseInt(idPedido)) == null) {
            return "Cliente e/ou pedido inexistente";
        }

        int quantidadeProduto = Integer.parseInt(quantidadePedido);
        Produtos produto = busca.buscaProdutoPorNome(nomeTabela,
                nomeColuna, nomeProduto, classeProduto);

        String mapKey = hashGen.generateKeyByObject(pedido, produto);
        String respostaHtml = "";

        // Se pedidoProduto não existe, adicionamos um novo em Pedidos e em Pizzas.
        // Caso contrário, apenas mudamos a quantidade.
        switch (tipoProduto) {
            case "pizza":

                if (pedido.getOnePedidoProduto(mapKey, "pizza") == null) {
                    PedidosPizzas pedidoPizza = new PedidosPizzas(pedido, (Pizzas) produto,
                            quantidadeProduto, produto.getNome(), mapKey,
                            quantidadeProduto * produto.getPreco());

                    pedido.setPedidoProduto(mapKey, pedidoPizza);
                    produto.setPedidoProduto(mapKey, pedidoPizza);
                    create.setPedidosTotais(pedido.getId(), produtos);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    respostaHtml = String.format("Produto %s adicionado ao pedido ID: %s", nomeProduto, idPedido);
                    break;
                }

                pedido.getOnePedidoProduto(mapKey, "pizza").setQty(quantidadeProduto);
                produto.getOnePedidoProduto(mapKey).setQty(quantidadeProduto);
                respostaHtml = String.format("Produto %s já existe no pedido ID: %s. Quantidade mudada para %s.",
                        nomeProduto, idPedido, quantidadePedido);
                break;

            case "bebida":

                if (pedido.getOnePedidoProduto(mapKey, "bebida") == null) {
                    PedidosBebidas pedidoBebida = new PedidosBebidas(pedido, (Bebidas) produto,
                            quantidadeProduto, produto.getNome(), mapKey,
                            quantidadeProduto * produto.getPreco());

                    pedido.setPedidoProduto(mapKey, pedidoBebida);
                    produto.setPedidoProduto(mapKey, pedidoBebida);
                    create.setPedidosTotais(pedido.getId(), produtos);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();

                    respostaHtml = String.format("Produto %s adicionado ao pedido ID: %s", nomeProduto, idPedido);
                    break;
                }

                pedido.getOnePedidoProduto(mapKey, "bebida").setQty(quantidadeProduto);
                produto.getOnePedidoProduto(mapKey).setQty(quantidadeProduto);
                respostaHtml = String.format("Produto %s já existe no pedido ID: %s. Quantidade mudada para %s.",
                        nomeProduto, idPedido, quantidadePedido);
                break;

            case "sobremesa":

                if (pedido.getOnePedidoProduto(mapKey, "bebida") == null) {
                    PedidosSobremesas pedidoSobremesa = new PedidosSobremesas(pedido, (Sobremesas) produto,
                            quantidadeProduto, produto.getNome(), mapKey,
                            quantidadeProduto * produto.getPreco());

                    pedido.setPedidoProduto(mapKey, pedidoSobremesa);
                    produto.setPedidoProduto(mapKey, pedidoSobremesa);
                    create.setPedidosTotais(pedido.getId(), produtos);

                    session.merge(pedido);
                    transaction.commit();
                    session.close();
                    respostaHtml = String.format("Produto %s adicionado ao pedido ID: %s", nomeProduto, idPedido);
                    break;
                }

                pedido.getOnePedidoProduto(mapKey, "sobremesa").setQty(quantidadeProduto);
                produto.getOnePedidoProduto(mapKey).setQty(quantidadeProduto);
                respostaHtml = String.format("Produto %s já existe no pedido ID: %s. Quantidade mudada para %s.",
                        nomeProduto, idPedido, quantidadePedido);
                break;

        }
        return respostaHtml;
    }
}
