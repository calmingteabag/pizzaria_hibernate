package com.example.pizzaria;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class PedidosCliente {
    private HashMap<String, ArrayList<ArrayList<String>>> pedidos = new HashMap<>();
    // To make things clearer i'm trying to do this
    // chave : arraylist : [arrylist_1, arraylist_2, arraylist_3, ...]

    public void createTestPedidos() {
        ArrayList<ArrayList<String>> pizzas = new ArrayList<>();
        ArrayList<ArrayList<String>> bebidas = new ArrayList<>();
        ArrayList<ArrayList<String>> sobremesas = new ArrayList<>();
        // initiate a new arraylist that will appended as string: arraylist on pedidos
        // hashmap
        pizzas.add(new ArrayList<>(List.of("marguerita", "calabresa", "1")));
        pizzas.add(new ArrayList<>(List.of("portuguesa", "none", "4")));
        pizzas.add(new ArrayList<>(List.of("mucarela", "none", "2")));

        bebidas.add(new ArrayList<>(List.of("guarana", "none", "3")));
        bebidas.add(new ArrayList<>(List.of("agua", "none", "1")));

        sobremesas.add(new ArrayList<>(List.of("brigadeiro", "none", "10")));
        sobremesas.add(new ArrayList<>(List.of("rapadura", "none", "3")));

        pedidos.put("pizzas", pizzas);
        pedidos.put("bebidas", bebidas);
        pedidos.put("sobremesas", sobremesas);

    }

    public HashMap<String, ArrayList<ArrayList<String>>> getPedidos() {
        return pedidos;
    }
}
