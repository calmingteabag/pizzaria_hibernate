package com.example.pizzaria;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;

public class DB_Populate {

    private String tableName;
    private String testColumnName;
    private Class<Produtos> entity;

    public DB_Populate(String tableName, String testColumn, Class<Produtos> entity) {
        this.tableName = tableName;
        this.testColumnName = testColumn;
        this.entity = entity;
    };

    public void populateDBMockupData() {
        // mockup data
        ArrayList<ArrayList<String>> mockupData = new ArrayList<>();
        mockupData.add(new ArrayList<>(List.of("calabresa", "Pizza de Calabresa", "70")));
        mockupData.add(new ArrayList<>(List.of("portuguesa", "Pizza Portuguesa", "65")));
        mockupData.add(new ArrayList<>(List.of("marguerita", "Pizza Marguerita", "50")));
        mockupData.add(new ArrayList<>(List.of("frango_catupiry", "Pizza de Frango com Catupiry", "66")));
        mockupData.add(new ArrayList<>(List.of("mucarela", "Pizza de Muçarela", "50")));
        mockupData.add(new ArrayList<>(List.of("brigadeiro", "Doce de Brigadeiro", "10")));
        mockupData.add(new ArrayList<>(List.of("doce_leite", "Doce de Leite", "5")));
        mockupData.add(new ArrayList<>(List.of("rapadura", "Rapadura", "15")));
        mockupData.add(new ArrayList<>(List.of("pacoca", "Paçoca", "5")));
        mockupData.add(new ArrayList<>(List.of("sonho", "Sonho", "20")));
        mockupData.add(new ArrayList<>(List.of("guarana", "Guaraná 2L", "20")));
        mockupData.add(new ArrayList<>(List.of("coca", "Coca Cola", "20")));
        mockupData.add(new ArrayList<>(List.of("breja", "Cevada com gás 1L", "10")));
        mockupData.add(new ArrayList<>(List.of("agua", "Agua da bica da SABESP", "5")));
        mockupData.add(new ArrayList<>(List.of("groselha", "Groselha (50% água, 50% açucar, 1% groselha)", "30")));

        // Setup session and transaction
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        // Insert mock data to db
        for (int i = 0; i < mockupData.size(); i++) {
            Transaction transaction = session.beginTransaction();
            String productName;
            String productDescription;
            int productPrice;

            productName = mockupData.get(i).get(0);
            productPrice = Integer.parseInt(mockupData.get(i).get(2));
            productDescription = mockupData.get(i).get(1);

            Produtos insertProdutos = new Produtos(productName, productPrice, productDescription);
            session.merge(insertProdutos);
            transaction.commit();

        }
        ;
        session.close();

    };
}
