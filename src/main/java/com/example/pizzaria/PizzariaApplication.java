package com.example.pizzaria;

import com.example.pizzaria.ExecuteSearch;

import org.springframework.ui.Model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
		// ExecuteSearch search = new ExecuteSearch("produtos_a", "nome_produto",
		// Produtos.class);
		// String resultado = search.searchNameDb("'marguerita'");
		// return resultado;

		return "index";
	}

	/*
	 * Idéia geral que tenho é, receber os dados em json vindos do
	 * 'frontend', e processar aqui. Vou usar a mesma estrategia
	 * da versão JS, que o json é separado por tipos (pizza, bebida
	 * e doces) e cada um deles tem uma array com os pedidos. Por
	 * exemplo:
	 * 
	 * pedidos : {
	 * pizzas : {
	 * [marguerita, 2],
	 * [napolitana,1],
	 * },
	 * 
	 * bebidas : {
	 * [coca, 3]
	 * }}
	 * 
	 * Depois eu itero a array, pego os pedidos, faço o calculo e
	 * entrego a descricao dos pedidos, os valores e o total para
	 * o frontend.
	 */
	@PostMapping("/pedidos")
	public String postBody(@RequestBody String fullName) {
		return fullName;
	}
}
