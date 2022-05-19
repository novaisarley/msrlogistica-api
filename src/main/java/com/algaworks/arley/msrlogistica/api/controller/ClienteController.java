package com.algaworks.arley.msrlogistica.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.arley.msrlogistica.domain.model.Cliente;
import com.algaworks.arley.msrlogistica.domain.repository.ClienteRepository;

@RestController()
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repositoryC;
	
	@GetMapping
	public List<Cliente> listarClientes() {		
		
		return repositoryC.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = repositoryC.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
			//Retorna o CODIGO 200 juntamente com o Cliente
		}else {
			//Retorna o CODIGO 404
			return ResponseEntity.notFound().build(); 
		}		
	}
	
	
}
