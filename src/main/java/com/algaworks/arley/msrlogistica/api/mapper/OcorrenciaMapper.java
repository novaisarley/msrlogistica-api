package com.algaworks.arley.msrlogistica.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.algaworks.arley.msrlogistica.api.model.EntregaModel;
import com.algaworks.arley.msrlogistica.api.model.OcorrenciaModel;
import com.algaworks.arley.msrlogistica.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {
	private ModelMapper mapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return mapper.map(ocorrencia, OcorrenciaModel.class);
	}
	
	public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias){
		return mapper.map(ocorrencias, new TypeToken<List<OcorrenciaModel>>() {}.getType());
	}
}
