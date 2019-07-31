package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uepb.gerenciador.model.JogoDeTabuleiro;


/**
 * Respons�vel pela persistencia dos dados de JogoDeTabuleiro ao banco de dados
 * @author Caio e Jose George
 *
 */
public interface JogoDeTabuleiroRepository extends JpaRepository<JogoDeTabuleiro, Integer> {
	/**
	 * Busca um jogo de tabuleiro por titulo 
	 */
	public List<JogoDeTabuleiro> findByTitulo(String titulo);
	
	/**
	 * Retorna uma lista de JogoDeTabuleiro pertencente ao usuario
	 * @param id o usuario
	 * @return lista de JogoDeTabuleiro do usuario
	 */
	@Query("SELECT obj FROM JogoDeTabuleiro obj WHERE obj.usuario.id = :id")
	public List<JogoDeTabuleiro> findAll(@Param("id") Integer id);
	
	/**
	 * Filtra por titulo
	 * @param id
	 * @param titulo
	 * @return Lista de JogoDeTabuleiro filtradas por titulo
	 */
	@Query("SELECT obj FROM JogoDeTabuleiro obj WHERE obj.usuario.id = :id AND obj.titulo = :titulo")
	public List<JogoDeTabuleiro> filtraPorTitulo(@Param("id") Integer id, @Param("titulo") String titulo);
}
