package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uepb.gerenciador.model.Item;


/**
 * Respons�vel pela persistencia dos dados de Item Generico ao banco de dados
 * @author Caio e Jose George
 *
 */
public interface ItemRepository  extends JpaRepository<Item, Integer> {
	/**
	 * Busca um Item por titulo 
	 */
	public List<Item> findByTitulo(String titulo);
	
	/**
	 * Retorna todos os itens do usuario 
	 * @param id do usuario
	 * @return Lista de itens do usuario
	 */
	@Query("SELECT obj FROM Item obj WHERE obj.usuario.id = :id")
	public List<Item> findAll(@Param("id") Integer id);
	
	/**
	 * Retorna Ranking de itens do usuario
	 * @param id do usuario
	 * @return ranking de itens do usuario
	 */
	@Query("SELECT obj FROM Item obj WHERE obj.usuario.id = :id ORDER BY obj.numEmprestimos DESC")
	public List<Item> ranking(@Param("id") Integer id);
	
	/**
	 * Filtra por titulo
	 * @param id
	 * @param titulo
	 * @return Lista de itens filtradas por titulo
	 */
	@Query("SELECT obj FROM Item obj WHERE obj.usuario.id = :id AND obj.titulo = :titulo")
	public List<Item> filtraPorTitulo(@Param("id") Integer id, @Param("titulo") String titulo);
	
	
}
