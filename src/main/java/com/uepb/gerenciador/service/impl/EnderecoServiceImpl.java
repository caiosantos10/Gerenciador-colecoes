package com.uepb.gerenciador.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.model.Endereco;
import com.uepb.gerenciador.repository.EnderecoRepository;

@Service
public class EnderecoServiceImpl {

	@Autowired
	private EnderecoRepository enderecoRepo;
	
	public Endereco create(Endereco endereco) {
		return enderecoRepo.save(endereco);
	}
	
}
