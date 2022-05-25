package com.algaworks.arley.msrlogistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.arley.msrlogistica.api.mapper.OcorrenciaMapper;
import com.algaworks.arley.msrlogistica.api.model.OcorrenciaModel;
import com.algaworks.arley.msrlogistica.api.model.input.OcorrenciaInput;
import com.algaworks.arley.msrlogistica.domain.model.Entrega;
import com.algaworks.arley.msrlogistica.domain.model.Ocorrencia;
import com.algaworks.arley.msrlogistica.domain.repository.OcorrenciaRepository;
import com.algaworks.arley.msrlogistica.domain.service.EntregaService;
import com.algaworks.arley.msrlogistica.domain.service.OcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("entregas/{idEntrega}/ocorrencias")
public class OcorrenciaController {
	
	private OcorrenciaService ocorrenciaService;
	private EntregaService entregaService;
	private OcorrenciaRepository ocorrenciaRepository;
	private OcorrenciaMapper ocorrenciaMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrarOcorrencia(@PathVariable Long idEntrega,
			@Valid @RequestBody OcorrenciaInput ocorrencia) {
		
		Ocorrencia ocorrenciaRegistrada = ocorrenciaService
				.registrarOcorrencia(idEntrega, ocorrencia.getDescricao());
		return ocorrenciaMapper.toModel(ocorrenciaRegistrada);
	}
	
	@GetMapping
	public List<OcorrenciaModel> getOcorrencias(@PathVariable Long idEntrega){
		Entrega entrega = entregaService.buscarEntrega(idEntrega);
		List<Ocorrencia> ocorrencias = ocorrenciaRepository.findByEntregaId(idEntrega); 
		return ocorrenciaMapper.toCollectionModel(ocorrencias);
	}
}
