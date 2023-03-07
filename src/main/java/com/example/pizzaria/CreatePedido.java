package com.example.pizzaria;

import com.example.pizzaria.DB_Operations.DB_Search;
import com.example.pizzaria.Entities.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;

public class CreatePedido {

    public CreatePedido() {
    };

    public void readPedido() {

    };

    public void testInsert() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();

        DB_Search search_client = new DB_Search("clientes", Clientes.class);
        Clientes cliente = search_client.getClientById("clienteId", 1);

        List<Pizzas> pizzas = new ArrayList<Pizzas>();
        List<Bebidas> bebidas = new ArrayList<Bebidas>();
        List<Sobremesas> sobremesas = new ArrayList<Sobremesas>();

        DB_Search search_pizza = new DB_Search("pizzas", Pizzas.class);
        List<?> pizzaResult = search_pizza.getProductClassList("nome", "calabresa");

        DB_Search search_bebidas = new DB_Search("bebidas", Bebidas.class);
        List<?> bebidaResult = search_bebidas.getProductClassList("nome", "agua");

        DB_Search search_sobrmesas = new DB_Search("sobremesas", Sobremesas.class);
        List<?> sobremesaResult = search_sobrmesas.getProductClassList("nome", "sonho");

        pizzas.add((Pizzas) pizzaResult.get(0));
        bebidas.add((Bebidas) bebidaResult.get(0));
        sobremesas.add((Sobremesas) sobremesaResult.get(0));
        /*
         * Quando alguem faz um pedido, varias coisas precisam acontecer
         * 
         * Criar um objeto Pedidos pra ser inserido no Db. Fazemos isso instanciado
         * Pedidos com argumentos
         * (e depois adcionamos no db via session.merge e transaction.commit)
         * 
         * Esse objeto vai ter que ser preenchido com o nome do cliente e todos os
         * pedidos e todos os dados vão ser buscados no DB.
         * 
         * O cliente pode ser pego com
         * Clientes cliente = session.get(Clientes.class, clienteId)
         * 
         * As pizzas/bebidas/sobremesas que vão nos pedidos são uma ArrayList de
         * Entidades.
         * Mas qual entidades? Podemos fazer algo assim
         * 
         * List<Pizzas> pizzas = new ArrayList<Pizzas>() -> cria uma lista, que é o que
         * pede na entidade Pedidos
         * 
         * Depois fazemos uma query no db, por exemplo
         * SelectionQuery<?> dbQuery = session.createNativeQuery("SELECT FROM etc etc",
         * Pizzas.class)
         * 
         * PAssamos resultado pra uma list com
         * List<?> dbSearchResultList = dbQuery.getResultList();
         * 
         * E pegamos o objeto que queremos com
         * Pizza dbObject = dbSearchResultList.get(0);
         * 
         * Colocamos isso na List<Pizzas>
         * pizzas.add(dbObject) -> quantas forem precisas, é uma ArrayList afinal
         * 
         * e FINALMENTE, adicionamos isso nos Pedidos, por exemplo
         * Pedidos pedido = new Pedido(cliente, pizzas, etc, etc)
         * onde cliente e pizzas são coisas que vc pegou do DB.
         * 
         * Cansativo, mas é por ai
         * 
         * 
         */

        Pedidos novoPedido = new Pedidos(0, cliente, pizzas, bebidas, sobremesas);
        Transaction transaction = session.beginTransaction();
        session.merge(novoPedido);
        transaction.commit();

    }
}
