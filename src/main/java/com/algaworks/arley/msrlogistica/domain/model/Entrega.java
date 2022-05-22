package com.algaworks.arley.msrlogistica.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	
	//Para um relacioanmento de chave estrangeira
	//Nome padrao da coluna vai ser "cliente_id"
	//Caso queira mudar o nome padrao é so usar o @JoinColumn(name = "NOVO_NOME")
	@ManyToOne
	private Cliente cliente;
	
	//Cria colunas para todos os atributos
	@Embedded
	private Destinatario destinatario;
	
	private BigDecimal taxa;
	
	/*esses READ_ONLY são para o consumidor da API nao 
	poder alterar diretamente esses dados no momento que passar 
	o corpo da requisição*/
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;
}
