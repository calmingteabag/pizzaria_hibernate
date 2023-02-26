package com.example.pizzaria;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
// @RestController
@Controller
public class PizzariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzariaApplication.class, args);
	}

	@RequestMapping("/")
	public String searchDb(Model model) {
		/*
		 * Resolvi colocar o metodo de criacao do DB logo que se abre a pagina. Tenho
		 * plena consciencia que é um jeito terrivel de fazer isso, mas sem idéias mais
		 * interessantes no momento.
		 * 
		 */
		DB_Populate populate = new DB_Populate("produtos");
		populate.populateDBMockupData();

		String teste_1 = "É o Tchan!";
		model.addAttribute("teste_1", teste_1);
		return "teste";
	}

	@PostMapping("/pedidos")
	// @ResponseBody // define se é para ele retornar uma pagina html ou a resposta
	// literal de alguma
	// coisa
	public String postBody(HttpServletRequest request, Model model) throws JsonProcessingException {
		/*
		 * Em teoria, os pedidos para esse 'backend' viriam de um post request
		 * de algum lugar. Por hora, criei um objeto que simula uma lista de
		 * pedidos de um cliente, que contém varias pizzas, bebidas e doces e a
		 * busca é feita em cima desse 'mockup'.
		 */

		// Cria um mockup dos pedidos e coloca em uma "variavel"
		PedidosCliente testPedidos = new PedidosCliente();
		testPedidos.createTestPedidos();
		HashMap<String, ArrayList<ArrayList<String>>> pedidos = testPedidos.getPedidos();

		// Calcula os preços baseado no Mockup
		// ...
		PedidosCalculate calculadora = new PedidosCalculate("produtos", "nome_produto", Produtos.class, pedidos);
		int total = calculadora.calculateTotal("pizzas", "bebidas", "sobremesas");
		System.out.println(total);

		// Test for receiving data from post request
		// and searcing on db
		DB_Search buscaDb = new DB_Search("produtos", Produtos.class);
		String pizzaFromPost = request.getParameter("pizzas");
		int precoPizza = buscaDb.searchPriceDb("nome_produto", pizzaFromPost);

		// Test POJO + jackson (@JsonProperty)

		// a parte todaspizzas/bebidas/sobremesas pode ser colocada num
		// outro metodo que vá adicionandoe calculando automaticamente
		// o campo de totais...maybe.

		// desafio agora é, depois de instanciado, como eu modifico uma propriedade em
		// nested, tipo totalpizza, que esta em listagempedido - pizzas[posicao] ?

		ArrayList<Pizzas> todasPizzas = new ArrayList<>();
		todasPizzas.add(new Pizzas("peperoni", 20, 0));
		todasPizzas.add(new Pizzas("marguerita", 30, 0));

		ArrayList<Bebidas> todasBebidas = new ArrayList<>();
		todasBebidas.add(new Bebidas("breja", 5, 0));
		todasBebidas.add(new Bebidas("coca", 2, 0));

		ArrayList<Sobremesas> todasSobremesas = new ArrayList<>();
		todasSobremesas.add(new Sobremesas("quindim", 10, 0));
		todasSobremesas.add(new Sobremesas("doce_leite", 5, 0));

		Pedidos novoPedido = new Pedidos("paul",
				new ListagemPedido(todasPizzas, todasBebidas, todasSobremesas, 0, 0, 0), 0);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = mapper.writeValueAsString(novoPedido);

		System.out.println(json);

		// System.out.printf("Selected %s pizza(s) of %s", qty, result);
		model.addAttribute("resultCalculate", precoPizza);
		model.addAttribute("postReq", pizzaFromPost);
		return "blank";
	}
}
