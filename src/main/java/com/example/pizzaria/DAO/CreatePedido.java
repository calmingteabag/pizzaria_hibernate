package com.example.pizzaria.DAO;

import com.example.pizzaria.Models.*;
import com.example.pizzaria.Utils.HibernateSession;
import com.example.pizzaria.testes_aprendizado.BebidasBusca;
import com.example.pizzaria.testes_aprendizado.PizzasBusca;
import com.example.pizzaria.testes_aprendizado.SobremesasBusca;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CreatePedido {

    public CreatePedido() {
    };

    public void testInsert() {
        Session session = HibernateSession.getSession();
        ItemKeyForHashMap keyGen = new ItemKeyForHashMap();

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
        String keyPizza_1 = keyGen.hashKeyGenerator(pedido, pizza_1);
        String keyPizza_2 = keyGen.hashKeyGenerator(pedido, pizza_2);

        BebidasBusca bebidaBusca = new BebidasBusca();
        Bebidas bebida_1 = bebidaBusca.buscarBebidaPorNome("bebidas", "nome", "coca");
        Bebidas bebida_2 = bebidaBusca.buscarBebidaPorNome("bebidas", "nome", "groselha");
        String nomeBebida_1 = bebida_1.getNome();
        String nomeBebida_2 = bebida_2.getNome();
        String keyBebida_1 = keyGen.hashKeyGenerator(pedido, bebida_1);
        String keyBebida_2 = keyGen.hashKeyGenerator(pedido, bebida_2);

        SobremesasBusca sobremesaBusca = new SobremesasBusca();
        Sobremesas sobremesa_1 = sobremesaBusca.buscarSobremesaPorNome("sobremesas", "nome", "sonho");
        Sobremesas sobremesa_2 = sobremesaBusca.buscarSobremesaPorNome("sobremesas", "nome", "rapadura");
        String nomeSobremesa_1 = sobremesa_1.getNome();
        String nomeSobremesa_2 = sobremesa_2.getNome();
        String keySobremesa_1 = keyGen.hashKeyGenerator(pedido, sobremesa_1);
        String keySobremesa_2 = keyGen.hashKeyGenerator(pedido, sobremesa_2);

        pedidoPizza_1.setPedido(pedido);
        pedidoPizza_1.setProduto(pizza_1);
        pedidoPizza_1.setQty(10);
        pedidoPizza_1.setNomeProduto(nomePizza_1);
        pizza_1.setPedido(keyPizza_1, pedidoPizza_1);

        pedidoPizza_2.setPedido(pedido);
        pedidoPizza_2.setProduto(pizza_2);
        pedidoPizza_2.setQty(6);
        pedidoPizza_2.setNomeProduto(nomePizza_2);

        pizza_2.setPedido(keyPizza_2, pedidoPizza_2);

        pedidoBebida_1.setPedido(pedido);
        pedidoBebida_1.setQty(2);
        pedidoBebida_1.setProduto(bebida_1);
        pedidoBebida_1.setNomeProduto(nomeBebida_1);
        bebida_1.setPedido(keyBebida_1, pedidoBebida_1);

        pedidoBebida_2.setPedido(pedido);
        pedidoBebida_2.setQty(4);
        pedidoBebida_2.setProduto(bebida_2);
        pedidoBebida_2.setNomeProduto(nomeBebida_2);
        bebida_2.setPedido(keyBebida_2, pedidoBebida_2);

        pedidoSobremesa_1.setPedido(pedido);
        pedidoSobremesa_1.setQty(5);
        pedidoSobremesa_1.setProduto(sobremesa_1);
        pedidoSobremesa_1.setNomeProduto(nomeSobremesa_1);
        sobremesa_1.setPedido(keySobremesa_1, pedidoSobremesa_1);

        pedidoSobremesa_2.setPedido(pedido);
        pedidoSobremesa_2.setQty(9);
        pedidoSobremesa_2.setProduto(sobremesa_2);
        pedidoSobremesa_2.setNomeProduto(nomeSobremesa_2);
        sobremesa_2.setPedido(keySobremesa_2, pedidoSobremesa_2);

        pedido.setPedidoProduto(nomePizza_1, pedidoPizza_1);
        pedido.setPedidoProduto(nomePizza_2, pedidoPizza_2);
        pedido.setPedidoProduto(nomeBebida_1, pedidoBebida_1);
        pedido.setPedidoProduto(nomeBebida_2, pedidoBebida_2);
        pedido.setPedidoProduto(nomeSobremesa_1, pedidoSobremesa_1);
        pedido.setPedidoProduto(nomeSobremesa_2, pedidoSobremesa_2);

        Transaction transaction = session.beginTransaction();
        session.merge(pedido);
        transaction.commit();
    }
}
