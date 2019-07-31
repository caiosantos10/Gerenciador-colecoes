 package com.uepb.gerenciador.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.uepb.gerenciador.exception.ItemInexistenteException;
/**
 * <h1>Iterface com metodos CRUD de controle dos objetos </h1>
 * @author Caio e Jose George
 *
 * @param <TIPO> tipo do objeto que sera tratado
 */
public interface IObjectController <TIPO> {
	/**
	 * Cria objeto
	 * @param item objeto a ser criado
	 * @return objeto criado
	 */
	public TIPO createObject(TIPO item);
	/**
	 * Retorna todos os objetos
	 * @return lista de objetos
	 */
	public List<TIPO> getAllObject();
	/**
	 * Recupera objeto por id
	 * @param itemId
	 * @return Objeto recuperado
	 * @throws ItemInexistenteException caso o objeto nao exista
	 */
	public TIPO getObjectById(Integer itemId) throws ItemInexistenteException;
	/**
	 * edita Objeto
	 * @param itemId id do objeto que sera editado
	 * @param itemDetails objeto com novos dados
	 * @return objeto atualizado
	 */
	public TIPO updateObject(Integer itemId, TIPO itemDetails);
	/**
	 * Deleta objeto
	 * @param itemId id do objeto
	 * @return status da operacao
	 * @throws ItemInexistenteException caso o objeto nao exista
	 */
	public ResponseEntity<?> deleteObject(Integer itemId) throws ItemInexistenteException;
}
