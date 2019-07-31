package com.uepb.gerenciador.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * 
 * @author Jose George e Caio
 *
 *Interface que gerencia as operações de controller dos itens
 *
 * @param <TIPO> TIPO pode ser qualquer item tais como: Jogo, HQ, DVD.
 */

public interface IItemController<TIPO> {

	/**
	 * responsável por salvar um item. 
	 * 
	 * @param item
	 * @param errors
	 * @return a view de home se o item foi salvo.
	 */
	String saveItem(TIPO item, BindingResult errors); 
	
	/**
	 * recebe um id e deleta um item.
	 * @param id 
	 * @return a view que lista os itens salvos TIPO salvo no banco.
	 */
	String deleteItem(String id); 
	
	/**
	 * responsável por retornar a lista com todos os itens
	 * @param model
	 * @return
	 */
	String getAllItens(Model model);
	
	
	/**
	 * responsável por atualizar um item de acervo
	 * @param item
	 * @return 
	 */
	String updateItem(TIPO item, BindingResult errors);
}
