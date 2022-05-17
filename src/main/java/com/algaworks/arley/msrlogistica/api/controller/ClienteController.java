package com.algaworks.arley.msrlogistica.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.arley.msrlogistica.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> helloWorld() {
		ArrayList<Cliente> lista = new ArrayList<>();
		
		var c = new Cliente();
		c.setId(1L);
		c.setNome("Arley");
		c.setEmail("arley@gmail.com");
		c.setTelefone("84994243220");
		
		var c1 = new Cliente();
		c1.setId(2L);
		c1.setNome("Jão");
		c1.setEmail("joao@gmail.com");
		c1.setTelefone("84994243220");
		
		return Arrays.asList(c, c1);
	}
}
