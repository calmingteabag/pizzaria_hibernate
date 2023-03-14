package com.example.pizzaria.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONPedidosProdutos {
    @JsonProperty("pedidos_pizzas")
    private JSONPedidosDetalhes pizzasDetalhes;

    @JsonProperty("pedidos_bebidas")
    private JSONPedidosDetalhes bebidasDetalhes;

    @JsonProperty("pedidos_sobremesas")
    private JSONPedidosDetalhes sobremesasDetalhes;

    public JSONPedidosProdutos(JSONPedidosDetalhes pizzasDetalhes, JSONPedidosDetalhes bebidasDetalhes,
            JSONPedidosDetalhes sobremesasDetalhes) {
        this.pizzasDetalhes = pizzasDetalhes;
        this.bebidasDetalhes = bebidasDetalhes;
        this.sobremesasDetalhes = sobremesasDetalhes;
    };
}