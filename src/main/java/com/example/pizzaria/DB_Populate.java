package com.example.pizzaria;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class DB_Populate {

    private String tableName;

    public DB_Populate(String tableName) {
        this.tableName = tableName;
    };

    public void populateDBMockupData() {
        // mockup data
        ArrayList<ArrayList<String>> mockupData = new ArrayList<>();
        mockupData.add(new ArrayList<>(List.of("calabresa", "Pizza de Calabresa", "70")));
        mockupData.add(new ArrayList<>(List.of("portuguesa", "Pizza Portuguesa", "65")));
        mockupData.add(new ArrayList<>(List.of("marguerita", "Pizza Marguerita", "50")));
        mockupData.add(new ArrayList<>(List.of("frango_catupiry", "Pizza de Frango com Catupiry", "66")));
        mockupData.add(new ArrayList<>(List.of("mucarela", "Pizza de Muçarela", "50")));
        mockupData.add(new ArrayList<>(List.of("milho_catupiry", "Pizza de Milho com Catupiry", "65")));
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

        /*
         * O session.merge() funciona para evitar entradas duplicadas, mas retorna
         * uma exception que trava o spring boot. Uma das sugestões foi modificar a
         * forma como spring trata a exceção especifica, que achei complicada
         * demais para esse projeto. A segunda sugestão achei mais interessante,
         * pois ela faz uma consulta no banco de dados e retorna um boolean se
         * achar/ não achar a tabela com o nome especificado.
         * 
         * Update: Descobri que a tabela é criada em outro lugar, portanto o teste
         * para ver se ela existe sempre retorna true. Como a tabela sempre é criada,
         * resolvi modificar a query e checar
         */

        String checkSQLQuery = String.format(
                "SELECT COUNT(*) FROM %s",
                tableName);

        Query<Integer> checkSQLQueryResult = session.createNativeQuery(checkSQLQuery, Integer.class);
        Integer rowsQty = checkSQLQueryResult.getSingleResult();
        System.out.println(rowsQty);
        // Insert mock data to db
        if (rowsQty == 0) {

            for (int i = 0; i < mockupData.size(); i++) {
                Transaction transaction = session.beginTransaction();
                String productName;
                String productDescription;
                int productPrice;

                productName = mockupData.get(i).get(0);
                productPrice = Integer.parseInt(mockupData.get(i).get(2));
                productDescription = mockupData.get(i).get(1);

                Produtos insertProdutos = new Produtos(productName, productPrice,
                        productDescription);
                session.merge(insertProdutos);
                transaction.commit();

            }
            ;
            session.close();
        }

    };
}
