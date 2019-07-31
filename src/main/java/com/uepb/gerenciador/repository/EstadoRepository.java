package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uepb.gerenciador.model.Estado;


/**
 * Responsável pela persistencia dos dados de Estado (Divisao territorial) ao banco de dados
 * @author Caio e Jose George
 *
 */
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();
	
	
}
