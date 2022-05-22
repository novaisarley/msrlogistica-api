package com.algaworks.arley.msrlogistica.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.arley.msrlogistica.domain.model.Entrega;
import com.algaworks.arley.msrlogistica.domain.model.StatusEntrega;
import com.algaworks.arley.msrlogistica.domain.repository.EntregaRepository;

@Service
public class EntregaService {
	
	@Autowired
	EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega solicitarEntrega(Entrega entrega) {
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
