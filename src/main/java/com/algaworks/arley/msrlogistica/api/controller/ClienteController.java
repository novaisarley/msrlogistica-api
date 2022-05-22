package com.algaworks.arley.msrlogistica.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.arley.msrlogistica.domain.model.Cliente;
import com.algaworks.arley.msrlogistica.domain.repository.ClienteRepository;
import com.algaworks.arley.msrlogistica.domain.service.ClienteService;

@RestController()
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repositoryC;

	@Autowired
	private ClienteService serviceC;

	@GetMapping
	public List<Cliente> listarClientes() {

		return repositoryC.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = repositoryC.findById(clienteId);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
			// Retorna o CODIGO 200 juntamente com o Cliente
		}
		// Retorna o CODIGO 404
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente) {
		// O @Valid valida as especificações definidas na classe(Cliente) antes de
		// executar o metodo
		// Com ele o erro retornado nao é mais Internal Server Error e sim o Bad Request
		return serviceC.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if (!repositoryC.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(clienteId);
		cliente = serviceC.salvar(cliente);

		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId) {
		if (repositoryC.existsById(clienteId)) {
			serviceC.excluir(clienteId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
