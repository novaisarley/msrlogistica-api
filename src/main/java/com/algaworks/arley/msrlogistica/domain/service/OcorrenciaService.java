package com.algaworks.arley.msrlogistica.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.arley.msrlogistica.api.model.OcorrenciaModel;
import com.algaworks.arley.msrlogistica.domain.exception.NegocioException;
import com.algaworks.arley.msrlogistica.domain.model.Entrega;
import com.algaworks.arley.msrlogistica.domain.model.Ocorrencia;
import com.algaworks.arley.msrlogistica.domain.repository.EntregaRepository;
import com.algaworks.arley.msrlogistica.domain.repository.OcorrenciaRepository;

import lombok.AllArgsConstructor;

//Outra opção para nao usar o @Autowired toda vez
@AllArgsConstructor
@Service
public class OcorrenciaService {
	
	private EntregaService entregaService;
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Transactional
	public Ocorrencia registrarOcorrencia(Long idEntrega, String descricao) {
		
		Entrega entrega = entregaService.buscarEntrega(idEntrega);
		
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(entrega);
		
		return ocorrenciaRepository.save(ocorrencia);
	}
	
}
