package com.example.pizzaria;

import com.example.pizzaria.DB_Operations.DB_Populate;
import com.example.pizzaria.DB_Operations.DB_Search;
import com.example.pizzaria.Entities.*;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;

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
		// DB_Populate populate = new DB_Populate("produtos");
		// populate.populateDBMockupData();

		String teste_1 = "É o Tchan!";
		model.addAttribute("teste_1", teste_1);
		return "teste";
	}

	@PostMapping("/pedidos")
	// @ResponseBody // define se é para ele retornar uma pagina html ou a resposta
	// literal de alguma
	// coisa
	public String postBody(HttpServletRequest request, Model model) {

		// mockup data
		Pizzas pizza = new Pizzas();
		Bebidas bebida = new Bebidas();
		Sobremesas sobremesa = new Sobremesas();

		DB_Populate insertDados = new DB_Populate();
		insertDados.populateDBMockupData(); // creates a mockup array to be inserted
		insertDados.productsInsert(pizza, "pizzas");
		insertDados.productsInsert(bebida, "bebidas");
		insertDados.productsInsert(sobremesa, "sobremesas");
		insertDados.clientInsert("clientes");

		// DB_Search search = new DB_Search("bebidas", Bebidas.class);
		// int preco = search.searchIntegersDb("nome", "breja", "getPreco");

		// System.out.println(preco);

		// DB_Search search_client = new DB_Search("clientes", Clientes.class);
		// Clientes cliente = search_client.getClientById("clienteId", 1);
		// System.out.println(cliente);

		// Clientes cliente_2 = search_client.getClientByName("clientenome",
		// "rei_gado");
		// System.out.println(cliente_2);

		// DB_Search search_pizza = new DB_Search("pizzas", Pizzas.class);
		// List<?> pizz = search_pizza.getProductClass("nome", "calabresa");
		// System.out.println(pizz.get(0));

		// CreatePedido meu_pedido = new CreatePedido();
		// meu_pedido.testInsert();

		// exemplo bruto de como retornar valores a partir do pedido

		// acho que podemos fazer como DB_Search e por um Class<?> entity, ai vc
		// passa Products.class
		// StandardServiceRegistry registry = new
		// StandardServiceRegistryBuilder().configure().build();
		// SessionFactory sessionFactory = new
		// MetadataSources(registry).buildMetadata().buildSessionFactory();
		// Session session = sessionFactory.openSession();
		// String searchQuery = String.format("SELECT * FROM %s WHERE %s = %s",
		// "pedidos", "pedidoid",
		// "'" + "7" + "'"); // aqui fica igual no searchIndexDb() do DB_Search

		// SelectionQuery<Pedidos> dbQuery = session.createNativeQuery(
		// searchQuery,
		// Pedidos.class); // tambem igual searchIndexDb()

		// List<Pedidos> dbSearchResultList = dbQuery.getResultList();

		// Daqui para baixo vc faz o paranaue de passar pra um Objeto
		// generico ao invez de Pedidos.

		// Object = pedidoObjeto = dbSearchResultList.get(0)

		// Depois vc usa o mesmo outro paranaue pra invocar o metodo da entidade
		// por exemplo, getPizzaTotals() de Pedidos.
		// e joga o resultado pra uma int ou manda pra onde precisar.

		// Pedidos pedidoGet = dbSearchResultList.get(0);
		// System.out.println(pedidoGet.getPizzaTotals());

		PedidosCalculate newCalculo = new PedidosCalculate("pedidos", Pedidos.class);
		int resultado = newCalculo.returnFullTotals("pedidoid", "7", "getPizzaTotals");

		System.out.println(resultado);
		/*
		 * Vc deve estar olhando pra mim e pensando
		 * "legal, mas como eu vou até os pedidos e retorno os valores, nomes,
		 * whatever necessario pra calcular?"
		 * 
		 * Lembra, quandp vc dá search e procura, por exemplo, pelo pedido id 1
		 * ele vai no DB, faz todas as buscas e te retonar um OBJETO da CLASSE
		 * PEDIDOS, todo preenchido.
		 * 
		 * A idéia então é vc criar uns metodos getter pra ler os valores das
		 * Arraylists e retornar alguma coisa.
		 * 
		 * 
		 */

		return "blank";
	}
}
