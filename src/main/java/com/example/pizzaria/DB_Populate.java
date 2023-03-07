package com.example.pizzaria;

import com.example.pizzaria.Entities.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.Query;

public class DB_Populate {

    // private ArrayList<ArrayList<String[]>> mockupProdutos = new ArrayList<>();

    private HashMap<String, ArrayList<String[]>> produtos = new HashMap<>();

    private StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private Session session = sessionFactory.openSession();

    public DB_Populate() {
    };

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

        produtos.put("pizzas", new ArrayList<>(pizzasTest));
        produtos.put("bebidas", new ArrayList<>(bebidasTest));
        produtos.put("sobremesas", new ArrayList<>(sobremesasTest));
    };

    public Boolean isTableFilled(String tableName) {
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

    public void productsInsert(ProdutosInterface entidadeHibernate, String tableName) {

        if (!isTableFilled(tableName)) {
            ArrayList<String[]> objectsToInsert = produtos.get(tableName);

            for (int i = 0; i < objectsToInsert.size(); i++) {
                String nome = objectsToInsert.get(i)[0];
                String descricao = objectsToInsert.get(i)[1];
                int preco = Integer.parseInt(objectsToInsert.get(i)[2]);

                entidadeHibernate.setNome(nome);
                entidadeHibernate.setDescricao(descricao);
                entidadeHibernate.setPreco(preco);

                Transaction transaction = session.beginTransaction();
                session.merge(entidadeHibernate);
                transaction.commit();
            }

        }
    };

    public void clientInsert(String tableName) {
        if (!isTableFilled(tableName)) {

            Clientes cliente = new Clientes("rei_gado");
            Transaction transaction = session.beginTransaction();
            session.merge(cliente);
            transaction.commit();

        }
    }
}
