package com.example.pizzaria.DB_Operations;

import com.example.pizzaria.Entities.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;

public class DB_Insert extends DB_Populate {
    private StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private Session session = sessionFactory.openSession();

    public DB_Insert() {
    };

    public String insertClient(Clientes cliente, String tableName, String columnName, String clientName) {

        if (clientName == "" || clientName.length() < 3) {

            return String.format("O nome é invalido, digite um nome válido");

        } else if (!super.isDuplicate(tableName, columnName, clientName)) {

            try {
                cliente.setNome(clientName);
                Transaction transaction = session.beginTransaction();
                session.merge(cliente);
                transaction.commit();
            } catch (Exception exception) {
                return String.format("Foi retornado o erro a seguir: %s", exception.getMessage());
            }
            return String.format("O cliente '%s' foi inserido corretamente.", clientName);
        }
        return String.format("O cliente '%s' já existe no banco de dados.", clientName);
    }
};
