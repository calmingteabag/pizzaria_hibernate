// package com.example.pizzaria;

// import java.util.ArrayList;

// import com.fasterxml.jackson.annotation.JsonProperty;

// public class Pedidos {
// @JsonProperty("nomeCliente")
// private String pojoCliente;

// @JsonProperty("listagemPedido")
// private ListagemPedido pojoListagem;

// @JsonProperty("totalPedido")
// private int pojoTotalPedido;

// public Pedidos() {
// };

// public Pedidos(String cliente, ListagemPedido listagem, int total) {
// this.pojoCliente = cliente;
// this.pojoListagem = listagem;
// this.pojoTotalPedido = total;
// };
// }

// class ListagemPedido {

// @JsonProperty("pizzas")
// private ArrayList<Pizzas> pojoPizzas;

// @JsonProperty("bebidas")
// private ArrayList<Bebidas> pojoBebidas;

// @JsonProperty("sobremesas")
// private ArrayList<Sobremesas> pojoSobremesas;

// @JsonProperty("totalPizzas")
// private int pojoTotalGrupoPizzas;

// @JsonProperty("totalBebidas")
// private int pojoTotalGrupoBebidas;

// @JsonProperty("totalSobremesas")
// private int pojoTotalGrupoSobremesas;

// public ListagemPedido() {
// };

// public ListagemPedido(ArrayList<Pizzas> pizzas, ArrayList<Bebidas> bebidas,
// ArrayList<Sobremesas> sobremesas,
// int totalPizzas, int totalBebidas, int totalSobremesas) {
// this.pojoPizzas = pizzas;
// this.pojoBebidas = bebidas;
// this.pojoSobremesas = sobremesas;
// this.pojoTotalGrupoPizzas = totalPizzas;
// this.pojoTotalGrupoBebidas = totalBebidas;
// this.pojoTotalGrupoSobremesas = totalSobremesas;
// };
// }

// class Pizzas {
// @JsonProperty("nomePizza")
// private String pojoNomePizza;

// @JsonProperty("quantidadePizza")
// private int pojoQtyPizza;

// @JsonProperty("totalPizza")
// private int pojoTotalPizza;

// public Pizzas() {
// };

// public Pizzas(String nomePizza, int qtyPizza, int totalPizza) {
// this.pojoNomePizza = nomePizza;
// this.pojoQtyPizza = qtyPizza;
// this.pojoTotalPizza = totalPizza;
// }
// }

// class Bebidas {

// @JsonProperty("nomeBebida")
// private String pojoNomeBebida;

// @JsonProperty("quantidadeBebida")
// private int pojoQtyBebida;

// @JsonProperty("totalBebida")
// private int pojoTotalBebida;

// public Bebidas() {
// };

// public Bebidas(String nomeBebida, int qtyBebida, int totalBebida) {
// this.pojoNomeBebida = nomeBebida;
// this.pojoQtyBebida = qtyBebida;
// this.pojoTotalBebida = totalBebida;
// };
// }

// class Sobremesas {

// @JsonProperty("nomeSobremesa")
// private String pojoNomeSobremesa;

// @JsonProperty("quantidadeSobremesa")
// private int pojoQtySobremesa;

// @JsonProperty("totalSobremesa")
// private int pojoTotalSobremesa;

// public Sobremesas() {
// };

// public Sobremesas(String nomeSobremesa, int qtySobremesa, int totalSobremesa)
// {
// this.pojoNomeSobremesa = nomeSobremesa;
// this.pojoQtySobremesa = qtySobremesa;
// this.pojoTotalSobremesa = totalSobremesa;
// };
// }