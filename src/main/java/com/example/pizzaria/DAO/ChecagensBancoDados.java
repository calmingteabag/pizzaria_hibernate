/* 
Checa o banco de dados (admitindo que ele existe) pela existencia ou
não de uma tabela específica, ou, se o valor que está sendo inserido
já existe em uma determinada tabela.
*/
package com.example.pizzaria.DAO;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.example.pizzaria.Utils.HibernateSession;

public class ChecagensBancoDados {

    public ChecagensBancoDados() {
    };

    public Boolean isTableFilled(String tableName) {
        Session session = HibernateSession.getSession();
        String checkSQLQuery = String.format(
                "SELECT COUNT(*) FROM %s",
                tableName);

        Query<Integer> checkSQLQueryResult = session.createNativeQuery(checkSQLQuery, Integer.class);
        Integer rowsQty = checkSQLQueryResult.getSingleResult();

        if (rowsQty > 0) {
            return true;
        }
        return false;
    };

    public Boolean isDuplicate(String tableName, String columnName, String insertValue) {
        Session session = HibernateSession.getSession();
        String checkSQLQuery = String.format("SELECT COUNT(*) FROM %s WHERE %s = %s", tableName,
                columnName,
                "'" + insertValue + "'");

        Query<Integer> checkSQLQueryResult = session.createNativeQuery(checkSQLQuery, Integer.class);
        Integer entriesQty = checkSQLQueryResult.getSingleResult();

        if (entriesQty > 0) {
            return true;
        }
        return false;
    }
}
