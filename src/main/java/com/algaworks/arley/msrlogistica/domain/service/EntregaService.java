package com.algaworks.arley.msrlogistica.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

	public ResponseEntity<EntregaModel> getEntregaById(Long idEntrega) {
		Optional<Entrega> entregaOptional = entregaRepository.findById(idEntrega);
		if(!entregaOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Entrega entrega = entregaOptional.get();
		EntregaModel entregaModel = new EntregaModel();
		
		entregaModel.setId(entrega.getId());
		entregaModel.setNomeCliente(entrega.getCliente().getNome());
		entregaModel.setDataPedido(entrega.getDataPedido());
		entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());
		entregaModel.setDestinatario(new DestinatarioModel());
		entregaModel.setStatus(entrega.getStatus());
		entregaModel.setTaxa(entrega.getTaxa());
		
		Destinatario destinatario = entrega.getDestinatario();
		entregaModel.getDestinatario().setBairro(destinatario.getBairro());
		entregaModel.getDestinatario().setComplemento(destinatario.getComplemento());
		entregaModel.getDestinatario().setLogradouro(destinatario.getLogradouro());
		entregaModel.getDestinatario().setNome(destinatario.getNome());
		entregaModel.getDestinatario().setNumero(destinatario.getNumero());
		
		return ResponseEntity.ok(entregaModel);
	}
}
