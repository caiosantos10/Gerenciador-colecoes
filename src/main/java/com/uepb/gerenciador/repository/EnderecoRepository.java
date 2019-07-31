package com.uepb.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uepb.gerenciador.model.Endereco;

/**
 * Responsável pela persistencia dos dados de Endereco ao banco de dados
 * @author Caio e Jose George
 *
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
