package com.example.pizzaria.DAO;

import com.example.pizzaria.Models.*;
import com.example.pizzaria.Utils.HibernateSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

public class CreateCliente {

    public CreateCliente() {
    };

    public String createNewCliente(Class<Clientes> clienteClass, String tableName, String columnName,
            String clientName, String clienteSobrenome) {

        Session session = HibernateSession.getSession();

        if (clientName == "" || clientName.length() < 2) {
            return "O nome é invalido, digite um nome maior que 2 caracteres não nulo.";
        } else if (clienteSobrenome == "" || clienteSobrenome.length() < 2) {
            return "O nome é invalido, digite um sobrenome maior que 2 caracteres não nulo.";
        }

        try {
            Clientes cliente = new Clientes(clientName, clienteSobrenome);
            session.merge(cliente);

            Transaction transaction = session.beginTransaction();
            transaction.commit();

            String searchQuery = String.format("SELECT * FROM %s WHERE %s = :atributo", tableName, columnName);

            SelectionQuery<?> dbQuery = session.createNativeQuery(
                    searchQuery,
                    Clientes.class);
            dbQuery.setParameter("atributo", clientName);

            Clientes getCliente = (Clientes) dbQuery.getSingleResult();

            session.close();

            return String.format("O cliente '%s' , id '%s', criado com sucesso.", cliente.getNome(),
                    getCliente.getId());
        } catch (Exception exception) {
            // handle clientes not existing in db
            return String.format("Foi retornado o erro a seguir: %s", exception.getMessage());
        }
    }

}
