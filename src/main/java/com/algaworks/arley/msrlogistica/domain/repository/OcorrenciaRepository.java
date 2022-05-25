package com.algaworks.arley.msrlogistica.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.arley.msrlogistica.domain.model.Entrega;
import com.algaworks.arley.msrlogistica.domain.model.Ocorrencia;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>{
	public List<Ocorrencia> findByEntregaId(Long entregaId);
}
