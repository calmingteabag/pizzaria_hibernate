package com.example.pizzaria.Controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.pizzaria.DAO.RetrievePedido;
import com.example.pizzaria.Entities.Pedidos;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BuscarEntidades {
    @PostMapping("/retrieve_pedido")
    public String retrievePedido(@RequestParam String form_pedido_id,
            HttpServletRequest request, Model model) {

        try {
            int formPedidoId = Integer.parseInt(form_pedido_id);
            RetrievePedido retrievePedido = new RetrievePedido();
            // Pedidos pedidoRetrieved = retrievePedido.getPedido(formPedidoId);
            ArrayList<?> pizzas = retrievePedido.getPedidosNames(formPedidoId, "pizzas");
            ArrayList<?> bebidas = retrievePedido.getPedidosNames(formPedidoId, "bebidas");
            ArrayList<?> sobremesas = retrievePedido.getPedidosNames(formPedidoId, "sobremesas");

            model.addAttribute("pizzas", pizzas);
            model.addAttribute("bebidas", bebidas);
            model.addAttribute("sobremesas", sobremesas);

            ArrayList<?> pizzaPreco = retrievePedido.getPedidosPrecos(formPedidoId, "pizzas");
            ArrayList<?> bebidasPreco = retrievePedido.getPedidosPrecos(formPedidoId, "bebidas");
            ArrayList<?> sobremesasPreco = retrievePedido.getPedidosPrecos(formPedidoId, "sobremesas");

            model.addAttribute("pizzasPreco", pizzaPreco);
            model.addAttribute("bebidasPreco", bebidasPreco);
            model.addAttribute("sobremesasPreco", sobremesasPreco);

            String nomeCliente = retrievePedido.getClienteNome(formPedidoId);

            model.addAttribute("nomeCliente", nomeCliente);

            Integer pizzaTotais = retrievePedido.getTotaisPorTipo(formPedidoId, "pizzas");
            Integer bebidaTotais = retrievePedido.getTotaisPorTipo(formPedidoId, "bebidas");
            Integer sobremesaTotais = retrievePedido.getTotaisPorTipo(formPedidoId, "sobremesas");

            model.addAttribute("totalPizzas", pizzaTotais);
            model.addAttribute("totalBebidas", bebidaTotais);
            model.addAttribute("totalSobremesas", sobremesaTotais);

            Integer totalGeral = retrievePedido.getTotalGeral(formPedidoId);
            model.addAttribute("totalGeral", totalGeral);

        } catch (Exception e) {
            System.out.println(e);
        }

        return "blank";
    }

    @PostMapping("/pedidos")
    // @ResponseBody
    // define se é para ele retornar uma pagina html ou a resposta
    // literal de algumacoisa
    public String pedidos(HttpServletRequest request, Model model) {

        // mockup data
        // Pizzas pizza = new Pizzas();
        // Bebidas bebida = new Bebidas();
        // Sobremesas sobremesa = new Sobremesas();

        // DB_Populate insertDados = new DB_Populate();
        // insertDados.populateDBMockupData(); // creates a mockup array to be inserted
        // insertDados.productsInsert(pizza, "pizzas", "nome");
        // insertDados.productsInsert(bebida, "bebidas", "nome");
        // insertDados.productsInsert(sobremesa, "sobremesas", "nome");

        // CreatePedido newPedido = new CreatePedido();
        // newPedido.testInsert();

        RetrievePedido retrievePedido = new RetrievePedido();
        Pedidos retrieved = retrievePedido.getPedido(1);
        System.out.println(retrieved.getProductNamesList("pizzas"));
        System.out.println(retrieved.getProductNamesList("bebidas"));
        System.out.println(retrieved.getProductNamesList("sobremesas"));
        System.out.println(retrieved.getProductPriceList("pizzas"));
        System.out.println(retrieved.getProductPriceList("bebidas"));
        System.out.println(retrieved.getProductPriceList("sobremesas"));
        System.out.println(retrieved.getProductQtyList("pizzas"));
        System.out.println(retrieved.getProductQtyList("bebidas"));
        System.out.println(retrieved.getProductQtyList("sobremesas"));

        // PedidosPizzas pedidoPizza = retrieved.getPedidosPizzas().get(0);
        // Pizzas pizza = pedidoPizza.getPizza();
        // System.out.println(retrieved.getPedidosPizzas());
        // System.out.println(pedidoPizza.getPizza());
        // System.out.println(pizza.getNome());
        // System.out.println(pizza.getDescricao());
        // System.out.println(pizza.getPreco());
        // System.out.println(pedidoPizza.getPedido());

        // System.out.println(pedidoPizza.getPizza().getNome());

        // System.out.println(retrieved.getPedidosPizzas().get(0).getPizza().getNome());

        // Hibernate.initialize(pedido.getPedidosPizzas());
        // System.out.println(pedido);
        // pedido.getPizzaTotals();
        // int pizzatotals = pedido.getPizzaTotals();

        // System.out.println(pizzatotals);
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

        // PedidosCalculate newCalculo = new PedidosCalculate("pedidos", Pedidos.class);
        // String[] metodos = { "getPizzaTotals", "getBebidaTotals",
        // "getSobremesaTotals" };
        // int resultado = newCalculo.returnFullTotals("pedidoid", "7", metodos);

        // System.out.println(resultado);
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
