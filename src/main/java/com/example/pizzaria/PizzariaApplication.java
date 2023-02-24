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

@SpringBootApplication
// @RestController
@Controller
public class PizzariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzariaApplication.class, args);
	}

	@RequestMapping("/")
	public String searchDb(Model model) {
		// Coloca os dados no db
		DB_Populate populate = new DB_Populate("produtos", "nome_produto", Produtos.class);
		populate.populateDBMockupData();

		String teste_1 = "É o Tchan!";
		model.addAttribute("teste_1", teste_1);
		return "teste";
	}

	@PostMapping("/pedidos")
	@ResponseBody
	public String postBody(HttpServletRequest request) {

		// Cria um mockup dos pedidos e coloca em uma "variavel"
		PedidosCliente testPedidos = new PedidosCliente();
		testPedidos.createTestPedidos();
		HashMap<String, ArrayList<ArrayList<String>>> pedidos = testPedidos.getPedidos();

		// teste busca no db
		DB_Search buscaDb = new DB_Search("produtos_a", Produtos.class);
		String dbGetResult = buscaDb.searchNameDb("nome_produto", "marguerita");
		String testGetResult = buscaDb.searchNameDb("nome_produto", "maconha");
		System.out.println(dbGetResult);
		System.out.println(testGetResult);

		// Calcula os preços baseado no Mockup
		// ...
		PedidosCalculate calculadora = new PedidosCalculate("produtos_a", "nome_produto", Produtos.class, pedidos);
		int total = calculadora.calculate();
		System.out.println(total);

		// Test for receiving data from post request
		String result = request.getParameter("pizzas");
		String qty = request.getParameter("quantity");
		System.out.printf("Selected %s pizza(s) of %s", qty, result);
		return result;
	}
}
