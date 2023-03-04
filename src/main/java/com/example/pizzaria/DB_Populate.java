package com.example.pizzaria;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;

import org.hibernate.query.Query;

import com.example.pizzaria.Entities.*;

public class DB_Populate {

    private ArrayList<ArrayList<String[]>> mockupProdutos = new ArrayList<>();
    private StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private Session session = sessionFactory.openSession();

    public DB_Populate() {
    };

    public void getProdutos() {
        System.out.println(mockupProdutos);
        System.out.println(mockupProdutos.get(0).get(0)[1]);
    }

    public void populateDBMockupData() {
        // mockup data
        ArrayList<String[]> pizzasTest = new ArrayList<>();
        pizzasTest.add(new String[] { "calabresa", "Pizza de Calabresa", "70" });
        pizzasTest.add(new String[] { "portuguesa", "Pizza Portuguesa", "65" });
        pizzasTest.add(new String[] { "marguerita", "Pizza Marguerita", "50" });
        pizzasTest.add(new String[] { "frango_catupiry", "Pizza de Frango com Catupiry", "66" });
        pizzasTest.add(new String[] { "mucarela", "Pizza de Muçarela", "50" });
        pizzasTest.add(new String[] { "milho_catupiry", "Pizza de Milho com Catupiry", "65" });

        ArrayList<String[]> bebidasTest = new ArrayList<>();
        bebidasTest.add(new String[] { "guarana", "Guaraná 2L", "20" });
        bebidasTest.add(new String[] { "coca", "Coca Cola", "20" });
        bebidasTest.add(new String[] { "breja", "Cevada com gás 1L", "10" });
        bebidasTest.add(new String[] { "agua", "Agua da bica da SABESP", "5" });
        bebidasTest.add(new String[] { "groselha", "Groselha (50% água, 50% açucar, 1% groselha)", "30" });

        ArrayList<String[]> sobremesasTest = new ArrayList<>();
        sobremesasTest.add(new String[] { "sonho", "Sonho", "20" });
        sobremesasTest.add(new String[] { "brigadeiro", "Doce de Brigadeiro", "10" });
        sobremesasTest.add(new String[] { "doce_leite", "Doce de Leite", "5" });
        sobremesasTest.add(new String[] { "rapadura", "Rapadura", "15" });
        sobremesasTest.add(new String[] { "pacoca", "Paçoca", "5" });

        ArrayList<ArrayList<String>> mockupSobremesas = new ArrayList<>();
        mockupSobremesas.add(new ArrayList<>(List.of("sonho", "Sonho", "20")));
        mockupSobremesas.add(new ArrayList<>(List.of("brigadeiro", "Doce de Brigadeiro", "10")));
        mockupSobremesas.add(new ArrayList<>(List.of("doce_leite", "Doce de Leite", "5")));
        mockupSobremesas.add(new ArrayList<>(List.of("rapadura", "Rapadura", "15")));
        mockupSobremesas.add(new ArrayList<>(List.of("pacoca", "Paçoca", "5")));

        mockupProdutos.add(new ArrayList<>(pizzasTest));
        mockupProdutos.add(new ArrayList<>(bebidasTest));
        mockupProdutos.add(new ArrayList<>(sobremesasTest));
    };

    public Boolean checkTableFill(String tableName) {
        String checkSQLQuery = String.format(
                "SELECT COUNT(*) FROM %s",
                tableName);

        Query<Integer> checkSQLQueryResult = session.createNativeQuery(checkSQLQuery, Integer.class);
        Integer rowsQty = checkSQLQueryResult.getSingleResult();

        if (rowsQty > 0) {
            return true;
        }
        return false;
    };

    public void queryInsert(ArrayList<Object> entidades, String tableName) {
        // loop thourgh entities
        // loop thourgh arraylist that contains values for each field
        for (Object obj : entidades) {
            if (obj instanceof Pizzas && checkTableFill(tableName)) {
                // loop through mockup of pizza insertion data then
                // another loop through all insertData
                ArrayList<String[]> pizzasToInsert = mockupProdutos.get(0);
                for (int i = 0; i < pizzasToInsert.size(); i++) {
                    String nome = pizzasToInsert.get(i)[0];
                    String descricao = pizzasToInsert.get(i)[1];
                    int preco = Integer.parseInt(pizzasToInsert.get(i)[2]);

                    ((Pizzas) obj).setNome(nome);
                    ((Pizzas) obj).setDescricao(descricao);
                    ((Pizzas) obj).setPreco(preco);

                    Transaction transaction = session.beginTransaction();
                    session.merge((Pizzas) obj);
                    transaction.commit();
                }

            }
            session.close();
        }
    };
}
