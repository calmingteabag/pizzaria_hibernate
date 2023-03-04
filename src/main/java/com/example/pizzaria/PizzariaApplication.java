package com.example.pizzaria;

import java.util.ArrayList;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.pizzaria.Entities.*;
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

		ArrayList<Object> entidades = new ArrayList<Object>();
		entidades.add(new Pizzas());
		entidades.add(new Bebidas());
		entidades.add(new Sobremesas());

		DB_Populate insertDados = new DB_Populate();
		insertDados.populateDBMockupData(); // creates a mockup array to be inserted
		insertDados.queryInsert(entidades, "pizzas");
		return "blank";
	}
}
