package com.example.pizzaria.Utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.type.jakartajson.JsonBJsonFormatMapper;

import com.example.pizzaria.Entities.*;
import com.example.pizzaria.Interfaces.ProdutosInterface;
import com.example.pizzaria.Utils.HibernateSession;

import com.example.pizzaria.JSON.*;

public class RetrievePedidosData {

    private String pedidoId;
    private Pedidos pedido;

    public RetrievePedidosData() {
    };

    public RetrievePedidosData(String newPedidoId) {
        this.pedidoId = newPedidoId;
    };

    // Get o nome do Cliente
    public String getClienteNome(int pedidoId) {
        Session session = HibernateSession.getSession();
        Pedidos pedido = session.get(Pedidos.class, pedidoId);

        Clientes cliente = pedido.getCliente();
        String clientNome = cliente.getNome() + " " + cliente.getSobrenome();

        return clientNome;
    }
}
