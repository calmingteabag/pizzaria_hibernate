package com.example.pizzaria;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;

public class HibernateTest {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Produtos p1 = new Produtos("napolitana", 50, "pizza napolitana");
        Produtos p2 = new Produtos("marguerita", 55, "Pizza marguerita");

        // session.persist(p1);
        // session.persist(p2);

        // Search por id
        Produtos produto = session.find(Produtos.class, 1);
        System.out.println("########################################################################");
        System.out.println(produto.getDescricao());
        System.out.println(produto.getId());
        System.out.println("########################################################################");

        // transaction.commit();

        session.close();
        session.close();

    };
}
