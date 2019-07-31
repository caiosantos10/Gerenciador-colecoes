package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uepb.gerenciador.model.JogoDeVideogame;


/**
 * Respons�vel pela persistencia dos dados de JogoDeVideogame ao banco de dados
 * @author Caio e Jose George
 *
 */
public interface JogoDeVideogameRepository extends JpaRepository<JogoDeVideogame, Integer> {
	/**
	 * <p>Busca JogoVideoGame por titulo</p>
	 */
	public List<JogoDeVideogame> findByTitulo(String titulo);
	
	/**
	 * <p>Busca JogoVideoGame por console</p>
	 */
	public List<JogoDeVideogame> findByConsole(String console);
	
	/**
	 * Retorna os JogoDeVideogame do usuario
	 * @param id do usuario
	 * @return Lista de jogos de JogoDeVideogame do usuario
	 */
	@Query("SELECT obj FROM JogoDeVideogame obj WHERE obj.usuario.id = :id")
	public List<JogoDeVideogame> findAll(@Param("id") Integer id);
	
	/**
	 * Filtra por titulo
	 * @param id
	 * @param titulo
	 * @return Lista de JogoDeVideogame filtradas por titulo
	 */
	@Query("SELECT obj FROM JogoDeVideogame obj WHERE obj.usuario.id = :id AND obj.titulo = :titulo")
	public List<JogoDeVideogame> filtraPorTitulo(@Param("id") Integer id, @Param("titulo") String titulo);
	
	/**
	 * Filtra por console
	 * @param id
	 * @param editora
	 * @return Lista de JogoDeVideogame filtradas por editora
	 */
	@Query("SELECT obj FROM JogoDeVideogame obj WHERE obj.usuario.id = :id AND obj.console = :console")
	public List<JogoDeVideogame> filtraPorConsole(@Param("id") Integer id, @Param("console") String console);
}
