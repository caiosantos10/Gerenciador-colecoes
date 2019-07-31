package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uepb.gerenciador.model.Emprestimo;

/**
 * Respons�vel pela persistencia dos dados de Emprestimo ao banco de dados
 * @author Caio e Jose George
 *
 */
public interface EmprestimoRepository extends JpaRepository <Emprestimo, Integer>{

	@Query("SELECT obj FROM Emprestimo obj WHERE obj.usuario.id = :id")
	public List<Emprestimo> findAll(@Param("id") Integer id);
	
}
