package com.example.pizzaria;

import java.util.ArrayList;
import java.util.HashMap;

public class CalculatePedido extends ExecuteSearch {

    private int totalPedido;
    private HashMap<String, ArrayList<ArrayList<String>>> pedidos;

    public CalculatePedido(String tablename, String columnname, Class<Produtos> entity,
            HashMap<String, ArrayList<ArrayList<String>>> hashmap) {
        super(tableName, columnName, entity);
        this.pedidos = hashmap;
    };

    public int calculate() {
        int totalPrecoPizzas;
        // get o preço total das pizzas
        ArrayList<ArrayList<String>> pedidosPizza = new ArrayList<ArrayList<String>>(pedidos.get("pizzas"));
        for (int i = 0; i < pedidosPizza.size(); i++) {
            ArrayList<String> currentPizza = pedidosPizza.get(i);
            String pizzaName = currentPizza.get(0);

            // now search pizzaName on DB
            // ok passei todos os parametros do executesearch pra cá
            // como eu uso o metodo searchNameDb da classe pai?
            // chatgpt: super.searchNameDb()

            System.out.println(pedidosPizza.get(i));
            System.out.println(currentPizza);
        }
        System.out.println(pedidosPizza);

        return totalPedido;
    }
}
