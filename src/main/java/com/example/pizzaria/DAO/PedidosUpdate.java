package com.example.pizzaria.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Utils.HibernateSession;

public class PedidosUpdate {

    public PedidosUpdate() {
    };

    public void updateClienteNome(String clientId, String newNome) {
        Session session = HibernateSession.getSession();
        Transaction transaction = session.beginTransaction();

        Clientes cliente = session.get(Clientes.class, clientId);
        cliente.setNome(newNome);

        session.merge(cliente);
        transaction.commit();
    }

    public void updateClienteSobrenome() {
    };

    public String updatePedidosPizzas(
            String pedidoId,
            String novaPizzaTable,
            String novaPizzaColumn,
            String novaPizzaGetIdMethodName,
            String nomePizzaAntiga,
            String nomePizzaNova) {
        // troca uma pizza por outra no pedido
        Session session = HibernateSession.getSession();
        Transaction transaction = session.beginTransaction();
        int id = Integer.parseInt(pedidoId);

        // get o pedido e retorna uma lista dos pedidosPizzas
        Pedidos pedido = session.get(Pedidos.class, id);
        List<PedidosPizzas> pedidosPizzas = pedido.getPedidosPizzas();

        // get o objeto da pizza nova (que vc quer inserir)
        BuscasGenericas buscaGenerica = new BuscasGenericas(Pizzas.class);
        int searchId = buscaGenerica.getIdByColumnValue(novaPizzaTable, novaPizzaColumn, nomePizzaNova,
                novaPizzaGetIdMethodName);
        Pizzas novaPizza = session.get(Pizzas.class, searchId);
        System.out.println("A pizza nova é: " + novaPizza.getNome());

        // fazemos um loop na lista de pedidospizzas
        for (int i = 0; i < pedidosPizzas.size(); i++) {
            PedidosPizzas currentPedidoPizza = pedidosPizzas.get(i);

            // extraímos a pizza atual
            Pizzas currPizza = currentPedidoPizza.getPizza();
            System.out.println("A pizza atual da lista PedidosPizzas é: " + currPizza.getNome());

            if (currPizza.getNome().equals(nomePizzaAntiga)) {
                // se o nome da pizza atual for o nome que queremos trocar (o nome da pizza
                // antiga), a pizza atual do objeto atual currentPedidoPizza é trocada pela nova
                currentPedidoPizza.setPizza(novaPizza);
                System.out.println("A pizza atual (agora trocada) é: " + currentPedidoPizza.getPizza().getNome());

                session.merge(currentPedidoPizza);
                transaction.commit();
                break;
            } else {
                return String.format("Pedido ID: %s. | Alteração: Pizza %s por %s | Pizza %s não encontrada no pedido.",
                        pedidoId, nomePizzaAntiga, nomePizzaNova, nomePizzaAntiga);
            }
        }

        return String.format("Pedido ID: %s. | Alteração: Pizza %s por %s", pedidoId, nomePizzaAntiga, nomePizzaNova);
    }

    public void updatePedidosBebidas() {
    };

    public void updatePedidosSobremesas() {
    };
}
