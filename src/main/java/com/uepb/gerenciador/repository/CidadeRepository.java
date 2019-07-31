package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uepb.gerenciador.model.Cidade;

/**
 * Responsável pela persistencia dos dados de Cidade ao banco de dados
 * @author Caio e Jose George
 *
 */
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	/**
	 * Retornar uma lista de cidade por estado
	 * 
	 * @param estado_id
	 * @return lista de cidade por estado
	 */
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
	public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);
	
}
