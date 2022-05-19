package com.algaworks.arley.msrlogistica.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.arley.msrlogistica.domain.model.Cliente;
import com.algaworks.arley.msrlogistica.domain.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository repositoryC;
	
	@GetMapping("/clientes")
	public List<Cliente> helloWorld() {		
		
		return repositoryC.findAll();
	}
}
