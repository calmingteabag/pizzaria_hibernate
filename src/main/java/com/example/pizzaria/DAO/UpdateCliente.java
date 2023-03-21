package com.example.pizzaria.DAO;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdateCliente extends ChecagensBancoDados {

    public UpdateCliente() {
    };

    public String updateClienteById(Class<Clientes> clienteClass, String idCliente,
            String clientName, String clienteSobrenome) {

        Session session = HibernateSession.getSession();
        int clienteId = Integer.parseInt(idCliente);

        if (clientName == "" || clientName.length() < 2) {
            return "O nome é invalido, digite um nome maior que 2 caracteres não nulo.";
        } else if (clienteId <= 0) {
            return "ID cliente inválido";
        }

        Clientes cliente = session.get(clienteClass, clienteId);
        cliente.setNome(clientName);
        cliente.setSobrenome(clienteSobrenome);
        session.merge(cliente);

        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();

        return String.format("O cliente '%s' update com sucesso para nome = %s, sobrenome = %s", idCliente, clientName,
                clienteSobrenome);

    }
};
