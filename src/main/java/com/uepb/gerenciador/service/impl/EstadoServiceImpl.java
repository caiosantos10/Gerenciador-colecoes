package com.uepb.gerenciador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.model.Estado;
import com.uepb.gerenciador.repository.EstadoRepository;

/**
 * Servico para tratar as entidades de Estado
 * @author Jose George e Caio
 */
@Service
public class EstadoServiceImpl {

	@Autowired
	private EstadoRepository repo;
	
	/**
	 * <p> Este m�todo lista todos os registros de Estado </p>
	 * @return uma lista com todos os registros de Estado
	 */
	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}
	
	
}
