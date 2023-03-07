package com.example.pizzaria;

import java.util.ArrayList;
import java.util.HashMap;

public class PedidosCalculate extends DB_Search {

    private HashMap<String, ArrayList<ArrayList<String>>> pedidos;
    private String columnName;

    private int totalPrecoPedido;

    public PedidosCalculate(String tablename, String column_name, Class<ProdutosTeste> entity,
            HashMap<String, ArrayList<ArrayList<String>>> hashmap) {
        super(tablename, entity);
        this.pedidos = hashmap;
        this.columnName = column_name;
    };

    public int calculatePedidos(String hashKey) {
        // Do hashmap, pegando as keys dos tipos de pedidos
        ArrayList<ArrayList<String>> pedidoTipoEspecifico = new ArrayList<ArrayList<String>>(pedidos.get(hashKey));
        Integer totalPrecoPedidoEspecifico = 0;

        if (pedidoTipoEspecifico.isEmpty()) {
            return totalPrecoPedidoEspecifico;
        }

        for (int i = 0; i < pedidoTipoEspecifico.size(); i++) {

            ArrayList<String> currentPedido = pedidoTipoEspecifico.get(i);
            String nomePrincipalPedido = currentPedido.get(0); // nome do pedido
            // principal
            String nomeSecundarioPedido = currentPedido.get(1); // no caso de pizzas
            String quantidadePedido = currentPedido.get(2);

            if (hashKey == "pizzas" && nomeSecundarioPedido != "none") {

                int firstFlavorPrice = super.searchPriceDb(columnName, nomePrincipalPedido);
                int secondFlavorPrice = super.searchPriceDb(columnName,
                        nomeSecundarioPedido);

                if (firstFlavorPrice > secondFlavorPrice) {

                    totalPrecoPedidoEspecifico = firstFlavorPrice * Integer.parseInt(quantidadePedido);
                    currentPedido.add(Integer.toString(totalPrecoPedidoEspecifico));
                    System.out.println(String.format("Your %s costs %d. O valor é o da pizza mais cara, %s ",
                            nomePrincipalPedido, totalPrecoPedidoEspecifico, nomePrincipalPedido));

                } else {
                    totalPrecoPedidoEspecifico = secondFlavorPrice * Integer.parseInt(quantidadePedido);
                    currentPedido.add(Integer.toString(totalPrecoPedidoEspecifico));
                    System.out.println(String.format("Your %s costs %d. O valor é o da pizza mais cara, %s ",
                            nomeSecundarioPedido, totalPrecoPedidoEspecifico, nomeSecundarioPedido));
                }

            } else {
                int precoProdutoAtual = super.searchPriceDb(columnName, nomePrincipalPedido);
                totalPrecoPedidoEspecifico = precoProdutoAtual * Integer.parseInt(quantidadePedido);
                currentPedido.add(Integer.toString(totalPrecoPedidoEspecifico));
                System.out.println(String.format("Your %s costs %d ", nomePrincipalPedido,
                        precoProdutoAtual));
            }
        }
        return totalPrecoPedidoEspecifico;
    }

    public int calculateTotal(String hashKeyPizzas, String hashKeyBebidas, String hashKeySobremesas) {
        int totalPedidoPizzas = calculatePedidos(hashKeyPizzas);
        // pedidos.get(hashKeyPizzas).add(new
        // ArrayList<>(List.of(Integer.toString(totalPedidoPizzas))));

        int totalPedidoBebidas = calculatePedidos(hashKeyBebidas);
        // pedidos.get(hashKeyBebidas).add(new
        // ArrayList<>(List.of(Integer.toString(totalPedidoBebidas))));

        int totalPedidoSobremesas = calculatePedidos(hashKeySobremesas);
        // pedidos.get(hashKeySobremesas).add(new
        // ArrayList<>(List.of(Integer.toString(totalPedidoSobremesas))));

        // pedidos.put("pizza_total", new
        // ArrayList<?>(List.of(Integer.toString(totalPedidoPizzas))));
        // a ideia é put no hashmap os preços totais por tipo de pedido

        totalPrecoPedido = totalPedidoPizzas + totalPedidoBebidas +
                totalPedidoSobremesas;

        return totalPrecoPedido;
    }
}