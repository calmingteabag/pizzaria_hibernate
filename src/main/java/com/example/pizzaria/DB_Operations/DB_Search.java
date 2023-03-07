package com.example.pizzaria.DB_Operations;

import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.SelectionQuery;

import com.example.pizzaria.Entities.*;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;

public class DB_Search {

    public String tableName;
    public Class<?> entity;
    public StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    public final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    public DB_Search(String table_name, Class<?> entity) {
        this.tableName = table_name;
        this.entity = entity;
    };

    public int searchIntegersDb(String column_name, String search_param, String getMethodName) {
        // talvez esse metodo seja desnecessario. Escrevi pra ele retornar o valor de um
        // campo (o preço) pra depois somar tudo em um metodo, mas a entidade Pedidos ja
        // tem um metodo
        // que retorna os valores...
        Session session = sessionFactory.openSession();
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.tableName,
                column_name,
                "'" + search_param + "'");

        SelectionQuery<?> dbQuery = session.createNativeQuery(
                searchQuery,
                entity);

        List<?> dbSearchResultList = dbQuery.getResultList();

        if (dbSearchResultList.isEmpty()) {
            session.close();
            return 0;

        } else {
            Object dbObject = dbSearchResultList.get(0);

            try {
                Method methodGetter = entity.getMethod(getMethodName);
                int result = (int) methodGetter.invoke(dbObject);
                session.close();
                return result;

            } catch (Exception error) {
                session.close();
                error.printStackTrace();
                return 0;
            }
        }
    }

    public Clientes getClientById(String column_name, int clientId) {
        Session session = sessionFactory.openSession();
        Clientes cliente = session.get(Clientes.class, clientId);
        session.close();

        return cliente;
    };

    public Clientes getClientByName(String column_name, String search_param) {
        Session session = sessionFactory.openSession();

        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.tableName,
                column_name,
                "'" + search_param + "'");

        SelectionQuery<Clientes> dbQuery = session.createNativeQuery(
                searchQuery,
                Clientes.class);

        List<Clientes> dbSearchResultList = dbQuery.getResultList();
        Clientes novoCliente = dbSearchResultList.get(0);
        session.close();

        return novoCliente;
    };

    public List<?> getProductClassList(String column_name, String search_param) {
        Session session = sessionFactory.openSession();

        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.tableName,
                column_name,
                "'" + search_param + "'");

        SelectionQuery<?> dbQuery = session.createNativeQuery(
                searchQuery,
                entity);

        List<?> dbSearchResultList = dbQuery.getResultList();
        // Class<?> novoCliente = dbSearchResultList.get(0);
        session.close();

        return dbSearchResultList;
    };
}
