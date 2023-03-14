/* 
Na minha ignorancia, estava criando uma session factory para cada vez que
tinha que usar uma session. Descobri que não é uma boa idéia e pesquisei
sobre como criar uma maneira de manter uma session factory para toda a 
aplicação. Foi-me sugerido esse codigo, que funciona, e ainda aprendi sobre
a existencia e o funcionamento do bloco static { }

Essa classe cria uma SessionFactory e abre sessions via getSession() para serem
distribuídas conforme necessidade na aplicação.
*/

package com.example.pizzaria.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
