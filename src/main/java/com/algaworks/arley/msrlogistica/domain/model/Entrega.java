package com.algaworks.arley.msrlogistica.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.arley.msrlogistica.domain.ValidationGroups;
import com.algaworks.arley.msrlogistica.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Para um relacionamento de chave estrangeira
	//Nome padrao da coluna vai ser "cliente_id"
	//Caso queira mudar o nome padrao é so usar o @JoinColumn(name = "NOVO_NOME")
	@ManyToOne
	private Cliente cliente;
	
	//Cria colunas para todos os atributos
	@Embedded
	private Destinatario destinatario;
	
	//"entrega" é o nome da propriedade do lado inverso (Ocorrencia)
	/*@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrrencias;*/
	
	@NotNull
	private BigDecimal taxa;

	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	private OffsetDateTime dataPedido;
	
	private OffsetDateTime dataFinalizacao;
	
	public void finalizar() {
		if(!this.getStatus().equals(StatusEntrega.PENDENTE)) {
			throw new NegocioException("Entrega não pode ser finalizada!");
		}
		this.setStatus(StatusEntrega.FINALIZADA);
		this.setDataFinalizacao(OffsetDateTime.now());
	}
	
	/*public Ocorrencia adicionarOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegisto(OffsetDateTime.now());
		ocorrencia.setEntrega(this);

		this.getOcorrrencias().add(ocorrencia);
		
		return ocorrencia;
	}*/
}
