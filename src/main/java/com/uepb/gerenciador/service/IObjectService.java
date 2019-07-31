package com.uepb.gerenciador.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.ItemInexistenteException;
/**
 * Interface para tratar operacoes de objetos de determinado tipo
 * @author Caio e Jose George
 *
 * @param <TIPO> tipo do objeto
 */
@Service
public interface IObjectService <TIPO> {
	
	
	/**
	 * Responsável por criar um objeto de um tipo generico
	 * @param receber o tipo do objeto
	 * @return obj criado
	 */
	TIPO create(TIPO object); 
	
	/**
	 * responsável por consulta o bd e retornar o objeto procurado
	 * @param id
	 * @return objeto encontrado
	 */
	TIPO getById(Integer id);
	
	/**
	 * responsavel por atualizar um object
	 * 
	 * @param id
	 * @param object
	 * @return o object atualizado
	 */
	TIPO update(Integer id, TIPO object); 
	
	/**
	 * excluir um object do bd
	 * @param id
	 * @throws  caso o obj não exista lança a exceção: ItemInexistenteException
	 */
	void delete(Integer id) throws ItemInexistenteException; 
	
	/**
	 * 
	 * @return todos os object de um tipo que estão no bd
	 */
	List<TIPO> getAll();
	

}
