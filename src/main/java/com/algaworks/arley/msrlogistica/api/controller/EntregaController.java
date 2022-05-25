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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.arley.msrlogistica.api.mapper.EntregaMapper;
import com.algaworks.arley.msrlogistica.api.model.EntregaModel;
import com.algaworks.arley.msrlogistica.api.model.input.EntregaInput;
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
	@Autowired
	private EntregaMapper entregaMapper;
	
	@GetMapping
	public List<EntregaModel> getEntregas() {
		return entregaMapper.toModel(entregaRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitarEntrega(@Valid @RequestBody EntregaInput entrega) {
		Entrega entregaConvertida = entregaMapper.toEntity(entrega);
		Entrega entregaCriada = entregaService.solicitarEntrega(entregaConvertida);
		
		return entregaMapper.toModel(entregaCriada);
	}
	
	@GetMapping("/{idEntrega}")
	public ResponseEntity<EntregaModel> getEntregaById(@PathVariable Long idEntrega){
		Optional<Entrega> entregaOptional = entregaRepository.findById(idEntrega);
		if(!entregaOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Entrega entrega = entregaOptional.get();
		
		return ResponseEntity.ok(entregaMapper.toModel(entrega)); 
	}
	
	@PutMapping("/{idEntrega}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizarEntrega(@PathVariable Long idEntrega){
		entregaService.finalizarEntrega(idEntrega);
	}
}
