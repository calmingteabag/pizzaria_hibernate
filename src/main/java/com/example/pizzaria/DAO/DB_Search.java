package com.example.pizzaria.DAO;

import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

public class DB_Search {

    public String tableName;
    public Class<?> entity;

    public DB_Search(String table_name, Class<?> entity) {
        this.tableName = table_name;
        this.entity = entity;
    };

    public int searchIntegersDb(String column_name, String search_param, String getMethodName) {
        // talvez esse metodo seja desnecessario. Escrevi pra ele retornar o valor de um
        // campo (o preço) pra depois somar tudo em um metodo, mas a entidade Pedidos ja
        // tem um metodo
        // que retorna os valores...
        Session session = HibernateSession.getSession();
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
        Session session = HibernateSession.getSession();
        Clientes cliente = session.get(Clientes.class, clientId);
        session.close();

        return cliente;
    };

    public Clientes getClientByName(String column_name, String search_param) {
        Session session = HibernateSession.getSession();

        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.tableName,
                column_name,
                "'" + search_param + "'");

        SelectionQuery<Clientes> dbQuery = session.createNativeQuery(
                searchQuery,
                Clientes.class);

        List<Clientes> dbSearchResultList = dbQuery.getResultList();
        Clientes cliente = dbSearchResultList.get(0);
        session.close();

        return cliente;
    };

    public List<?> getItemClassList(String column_name, String search_param, Boolean isSearchingForId) {
        Session session = HibernateSession.getSession();
        String searchQuery;

        if (isSearchingForId) {
            searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.tableName,
                    column_name,
                    search_param);
        }

        searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.tableName,
                column_name,
                "'" + search_param + "'");

        SelectionQuery<?> dbQuery = session.createNativeQuery(
                searchQuery,
                entity);

        List<?> dbSearchResultList = dbQuery.getResultList();
        session.close();

        return dbSearchResultList;
    };

    // public void getPedidosElements(String column_name, String joinTableName,
    // String joinTableColumn,
    // String search_param) {
    // Session session = HibernateSession.getSession();

    // String searchQuery = String.format("SELECT p FROM %s WHERE %s = %s",
    // this.tableName,
    // column_name,
    // "'" + search_param + "'");

    // Query<Pedidos> query = (Query<Pedidos>) session.createQuery(
    // searchQuery, Pedidos.class);

    // }
}
