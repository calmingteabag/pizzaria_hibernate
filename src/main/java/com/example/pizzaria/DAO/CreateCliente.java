/* 
 * Autoexplicativo, insere um cliente no banco de dados.
 */

package com.example.pizzaria.DAO;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CreateCliente extends ChecagensBancoDados {

    public CreateCliente() {
    };

    public String insertClient(Clientes cliente, String tableName, String columnName, String clientName,
            String clienteSobrenome) {

        Session session = HibernateSession.getSession();
        if (clientName == "" || clientName.length() < 3) {

            return String.format("O nome é invalido, digite um nome válido");

            // } else if (!super.isDuplicate(tableName, columnName, clientName)) {
        }
        try {
            cliente.setNome(clientName);
            cliente.setSobrenome(clienteSobrenome);
            Transaction transaction = session.beginTransaction();
            session.merge(cliente);
            transaction.commit();
        } catch (Exception exception) {
            return String.format("Foi retornado o erro a seguir: %s", exception.getMessage());
        }
        return String.format("O cliente '%s' foi inserido corretamente.", clientName);
        // }
        // return String.format("O cliente '%s' já existe no banco de dados.",
        // clientName);
    }

};
