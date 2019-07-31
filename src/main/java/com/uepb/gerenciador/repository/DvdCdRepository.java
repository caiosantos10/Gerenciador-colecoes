package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uepb.gerenciador.model.DvdCd;

/**
 * Especifica metodos utilizados para operacoes de CRUD sobre itens do tipo DvdCd
 * @author Caio Silva e Jose George 
 */
public interface DvdCdRepository extends JpaRepository<DvdCd, Integer> {
	
	/**
	 * Busca um DVD por marca 
	 */
	public List<DvdCd> findByMarca(String marca);
	
	/**
	 * Busca um DVD por conteudo 
	 */
	public List<DvdCd> findByConteudo(String conteudo);
	
	/**
	 * Busca um DVD por titulo 
	 */
	public List<DvdCd> findByTitulo(String titulo);
	
	/**
	 * Busca DvdCd pertecentes a usuario
	 * @param id 
	 * @return Lista de DvdCd do usuario
	 */
	@Query("SELECT obj FROM DvdCd obj WHERE obj.usuario.id = :id")
	public List<DvdCd> findAll(@Param("id") Integer id);
	
	/**
	 * Filtra por titulo
	 * @param id
	 * @param titulo
	 * @return Lista de DvdCd filtradas por titulo
	 */
	@Query("SELECT obj FROM DvdCd obj WHERE obj.usuario.id = :id AND obj.titulo = :titulo")
	public List<DvdCd> filtraPorTitulo(@Param("id") Integer id, @Param("titulo") String titulo);
	
	/**
	 * Filtra por marca
	 * @param id
	 * @param titulo
	 * @return Lista de DvdCd filtradas por marca
	 */
	@Query("SELECT obj FROM DvdCd obj WHERE obj.usuario.id = :id AND obj.marca = :marca")
	public List<DvdCd> filtraPorMarca(@Param("id") Integer id, @Param("marca") String marca);
	
}
