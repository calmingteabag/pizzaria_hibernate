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

		// System.out.println(pedidos);
		// System.out.println(pedidos.get("pizzas"));

		// teste busca no db
		ExecuteSearch buscaDb = new ExecuteSearch("produtos_a", "nome_produto", Produtos.class);
		String dbGetResult = buscaDb.searchNameDb("'marguerita'");

		System.out.println(dbGetResult);

		// Calcula os preços baseado no Mockup
		// ...
		CalculatePedido calculadora = new CalculatePedido("produtos_a", "nome_produto", Produtos.class, pedidos);
		int total = calculadora.calculate();
		System.out.println(total);

		String result = request.getParameter("pizzas");
		String qty = request.getParameter("quantity");
		System.out.printf("Selected %s pizza(s) of %s", qty, result);
		return result;
	}
}
