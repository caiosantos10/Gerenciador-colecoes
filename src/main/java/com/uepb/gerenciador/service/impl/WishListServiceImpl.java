package com.uepb.gerenciador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.model.WishList;
import com.uepb.gerenciador.repository.WishListRepository;

@Service
public class WishListServiceImpl {

	@Autowired
	private WishListRepository repo; 
	
	@Autowired
	private UsuarioServiceImpl userService;
	
	List<WishList> lista;
	
	public WishList create(WishList item) {
		
		/**
		 * User autenticado
		 */
		Usuario user = userService.getById(UserServiceImpl.authenticated().getId());

		/**
		 * Caso não exista retornar null
		 */
		if (user == null) {
			return null;
		}
		
		item.setUsuario(user);
		
		return repo.save(item);
	}
	
	/**
	 * 
	 * @return LISTA COM O VALOR TOTAL DOS ITENS DA WISHLIST
	 */
	public double getPrecoTotal(){

		lista = getAll();
		double soma = 0.0;
	
		for(WishList x : lista) {
			soma = soma + x.getPreco();
		}
		
		return soma;
	}
	
	public List<WishList> getAll(){
		return repo.findAll(UserServiceImpl.authenticated().getId());
	}
	
	
}
