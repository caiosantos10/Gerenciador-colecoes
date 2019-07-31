package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uepb.gerenciador.model.HQ;
import com.uepb.gerenciador.model.Saga;


/**
 * Respons�vel pela persistencia dos dados de HQ ao banco de dados
 * @author Caio e Jose George
 *
 */
public interface HqRepository extends JpaRepository<HQ, Integer> {
	/**
	 * <p>Busca HQ por titulo</p>
	 */
	public List<HQ> findByTitulo(String titulo);
	/**
	 * <p>Busca HQ por Universo</p>
	 */
	public List<HQ> findByUniverso(String universo);
	/**
	 * <p>Busca HQ por Saga</p>
	 */
	public List<HQ> findBySaga(Saga saga);
	
	/**
	 * Retorna lista de hqs pertencentes a usuario
	 * @param id
	 * @return Lista de HQs do usuario
	 */
	@Query("SELECT obj FROM HQ obj WHERE obj.usuario.id = :id")
	public List<HQ> findAll(@Param("id") Integer id);
	
	/**
	 * Filtra por titulo
	 * @param id
	 * @param titulo
	 * @return Lista de HQ filtradas por titulo
	 */
	@Query("SELECT obj FROM HQ obj WHERE obj.usuario.id = :id AND obj.titulo = :titulo")
	public List<HQ> filtraPorTitulo(@Param("id") Integer id, @Param("titulo") String titulo);
	
	/**
	 * Filtra por editora
	 * @param id
	 * @param editora
	 * @return Lista de HQ filtradas por editora
	 */
	@Query("SELECT obj FROM HQ obj WHERE obj.usuario.id = :id AND obj.editora = :editora")
	public List<HQ> filtraPorEditora(@Param("id") Integer id, @Param("editora") String editora);
	
	/**
	 * Filtra por universo
	 * @param id
	 * @param universo
	 * @return Lista de HQ filtradas por universo
	 */
	@Query("SELECT obj FROM HQ obj WHERE obj.usuario.id = :id AND obj.universo = :universo")
	public List<HQ> filtraPorUniverso(@Param("id") Integer id, @Param("universo") String universo);
	
}
