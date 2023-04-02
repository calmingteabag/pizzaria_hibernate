package com.example.pizzaria.Services;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.Session;

import com.example.pizzaria.Models.Pedidos;
import com.example.pizzaria.Utils.HibernateSession;

public class UserInputChecker {

    public UserInputChecker() {
    }

    public String checkInput(String pedidoId, String tipoProduto, String quantidade, String nomeProduto) {
        HashSet<String> setProdutos = new HashSet<>(Arrays.asList("pizza", "bebida",
                "sobremesa"));
        String tipo = tipoProduto.toLowerCase();
        Session session = HibernateSession.getSession();

        try {
            Integer.parseInt(pedidoId);
        } catch (NumberFormatException e) {
            return "Digite apenas numeros";
        }

        try {
            Integer.parseInt(quantidade);
        } catch (NumberFormatException e) {
            return "Digite apenas numeros";
        }

        if (setProdutos.contains(tipo) == false) {
            return "Produto inexistente. Use \"pizza\", \"bebida\" ou \"sobremesa\" (semaspas) ";
        }

        if (session.get(Pedidos.class, Integer.parseInt(pedidoId)) == null) {
            return "Id Produto inexistente.";
        }

        if (Integer.parseInt(quantidade) > 5 || Integer.parseInt(quantidade) <= 0) {
            return "Máximo 5 itens por tipo";
        }

        return "ok";
    }
}
