package com.algaworks.arley.msrlogistica.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.arley.msrlogistica.domain.exception.NegocioException;
import com.algaworks.arley.msrlogistica.domain.model.Cliente;
import com.algaworks.arley.msrlogistica.domain.model.Entrega;
import com.algaworks.arley.msrlogistica.domain.model.StatusEntrega;
import com.algaworks.arley.msrlogistica.domain.repository.ClienteRepository;
import com.algaworks.arley.msrlogistica.domain.repository.EntregaRepository;

@Service
public class EntregaService {
	
	@Autowired
	private EntregaRepository entregaRepository;
		
	@Autowired
	private ClienteService clienteService;
	
	@Transactional
	public Entrega solicitarEntrega(Entrega entrega) {
		Cliente cliente = clienteService.buscarCliente(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
