package com.example.pizzaria.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONPedido {

    @JsonProperty("info_cliente")
    private InfoCliente infoCliente;

    @JsonProperty("info_pedido")
    private InfoPedido infoPedido;

    public JSONPedido() {
    }

    public JSONPedido(InfoCliente infoCliente, InfoPedido pedido, Integer total) {
        this.infoCliente = infoCliente;
        this.infoPedido = pedido;
    }

    public InfoCliente getInfoCliente() {
        return infoCliente;
    }

    public void setInfoCliente(InfoCliente infoCliente) {
        this.infoCliente = infoCliente;
    }

    public InfoPedido getInfoPedido() {
        return infoPedido;
    }

    public void setInfoPedido(InfoPedido newInfoPedido) {
        this.infoPedido = newInfoPedido;
    }
}
