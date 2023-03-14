package com.example.pizzaria.Utils;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SessionIniciador {

    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Modulo SessionIniciador Carregado");
        HibernateSession.getSession();
    }
}
