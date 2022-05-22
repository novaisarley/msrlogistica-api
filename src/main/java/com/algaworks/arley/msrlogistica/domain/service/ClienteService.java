package com.algaworks.arley.msrlogistica.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.arley.msrlogistica.domain.exception.NegocioException;
import com.algaworks.arley.msrlogistica.domain.model.Cliente;
import com.algaworks.arley.msrlogistica.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositoryC;

	
	public Cliente buscarCliente(Long idCliente) {
		Optional<Cliente> cliente = repositoryC.findById(idCliente);

		if (!cliente.isPresent()) {
			throw new NegocioException("id do cliente informado não existe!");
		}
		
		return cliente.get();
	}

	/*
	 * Uma exceção qualquer ocorra, nesse caso um rollback deverá ser feito para o
	 * estado anterior (como se nenhuma das operações tivesse oc orrido).
	 */
	@Transactional
	public Cliente salvar(Cliente cliente) {
		Optional<Cliente> clienteAux = repositoryC.findByEmail(cliente.getEmail());
		if (clienteAux.isPresent()) {
			if (!clienteAux.get().equals(cliente)) {
				// esse if é para comparar se o cliente a "salvar" já é existente e está sendo
				// apenas atualizado.
				// nesse caso ele sabe q os email são iguais e verifica se os IDs são os mesmos,
				// caso nao retornamos a exception
				throw new NegocioException("Ja existe um cliente com este email");
			}
		}

		return repositoryC.save(cliente);
	}

	@Transactional
	public void excluir(Long clienteId) {
		repositoryC.deleteById(clienteId);
	}
}
