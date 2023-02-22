package com.example.pizzaria;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.boot.MetadataSources;
import org.hibernate.query.SelectionQuery;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.SessionFactory;

public class HibernateTest {

    public static void main(String[] args) {

        ExecuteSearch search = new ExecuteSearch("produtos_a", "nome_produto", Produtos.class);
        String result = search.searchNameDb("'marguerita'");

        System.out.println(result);

        // StandardServiceRegistry registry = new
        // StandardServiceRegistryBuilder().configure().build();
        // final SessionFactory sessionFactory = new
        // MetadataSources(registry).buildMetadata().buildSessionFactory();

        // Session session = sessionFactory.openSession();
        // Transaction transaction = session.beginTransaction();

        // Produtos p1 = new Produtos("napolitana", 50, "pizza napolitana");
        // Produtos p2 = new Produtos("marguerita", 55, "Pizza marguerita");

        // session.persist(p1);
        // session.persist(p2);

        // Search por id
        // Produtos produto = session.find(Produtos.class, 6);
        // System.out.println("########################################################################");
        // System.out.println(produto.getDescricao());
        // System.out.println(produto.getId());
        // System.out.println("########################################################################");

        // now, how tf I do selection query

        // Produtos p3 = (Produtos) session
        // .createQuery("SELECT nome_produto FROM Produtos WHERE nome_produto =
        // marguerita", Produtos.class);
        // System.out.println(p3);

        // Produtos p4 = (Produtos) session.createSelectionQuery("SELECT marguerita FROM
        // ")
        // transaction.commit();

        // SelectionQuery<Produtos> produto_2 = session
        // .createNativeQuery("SELECT * FROM produtos_a WHERE nome_produto =
        // 'marguerita'",
        // Produtos.class);

        // System.out.println("########################################################################");
        // // System.out.println(produto_2);

        // List<Produtos> results = produto_2.getResultList();

        // Produtos nomeProduto = results.get(0);
        // String nomeProdutoString = results.get(0).getNome();

        // System.out.println(nomeProdutoString);

        // System.out.println("########################################################################");
        // session.close();
        // session.close();

    };
}
