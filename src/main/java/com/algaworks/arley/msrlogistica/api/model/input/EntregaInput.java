package com.algaworks.arley.msrlogistica.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.algaworks.arley.msrlogistica.api.model.ClienteResumidoModel;
import com.algaworks.arley.msrlogistica.api.model.DestinatarioModel;
import com.algaworks.arley.msrlogistica.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaInput {
	@Valid
	@NotNull
	private ClienteIdInput cliente;
	
	@Valid
	@NotNull
	private DestinatarioInput destinatario;
	
	@NotNull
	private BigDecimal taxa;
}
