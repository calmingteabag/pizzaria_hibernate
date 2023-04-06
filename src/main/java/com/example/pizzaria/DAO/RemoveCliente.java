/* 
 * Essa classe existe, mas não faz sentido no contexto desse app
 * pois remover um cliente simplesmente quebraria as relações 
 * no banco de dados.
 */
package com.example.pizzaria.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.pizzaria.Models.Clientes;
import com.example.pizzaria.Utils.HibernateSession;

public class RemoveCliente {

    public RemoveCliente() {
    }

    public String removeClientById(String idCliente) {
        Session session = HibernateSession.getSession();
        Transaction transaction = session.beginTransaction();
        int intIdCliente = Integer.parseInt(idCliente);
        Clientes cliente = session.get(Clientes.class, intIdCliente);
        session.remove(cliente);
        transaction.commit();
        session.close();

        return String.format("Cliente id %s removido", idCliente);
    }
}
