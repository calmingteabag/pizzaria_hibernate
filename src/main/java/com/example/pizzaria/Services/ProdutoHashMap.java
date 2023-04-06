package com.example.pizzaria.Services;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Interfaces.Produtos;
import com.example.pizzaria.Models.Pedidos;

// Nos meus modelos, eu uso Map<String, _______> ao invés de lista para guardar
// as referencias do lado 'muitos' no lado 'um', na relação um-para-muitos. "Inventei"
// esse gerador de hash que basicamente, monta a chave do map com o id de pedidos +
// id do produto. Pensando agora isso pode por ventura trazer problemas, pq existem
// 3 produtos com mesmo id, embora pertencentes a modelos diferentes.
@Service
public class ProdutoHashMap {

    public ProdutoHashMap() {
    }

    public String generateKeyByObject(Pedidos pedido, Produtos produto) {
        String idPedido = String.valueOf(pedido.getId());
        String idProduto = String.valueOf(produto.getId());
        String delimiter = "_";

        return idPedido + delimiter + idProduto;
    }

    public String generateKeybyId(String pedidoId, String produtoId) {
        String delimiter = "_";

        return pedidoId + delimiter + produtoId;
    }

    public String decodeKey(String hashString, String returnType) {
        // returnType = pedidoId, produtoId or name(of the product)
        String[] splitHashString = hashString.split("_");

        switch (returnType) {
            case "pedidoId":
                return splitHashString[0];
            case "produtoId":
                return splitHashString[1];
            default:
                return "Invalid String";
        }
    }
}
