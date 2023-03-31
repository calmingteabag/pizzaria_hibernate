package com.example.pizzaria.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoCliente {

    @JsonProperty("id_cliente")
    private int idCliente;

    @JsonProperty("nome_cliente")
    private String nomeCliente;

    // outras propriedades

    // podemos adicionar depois mais coisas do tipo
    // @JsonProperty("endereco_entrega")
    // private Endereço endereço; - nova classe com as infos do
    // endereço

    // podemos ter outro para dados de contato, telefone/ tel cel
    // @JsonProperty("dados_cliente")
    // Private DadosCliente dadosCLiente; - class para os dados
    // tipo cpf, telefone, etc

    // por hora vou deixar só o nominho, pois teria que mudar o banco
    // inteiro

    public InfoCliente() {
    }

    public InfoCliente(int newId, String nome) {
        this.idCliente = newId;
        this.nomeCliente = nome;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int newId) {
        this.idCliente = newId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nome) {
        this.nomeCliente = nome;
    }
}
