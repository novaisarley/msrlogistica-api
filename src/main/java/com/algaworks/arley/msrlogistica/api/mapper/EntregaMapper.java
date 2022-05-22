package com.algaworks.arley.msrlogistica.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.arley.msrlogistica.api.model.EntregaModel;
import com.algaworks.arley.msrlogistica.domain.model.Entrega;

@Component
public class EntregaMapper {

	@Autowired
	private ModelMapper mapper;

	public EntregaModel toModel(Entrega entrega) {
		return mapper.map(entrega, EntregaModel.class);
	}
	
	public List<EntregaModel> toModel(List<Entrega> entregas) {
		return mapper.map(entregas, new TypeToken<List<EntregaModel>>() {}.getType());
		//Usei o TypeToken, pois não é possivel usar "List<EntregaModel>.class"
	}
}
