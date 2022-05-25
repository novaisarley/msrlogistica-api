package com.algaworks.arley.msrlogistica.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.algaworks.arley.msrlogistica.api.mapper.EntregaMapper;
import com.algaworks.arley.msrlogistica.api.model.DestinatarioModel;
import com.algaworks.arley.msrlogistica.api.model.EntregaModel;
import com.algaworks.arley.msrlogistica.domain.exception.NegocioException;
import com.algaworks.arley.msrlogistica.domain.model.Cliente;
import com.algaworks.arley.msrlogistica.domain.model.Destinatario;
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
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
	
	@Transactional
	public Entrega buscarEntrega(Long id) {
		Optional<Entrega> entregaO = entregaRepository.findById(id);
		if(!entregaO.isPresent()) {
			throw new NegocioException("Entrega não encontrada");
		}
		
		return entregaO.get();
		
	}
	
	@Transactional
	public void finalizarEntrega(Long entregaId) {
		Entrega entrega = buscarEntrega(entregaId);
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}

	
}
