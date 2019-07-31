package com.uepb.gerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uepb.gerenciador.model.WishList;
import com.uepb.gerenciador.service.impl.WishListServiceImpl;

@Controller
@RequestMapping("/wishlist")
public class WishListController {

	@Autowired
	private WishListServiceImpl service;

	/**
	 * 
	 * @return TELA DE CADASTRO DE WISHLIST
	 */
	@RequestMapping(path = "/adicionar", method = RequestMethod.GET)
	public String loadForm() {
		return "wishlist/cadastro";
	}
	
	/**
	 * Cadastra uma item de desejo e retonar pra home
	 * @param item
	 * @return TELA PRINCIPAL
	 */
	@PostMapping("/adicionar")
	public String saveItem(WishList item) {
		service.create(item);
		return "redirect:/home";
	}
	
}

