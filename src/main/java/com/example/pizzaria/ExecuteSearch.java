package com.example.pizzaria;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.SelectionQuery;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;

public class ExecuteSearch {
    /*
     * Essa classe e o metodo searchNameDb faz um query do tipo
     * SELECT * FROM tabela WHERE coluna = valor
     * 
     */

    protected static String tableName;
    protected static String columnName;
    protected static Class<Produtos> entity;

    public ExecuteSearch(String tablename, String columnname, Class<Produtos> entity) {
        // how do I call tablename so it won't be confused with tableName from
        // class variable?

        ExecuteSearch.tableName = tablename;
        ExecuteSearch.columnName = columnname;
        ExecuteSearch.entity = entity;
    };

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String searchNameDb(String search_param) {
        String searchParam = search_param;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.tableName, this.columnName,
                searchParam);

        SelectionQuery<Produtos> dbQuery = session.createNativeQuery(
                searchQuery,
                // Produtos.class);
                this.entity);

        List<Produtos> dbSearchResult = dbQuery.getResultList();
        String dbNomeProduto = dbSearchResult.get(0).getNome();
        session.close();

        return dbNomeProduto;

    }

}
