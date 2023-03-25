package com.example.pizzaria.DAO;

import com.example.pizzaria.Interfaces.Produtos;
import com.example.pizzaria.Models.Pedidos;

public class ItemKeyForHashMap {

    public ItemKeyForHashMap() {
    }

    public String hashKeyGenerator(Pedidos pedido, Produtos produto) {
        String name = produto.getNome();
        String idPedido = String.valueOf(pedido.getId());
        String idProduto = String.valueOf(produto.getId());
        String delimiter = "_";

        return name + delimiter + idPedido + delimiter + idProduto;
    }

    public String hashKeyDecoder(String hashString, String returnType) {
        // returnType = pedidoId, produtoId or name(of the product)
        String[] splitHashString = hashString.split("_");

        switch (returnType) {
            case "productName":
                return splitHashString[0];
            case "pedidoId":
                return splitHashString[1];
            case "produtoId":
                return splitHashString[2];
            default:
                return "Invalid String";
        }
    }
}
