package com.example.pizzaria;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.boot.MetadataSources;
import org.hibernate.query.SelectionQuery;

import jakarta.persistence.criteria.Selection;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.SessionFactory;

public class ExecuteSearch {

    private String tableName;
    private String columnName;

    public ExecuteSearch(String tablename, String columnname, Class<Produtos> product) {
        // how do I call tablename so it won't be confused with tableName from
        // class variable?

        this.tableName = tablename;
        this.columnName = columnname;
    };

    public String searchNameDb(String search_param) {
        String searchParam = search_param;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.tableName, this.columnName,
                searchParam);

        SelectionQuery<Produtos> dbQuery = session.createNativeQuery(
                searchQuery,
                Produtos.class);

        List<Produtos> dbSearchResult = dbQuery.getResultList();
        String dbNomeProduto = dbSearchResult.get(0).getNome();

        return dbNomeProduto;

    }

}
