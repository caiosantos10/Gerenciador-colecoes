package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uepb.gerenciador.model.Amigo;

/**
 * Respons�vel pela persistencia dos dados de Amigo ao banco de dados
 * @author Caio e Jose George
 *
 */
public interface AmigoRepository extends JpaRepository<Amigo, Integer>{


	@Query("SELECT obj FROM Amigo obj WHERE obj.usuario.id = :id")
	public List<Amigo> findAll(@Param("id") Integer id);
	
}
