package com.uepb.gerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uepb.gerenciador.service.impl.UserServiceImpl;
import com.uepb.gerenciador.service.impl.WishListServiceImpl;

/**
 * @author Caio e Jose George
 */
@Controller
public class DashController {
	
	@Autowired
	WishListServiceImpl wishService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getDash(Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("userlogado", UserServiceImpl.authenticated().getUsername());
		model.addAttribute("desejos", wishService.getAll());
		model.addAttribute("precoTotal", wishService.getPrecoTotal());
		mv.setViewName("inicio");
		return mv;
	}

	
}
