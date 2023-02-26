package com.example.pizzaria;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.SelectionQuery;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;

public class DB_Search {
    /*
     * Essa classe e o metodo searchNameDb faz um query do tipo
     * SELECT * FROM tabela WHERE coluna = valor
     * 
     */

    protected static String tableName;
    protected static Class<Produtos> entity;

    public DB_Search(String tablename, Class<Produtos> entity) {
        DB_Search.tableName = tablename;
        // DB_Search.columnName = columnname;
        DB_Search.entity = entity;
    };

    public int searchPriceDb(String column_name, String search_param) {
        String searchParam = search_param;
        String columnName = column_name;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", DB_Search.tableName,
                columnName,
                "'" + searchParam + "'");

        SelectionQuery<Produtos> dbQuery = session.createNativeQuery(
                searchQuery,
                DB_Search.entity);

        List<Produtos> dbSearchResultList = dbQuery.getResultList();

        if (dbSearchResultList.isEmpty()) {
            return 0;
        } else {
            int dbSearchResult = dbSearchResultList.get(0).getPreco();
            session.close();

            return dbSearchResult;
        }

    }

}
