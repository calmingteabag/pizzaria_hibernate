package com.example.pizzaria.DAO;

import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import com.example.pizzaria.Models.*;
import com.example.pizzaria.Utils.HibernateSession;

public class BuscasGenericas {

    public String tableName;
    private Class<?> entity;

    public BuscasGenericas() {
    }

    public BuscasGenericas(Class<?> entity) {
        this.entity = entity;
    };

    public int getIdByColumnValue(Class<?> entity, String tableName, String columnName, String columnValue,
            String getMethodName) {
        Session session = HibernateSession.getSession();
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", tableName,
                columnName,
                "'" + columnValue + "'");

        SelectionQuery<?> dbQuery = session.createNativeQuery(
                searchQuery,
                entity);

        List<?> dbSearchResultList = dbQuery.getResultList();

        Object dbObject = dbSearchResultList.get(0);

        try {
            Method methodGetter = entity.getMethod(getMethodName);
            int resultId = (int) methodGetter.invoke(dbObject);
            session.close();
            return resultId;

        } catch (Exception error) {
            session.close();
            error.printStackTrace();
            return 0;
        }
    }

    public String getColumnValueById(String tableName, String columnName, String columnValue, String getMethodName) {
        Session session = HibernateSession.getSession();
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", tableName,
                columnName,
                columnValue);

        SelectionQuery<?> dbQuery = session.createNativeQuery(
                searchQuery,
                entity);

        List<?> dbSearchResultList = dbQuery.getResultList();

        Object dbObject = dbSearchResultList.get(0);

        try {
            Method methodGetter = entity.getMethod(getMethodName);
            String resultParam = (String) methodGetter.invoke(dbObject);
            session.close();
            return resultParam;

        } catch (Exception error) {
            session.close();
            error.printStackTrace();
            return "";
        }
    };

    public int getIdByName(String table_name, String column_name, String search_name, String methodName) {
        Session session = HibernateSession.getSession();
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", table_name,
                column_name,
                "'" + search_name + "'");

        SelectionQuery<?> dbQuery = session.createNativeQuery(
                searchQuery,
                entity);

        List<?> dbSearchResultList = dbQuery.getResultList();

        Object dbObject = dbSearchResultList.get(0);

        try {
            Method methodGetter = entity.getMethod(methodName);
            int resultId = (int) methodGetter.invoke(dbObject);
            session.close();
            return resultId;

        } catch (Exception error) {
            session.close();
            error.printStackTrace();
            return 0;
        }
    }

    public String getNameById(String table_name, String column_name, String search_name, String methodName) {
        Session session = HibernateSession.getSession();
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", table_name,
                column_name,
                "'" + search_name + "'");

        SelectionQuery<?> dbQuery = session.createNativeQuery(
                searchQuery,
                entity);

        List<?> dbSearchResultList = dbQuery.getResultList();

        Object dbObject = dbSearchResultList.get(0);

        try {
            Method methodGetter = entity.getMethod(methodName);
            String resultParam = (String) methodGetter.invoke(dbObject);
            session.close();
            return resultParam;

        } catch (Exception error) {
            session.close();
            error.printStackTrace();
            return "";
        }
    };

    public int searchIntegersDb(String column_name, String search_param, String methodName) {
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
                Method methodGetter = entity.getMethod(methodName);
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

    public List<?> getItemClassList(Class<?> entity, String tableName, String column_name, String searchParam,
            Boolean isSearchingForId) {
        Session session = HibernateSession.getSession();
        String searchQuery;

        if (isSearchingForId) {
            searchQuery = String.format("SELECT * FROM %s WHERE %s = :buscaValor", tableName,
                    column_name,
                    searchParam);
        } else {
            searchQuery = String.format("SELECT * FROM %s WHERE %s = :buscaValor", tableName,
                    column_name,
                    "'" + searchParam + "'");
        }

        SelectionQuery<?> query = session.createNativeQuery(searchQuery, entity);
        query.setParameter("buscaValor", searchParam);

        List<?> dbSearchResultList = query.getResultList();
        session.close();

        return dbSearchResultList;
    }

}
