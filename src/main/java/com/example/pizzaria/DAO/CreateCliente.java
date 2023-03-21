package com.example.pizzaria.DAO;

import com.example.pizzaria.Entities.*;
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

        String buscaCliente = String.format("SELECT * FROM %s WHERE %s = :buscaValor", tableName, columnName);
        SelectionQuery<?> clientQuery = session.createNativeQuery(buscaCliente, clienteClass);
        clientQuery.setParameter("buscaValor", clientName);

        try {
            Clientes cliente = (Clientes) clientQuery.getSingleResult();
            cliente.setNome(clientName);
            cliente.setSobrenome(clienteSobrenome);
            session.merge(cliente);

            Transaction transaction = session.beginTransaction();
            transaction.commit();
            session.close();

            return String.format("O cliente '%s' update com sucesso.", cliente.getNome());
        } catch (Exception exception) {
            // handle clientes not existing in db
            return String.format("Foi retornado o erro a seguir: %s", exception.getMessage());
        }
    }

}
