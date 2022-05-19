package com.algaworks.arley.msrlogistica.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.arley.msrlogistica.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
