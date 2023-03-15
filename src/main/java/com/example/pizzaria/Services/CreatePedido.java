package com.example.pizzaria.Services;

import com.example.pizzaria.DAO.DB_Search;
import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class CreatePedido {

    public CreatePedido() {
    };

    /*
     * 100% probabilidade de ler as coisas de algum json. O que pensei é o frontend
     * mandar algo assim:
     * 
     * {
     * 
     * "clienteid": "12232",
     * "pedido" : {
     * 
     * "pizzas" : ["mucarela", "calabresa", "catupiry"],
     * "bebidas" : ["agua", "breja"],
     * "sobremesas" ["pacoca", "quindim"],
     * }
     * }
     */
    public void readPedido() {

    };

    public void testInsert() {
        Session session = HibernateSession.getSession();

        DB_Search search_client = new DB_Search("clientes", Clientes.class);
        Clientes cliente = search_client.getClientById("clienteId", 1); // get o cliente
        Pedidos pedido = new Pedidos(cliente); // cria um novo pedido já com o cliente que fez o pedido

        // criar os PedidosXXXXXX pra inserir
        PedidosPizzas pedidoPizza_1 = new PedidosPizzas();
        PedidosPizzas pedidoPizza_2 = new PedidosPizzas();
        PedidosBebidas pedidoBebida_1 = new PedidosBebidas();
        PedidosBebidas pedidoBebida_2 = new PedidosBebidas();
        PedidosSobremesas pedidoSobremesa_1 = new PedidosSobremesas();
        PedidosSobremesas pedidoSobremesa_2 = new PedidosSobremesas();

        // search o db e pega os elementos
        DB_Search search_pizza = new DB_Search("pizzas", Pizzas.class);
        List<?> pizzaResult_1 = search_pizza.getItemClassList("nome",
                "calabresa", false);
        List<?> pizzaResult_2 = search_pizza.getItemClassList("nome",
                "portuguesa", false);
        Pizzas pizza_1 = (Pizzas) pizzaResult_1.get(0);
        Pizzas pizza_2 = (Pizzas) pizzaResult_2.get(0);

        DB_Search search_bebida = new DB_Search("bebidas", Bebidas.class);
        List<?> bebidaResult_1 = search_bebida.getItemClassList("nome",
                "coca", false);
        List<?> bebidaResult_2 = search_bebida.getItemClassList("nome",
                "groselha", false);
        Bebidas bebida_1 = (Bebidas) bebidaResult_1.get(0);
        Bebidas bebida_2 = (Bebidas) bebidaResult_2.get(0);

        DB_Search search_sobremesa = new DB_Search("sobremesas", Sobremesas.class);
        List<?> sobremesaResult_1 = search_sobremesa.getItemClassList("nome",
                "sonho", false);
        List<?> sobremesaResult_2 = search_sobremesa.getItemClassList("nome",
                "rapadura", false);
        Sobremesas sobremesa_1 = (Sobremesas) sobremesaResult_1.get(0);
        Sobremesas sobremesa_2 = (Sobremesas) sobremesaResult_2.get(0);

        // montamos a relação adicionando o pedido e a pizza/bebida/sobremesa
        pedidoPizza_1.setPedido(pedido);
        pedidoPizza_1.setQty(10);
        pedidoPizza_1.setPizza(pizza_1);

        pedidoPizza_2.setPedido(pedido);
        pedidoPizza_2.setQty(6);
        pedidoPizza_2.setPizza(pizza_2);

        pedidoBebida_1.setPedido(pedido);
        pedidoBebida_1.setQty(2);
        pedidoBebida_1.setBebida(bebida_1);

        pedidoBebida_2.setPedido(pedido);
        pedidoBebida_2.setQty(4);
        pedidoBebida_2.setBebida(bebida_2);

        pedidoSobremesa_1.setPedido(pedido);
        pedidoSobremesa_1.setQty(5);
        pedidoSobremesa_1.setSobremesa(sobremesa_1);

        pedidoSobremesa_2.setPedido(pedido);
        pedidoSobremesa_2.setQty(9);
        pedidoSobremesa_2.setSobremesa(sobremesa_2);

        // adicionamos o PedidoPizza em Pedidos e em Pizzas,
        // PedidoBebida em Pedidos e Bebidas e finalmente
        // PedidoSobremesa em Pedidos e Sobremesas.

        // lembrando que são lists
        List<PedidosPizzas> pedidoPizzaList = new ArrayList<>();
        pedidoPizzaList.add(pedidoPizza_1);
        pedidoPizzaList.add(pedidoPizza_2);
        pedido.setPedidosPizzas(pedidoPizzaList);
        pizza_1.setPedidosPizzas(pedidoPizzaList);
        pizza_2.setPedidosPizzas(pedidoPizzaList);

        List<PedidosBebidas> pedidoBebidaList = new ArrayList<>();
        pedidoBebidaList.add(pedidoBebida_1);
        pedidoBebidaList.add(pedidoBebida_2);
        pedido.setPedidosBebidas(pedidoBebidaList);
        bebida_1.setPedidosBebidas(pedidoBebidaList);
        bebida_2.setPedidosBebidas(pedidoBebidaList);

        List<PedidosSobremesas> pedidoSobremesaList = new ArrayList<>();
        pedidoSobremesaList.add(pedidoSobremesa_1);
        pedidoSobremesaList.add(pedidoSobremesa_2);
        pedido.setPedidosSobremesas(pedidoSobremesaList);
        sobremesa_1.setPedidosSobremesas(pedidoSobremesaList);
        sobremesa_2.setPedidosSobremesas(pedidoSobremesaList);

        Transaction transaction = session.beginTransaction();
        // session.merge(pedidoPizza_1);
        // session.merge(pedidoPizza_2);
        // session.merge(pedidoBebida_1);
        // session.merge(pedidoBebida_2);
        // session.merge(pedidoSobremesa_1);
        // session.merge(pedidoSobremesa_2);
        session.merge(pedido);
        transaction.commit();
    }
}
