package com.example.pizzaria;

import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.SelectionQuery;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;

public class DB_Search {

    public String tableName;
    public Class<?> entity;

    public DB_Search(String tablename, Class<?> entity) {
        this.tableName = tablename;
        this.entity = entity;
    };

    public int searchPriceDb(String column_name, String search_param) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.tableName,
                column_name,
                "'" + search_param + "'");

        SelectionQuery<?> dbQuery = session.createNativeQuery(
                searchQuery,
                entity);

        List<?> dbSearchResultList = dbQuery.getResultList();

        if (dbSearchResultList.isEmpty()) {
            return 0;

        } else {
            Object dbObject = dbSearchResultList.get(0);

            try {
                Method methodGetter = entity.getMethod("getPreco");
                int preco = (int) methodGetter.invoke(dbObject);
                return preco;

            } catch (Exception error) {
                error.printStackTrace();
                return 0;
            }
        }
    }
}
