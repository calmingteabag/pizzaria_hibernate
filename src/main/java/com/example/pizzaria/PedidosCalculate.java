package com.example.pizzaria;

import com.example.pizzaria.Entities.*;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Method;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;

import org.hibernate.query.SelectionQuery;

public class PedidosCalculate {

    public int pedidoTotal;
    public String pedidosTableName;
    public Class<?> pedidosEntity;
    private StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private Session session = sessionFactory.openSession();

    public PedidosCalculate() {
    };

    public PedidosCalculate(String pedidosTableName, Class<?> pedidosEntity) {
        this.pedidosTableName = pedidosTableName;
        this.pedidosEntity = pedidosEntity;
    };

    public int returnFullTotals(String column_name, String search_param, String getMethodName) {
        // esse metodo ta retornando só um resultado, nas precisa somar todos.
        // pensei em uma array de getMethods + loop em methodGetter pra ele ir
        // somando os totais.
        String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s", this.pedidosTableName,
                column_name,
                "'" + search_param + "'");

        SelectionQuery<?> dbQuery = session.createNativeQuery(
                searchQuery,
                pedidosEntity);

        List<?> dbSearchResultList = dbQuery.getResultList();

        if (dbSearchResultList.isEmpty()) {
            session.close();
            return 0;

        } else {
            Object pedidoObject = dbSearchResultList.get(0);

            try {
                Method methodGetter = pedidosEntity.getMethod(getMethodName);
                int result = (int) methodGetter.invoke(pedidoObject);
                pedidoTotal += result;
                session.close();
                return pedidoTotal;

            } catch (Exception error) {
                session.close();
                error.printStackTrace();
                return 0;
            }
        }

    };
}

// private HashMap<String, ArrayList<ArrayList<String>>> pedidos;
// private String columnName;

// private int totalPrecoPedido;

// public PedidosCalculate(String tablename, String column_name,
// Class<ProdutosTeste> entity,
// HashMap<String, ArrayList<ArrayList<String>>> hashmap) {
// super(tablename, entity);
// this.pedidos = hashmap;
// this.columnName = column_name;
// };

// public int calculatePedidos(String hashKey) {
// // Do hashmap, pegando as keys dos tipos de pedidos
// ArrayList<ArrayList<String>> pedidoTipoEspecifico = new
// ArrayList<ArrayList<String>>(pedidos.get(hashKey));
// Integer totalPrecoPedidoEspecifico = 0;

// if (pedidoTipoEspecifico.isEmpty()) {
// return totalPrecoPedidoEspecifico;
// }

// for (int i = 0; i < pedidoTipoEspecifico.size(); i++) {

// ArrayList<String> currentPedido = pedidoTipoEspecifico.get(i);
// String nomePrincipalPedido = currentPedido.get(0); // nome do pedido
// // principal
// String nomeSecundarioPedido = currentPedido.get(1); // no caso de pizzas
// String quantidadePedido = currentPedido.get(2);

// if (hashKey == "pizzas" && nomeSecundarioPedido != "none") {

// int firstFlavorPrice = super.searchIntegersDb(columnName,
// nomePrincipalPedido);
// int secondFlavorPrice = super.searchIntegersDb(columnName,
// nomeSecundarioPedido);

// if (firstFlavorPrice > secondFlavorPrice) {

// totalPrecoPedidoEspecifico = firstFlavorPrice *
// Integer.parseInt(quantidadePedido);
// currentPedido.add(Integer.toString(totalPrecoPedidoEspecifico));
// System.out.println(String.format("Your %s costs %d. O valor é o da pizza mais
// cara, %s ",
// nomePrincipalPedido, totalPrecoPedidoEspecifico, nomePrincipalPedido));

// } else {
// totalPrecoPedidoEspecifico = secondFlavorPrice *
// Integer.parseInt(quantidadePedido);
// currentPedido.add(Integer.toString(totalPrecoPedidoEspecifico));
// System.out.println(String.format("Your %s costs %d. O valor é o da pizza mais
// cara, %s ",
// nomeSecundarioPedido, totalPrecoPedidoEspecifico, nomeSecundarioPedido));
// }

// } else {
// int precoProdutoAtual = super.searchPriceDb(columnName, nomePrincipalPedido);
// totalPrecoPedidoEspecifico = precoProdutoAtual *
// Integer.parseInt(quantidadePedido);
// currentPedido.add(Integer.toString(totalPrecoPedidoEspecifico));
// System.out.println(String.format("Your %s costs %d ", nomePrincipalPedido,
// precoProdutoAtual));
// }
// }
// return totalPrecoPedidoEspecifico;
// }

// public int calculateTotal(String hashKeyPizzas, String hashKeyBebidas, String
// hashKeySobremesas) {
// int totalPedidoPizzas = calculatePedidos(hashKeyPizzas);
// // pedidos.get(hashKeyPizzas).add(new
// // ArrayList<>(List.of(Integer.toString(totalPedidoPizzas))));

// int totalPedidoBebidas = calculatePedidos(hashKeyBebidas);
// // pedidos.get(hashKeyBebidas).add(new
// // ArrayList<>(List.of(Integer.toString(totalPedidoBebidas))));

// int totalPedidoSobremesas = calculatePedidos(hashKeySobremesas);
// // pedidos.get(hashKeySobremesas).add(new
// // ArrayList<>(List.of(Integer.toString(totalPedidoSobremesas))));

// // pedidos.put("pizza_total", new
// // ArrayList<?>(List.of(Integer.toString(totalPedidoPizzas))));
// // a ideia é put no hashmap os preços totais por tipo de pedido

// totalPrecoPedido = totalPedidoPizzas + totalPedidoBebidas +
// totalPedidoSobremesas;

// return totalPrecoPedido;
// }
// }