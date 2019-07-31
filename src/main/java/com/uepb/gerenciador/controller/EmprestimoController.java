package com.uepb.gerenciador.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uepb.gerenciador.exception.EmprestimoServicoException;
import com.uepb.gerenciador.model.Emprestimo;
import com.uepb.gerenciador.service.impl.AmigoServiceImpl;
import com.uepb.gerenciador.service.impl.EmprestimoServiceImpl;
import com.uepb.gerenciador.service.impl.ItemServiceImpl;

/**
 * <h1>Controller para tratar emprestimo</h1>
 * @author Jose George e Caio
 * @version 1.0.0
 */

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController{
	
	private static final Logger logger = 
		      Logger.getLogger(EmprestimoController.class);
	
	@Autowired
	private ItemServiceImpl item; 
	
	@Autowired
	private AmigoServiceImpl amigo;
	
	@Autowired
	private EmprestimoServiceImpl emprestimoService;
	/**
	 * Carrega formulario para criacao de emprestimo
	 * @param model
	 * @return view para cadastro de emprestimos
	 */
	@GetMapping("cadastro")
	public String loadForm(Model model) {
		logger.info("Carregando formulario de emprestimo");
		model.addAttribute("itens",item.getAll()); 
		model.addAttribute("amigos", amigo.getAll());
		return "emprestimo/cadastro";
	}
	
	/**
	 * Realiza emprestimo
	 * @param emprestimo
	 * @return view para home do sistema
	 */
	@PostMapping("cadastro")
	public String realizarEmprestimo(@Valid Emprestimo emprestimo) throws EmprestimoServicoException{
		if(amigo == null) {
			logger.warn("amigo eh nulo "+ EmprestimoController.class);
			return "redirect:/emprestimo/cadastro";
		}
		
		if(item == null) {
			logger.warn("item eh nulo "+ EmprestimoController.class);
			return "redirect:/emprestimo/cadastro";
		}
		
		logger.info("Realizando Emprestimo");
		emprestimoService.create(emprestimo);
		return "redirect:/home";
		
	}
	
	/**
	 * Lista todos os emprestimos
	 * @param model
	 * @return view para lista de emprestimos
	 */
	@GetMapping("listar")
	public String loadListar(Model model) {
		logger.info("Listando emprestimos");
		model.addAttribute("emprestimos", emprestimoService.getAll());
		return "emprestimo/listar";
	}	
	
	/**
	 * Deleta emprestimo
	 * @param id identificador do emprestimo
	 * @return view para listagem de emprestimos
	 */
	@RequestMapping(path = "/deletar/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable(name = "id") String id) {
		try {
			logger.warn("Deletando emprestimo");
			emprestimoService.delete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			logger.warn("Erro ao deletar emprestimo "+ EmprestimoController.class);
			e.printStackTrace();
		}
		return "redirect:/emprestimo/listar";
	}

}

