package com.example.pizzaria.DAO;

import com.example.pizzaria.Models.*;
import com.example.pizzaria.Services.CalculaValoresPedido;
import com.example.pizzaria.Services.ProdutoHashMap;
import com.example.pizzaria.Utils.HibernateSession;
import com.example.pizzaria.testes_aprendizado.BebidasBusca;
import com.example.pizzaria.testes_aprendizado.PizzasBusca;
import com.example.pizzaria.testes_aprendizado.SobremesasBusca;

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

    public void testInsert() {
        Session session = HibernateSession.getSession();
        Transaction transaction = session.beginTransaction();
        ProdutoHashMap keyGen = new ProdutoHashMap();

        Clientes cliente = session.get(Clientes.class, 1); // get o cliente
        Pedidos pedido = new Pedidos(cliente); // cria um novo pedido já com o cliente que fez o pedido
        pedido.setNomeCliente(cliente.getNome());
        session.persist(pedido); // watch out this code
        cliente.setPedido(pedido.getId(), pedido); // and this too

        PedidosPizzas pedidoPizza_1 = new PedidosPizzas();
        PedidosPizzas pedidoPizza_2 = new PedidosPizzas();
        PedidosBebidas pedidoBebida_1 = new PedidosBebidas();
        PedidosBebidas pedidoBebida_2 = new PedidosBebidas();
        PedidosSobremesas pedidoSobremesa_1 = new PedidosSobremesas();
        PedidosSobremesas pedidoSobremesa_2 = new PedidosSobremesas();

        PizzasBusca pizzaBusca = new PizzasBusca();
        Pizzas pizza_1 = pizzaBusca.buscarPizzaPorNome("pizzas", "nome", "calabresa");
        Pizzas pizza_2 = pizzaBusca.buscarPizzaPorNome("pizzas", "nome", "portuguesa");
        String nomePizza_1 = pizza_1.getNome();
        String nomePizza_2 = pizza_2.getNome();
        String keyPizza_1 = keyGen.generateKeyByObject(pedido, pizza_1);
        String keyPizza_2 = keyGen.generateKeyByObject(pedido, pizza_2);

        BebidasBusca bebidaBusca = new BebidasBusca();
        Bebidas bebida_1 = bebidaBusca.buscarBebidaPorNome("bebidas", "nome", "coca");
        Bebidas bebida_2 = bebidaBusca.buscarBebidaPorNome("bebidas", "nome", "groselha");
        String nomeBebida_1 = bebida_1.getNome();
        String nomeBebida_2 = bebida_2.getNome();
        String keyBebida_1 = keyGen.generateKeyByObject(pedido, bebida_1);
        String keyBebida_2 = keyGen.generateKeyByObject(pedido, bebida_2);

        SobremesasBusca sobremesaBusca = new SobremesasBusca();
        Sobremesas sobremesa_1 = sobremesaBusca.buscarSobremesaPorNome("sobremesas", "nome", "sonho");
        Sobremesas sobremesa_2 = sobremesaBusca.buscarSobremesaPorNome("sobremesas", "nome", "rapadura");
        String nomeSobremesa_1 = sobremesa_1.getNome();
        String nomeSobremesa_2 = sobremesa_2.getNome();
        String keySobremesa_1 = keyGen.generateKeyByObject(pedido, sobremesa_1);
        String keySobremesa_2 = keyGen.generateKeyByObject(pedido, sobremesa_2);

        pedidoPizza_1.setPedido(pedido);
        pedidoPizza_1.setProduto(pizza_1);
        pedidoPizza_1.setQty(10);
        pedidoPizza_1.setNomeProduto(nomePizza_1);
        pedidoPizza_1.setMapKey(keyPizza_1);
        pedidoPizza_1.setTotalProduto(10 * pizza_1.getPreco());
        pizza_1.setPedidoProduto(keyPizza_1, pedidoPizza_1);

        pedidoPizza_2.setPedido(pedido);
        pedidoPizza_2.setProduto(pizza_2);
        pedidoPizza_2.setQty(6);
        pedidoPizza_2.setNomeProduto(nomePizza_2);
        pedidoPizza_2.setMapKey(keyPizza_2);
        pedidoPizza_2.setTotalProduto(6 * pizza_2.getPreco());
        pizza_2.setPedidoProduto(keyPizza_2, pedidoPizza_2);

        pedidoBebida_1.setPedido(pedido);
        pedidoBebida_1.setQty(2);
        pedidoBebida_1.setProduto(bebida_1);
        pedidoBebida_1.setNomeProduto(nomeBebida_1);
        pedidoBebida_1.setMapKey(keyBebida_1);
        pedidoBebida_1.setTotalProduto(2 * bebida_1.getPreco());
        bebida_1.setPedidoProduto(keyBebida_1, pedidoBebida_1);

        pedidoBebida_2.setPedido(pedido);
        pedidoBebida_2.setQty(4);
        pedidoBebida_2.setProduto(bebida_2);
        pedidoBebida_2.setNomeProduto(nomeBebida_2);
        pedidoBebida_2.setMapKey(keyBebida_2);
        pedidoBebida_2.setTotalProduto(4 * bebida_2.getPreco());
        bebida_2.setPedidoProduto(keyBebida_2, pedidoBebida_2);

        pedidoSobremesa_1.setPedido(pedido);
        pedidoSobremesa_1.setQty(5);
        pedidoSobremesa_1.setProduto(sobremesa_1);
        pedidoSobremesa_1.setNomeProduto(nomeSobremesa_1);
        pedidoSobremesa_1.setMapKey(keySobremesa_1);
        pedidoSobremesa_1.setTotalProduto(5 * sobremesa_1.getPreco());
        sobremesa_1.setPedidoProduto(keySobremesa_1, pedidoSobremesa_1);

        pedidoSobremesa_2.setPedido(pedido);
        pedidoSobremesa_2.setQty(9);
        pedidoSobremesa_2.setProduto(sobremesa_2);
        pedidoSobremesa_2.setNomeProduto(nomeSobremesa_2);
        pedidoSobremesa_2.setMapKey(keySobremesa_2);
        pedidoSobremesa_2.setTotalProduto(9 * sobremesa_2.getPreco());
        sobremesa_2.setPedidoProduto(keySobremesa_2, pedidoSobremesa_2);

        pedido.setPedidoProduto(keyPizza_1, pedidoPizza_1);
        pedido.setPedidoProduto(keyPizza_2, pedidoPizza_2);
        pedido.setPedidoProduto(keyBebida_1, pedidoBebida_1);
        pedido.setPedidoProduto(keyBebida_2, pedidoBebida_2);
        pedido.setPedidoProduto(keySobremesa_1, pedidoSobremesa_1);
        pedido.setPedidoProduto(keySobremesa_2, pedidoSobremesa_2);
        pedido.setDesconto(10);

        session.merge(pedido);
        transaction.commit();
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
