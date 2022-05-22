package com.algaworks.arley.msrlogistica.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.arley.msrlogistica.domain.model.Entrega;
import com.algaworks.arley.msrlogistica.domain.repository.EntregaRepository;
import com.algaworks.arley.msrlogistica.domain.service.EntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	@Autowired
	private EntregaService entregaService;

	@Autowired
	private EntregaRepository entregaRepository;
	
	@GetMapping
	public List<Entrega> getEntregas() {
		return entregaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitarEntrega(@Valid @RequestBody Entrega entrega) {
		return entregaService.solicitarEntrega(entrega);
		
	}
	
	@GetMapping("/{idEntrega}")
	public ResponseEntity<Entrega> getEntregaById(@PathVariable Long idEntrega){
		return entregaService.getEntregaById(idEntrega);
	}
}
