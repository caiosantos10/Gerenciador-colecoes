package com.uepb.gerenciador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.model.Cidade;
import com.uepb.gerenciador.repository.CidadeRepository;

/**
 * Servico para tratar as entidades de Cidade
 * @author Jose George e Caio
 */
@Service
public class CidadeServiceImpl {

	@Autowired
	private CidadeRepository repo;
	
	/**
	 * <p> Este m�todo lista todos os registros de Cidade </p>
	 * @return uma lista com todos os registros de Cidade
	 */
	public List<Cidade> findByEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}
	
	public List<Cidade> getAll() {
		return repo.findAll();	
	}
	
	
}
