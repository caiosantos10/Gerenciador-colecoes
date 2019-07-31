package com.uepb.gerenciador.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.model.ItemFiltro;
import com.uepb.gerenciador.model.JogoDeTabuleiro;
import com.uepb.gerenciador.service.impl.JogoDeTabuleiroServiceImpl;

/**
 * Controle para tratar operacoes com JogoDeTabuleiro
 * @author Caio e Jose George
 */
@Controller
@RequestMapping("/jogo_tabuleiro")
public class JogoDeTabuleiroController implements IItemController<JogoDeTabuleiro> {
	private static final Logger logger = 
		      Logger.getLogger(JogoDeTabuleiroController.class);
	
	@Autowired
	JogoDeTabuleiroServiceImpl itemService;

	/**
	 * 
	 * Exibe formulario para cadastro de Jogo De Tabuleiro.
	 * @return view de cadastro
	 */
	@RequestMapping(path = "/adicionar", method = RequestMethod.GET)
	public String addJogoTabuleiro() {
		logger.info("Criando Jogo De Tabuleiro");
		return "item/jogotabuleiro/cadastro";
	}
	
	/** 	
	 * Salva Jogo de Tabuleiro
	 * @param item Jogo De Tabuleiro a ser salvo
	 * @param erros
	 * @return view para home 
	 */
	@PostMapping("/adicionar")
	@Override
	public String saveItem(JogoDeTabuleiro item, BindingResult errors) {
		/**
		 * verifica se existe algum campo não preenchido corretamente
		 */
		if(errors.hasErrors()) {
			logger.error("Erro ao criar o registro de Jogo de Tabuleiro: " + item.getTitulo());
			return "item/jogotabuleiro/cadastro";
		}
		
		itemService.create(item); 
		logger.info("Salvando registro de jogo tabuleiro: " + item.getTitulo());
		return "redirect:/home";
	}

	
	/**
	 * Deletar um jogo de tabuleiro
	 * @param id do Jogo De Tabuleiro.
	 * @return view para jogos
	 */
	@RequestMapping(path = "/deletar/{id}", method = RequestMethod.GET)
	@Override
	public String deleteItem(@PathVariable(name = "id") String id) {
		try {
			logger.info("Deletando Jogo De Tabuleiro.");
			itemService.delete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			logger.error("Erro ao deletar Jogo De Tabuleiro "+ JogoDeTabuleiroController.class );
			e.printStackTrace();
		} catch (ItemInexistenteException e) {
			logger.error("Jogo De Tabuleiro inexistente"+ JogoDeTabuleiroController.class);
			e.printStackTrace();
		}
		return "redirect:/jogo_tabuleiro/jogos";
	}

	/**
	 * Lista todos os jogos de tabuleiro
	 * @param model
	 * @return view para listagen de Jogo De Tabuleiro.
	 */
	@RequestMapping(path = "jogos", method = RequestMethod.GET)
	@Override
	public String getAllItens(Model model) {
		logger.info("Listando todos os Jogo De Tabuleiro.");
		model.addAttribute("jogos", itemService.getAll());
		return "item/jogotabuleiro/listar";
	}
	/**
	 * Carrega formulario para edicao de Jogo De Tabuleiro.
	 * @param model
	 * @param id identificador do Jogo De Tabuleiro.
	 * @return view para formulario de edicao
	 */
	@RequestMapping(path = "/editar/{id}", method = RequestMethod.GET)
    public String loadFormEdit(Model model, @PathVariable(value = "id") String id) {
		logger.info("Carregando formulario para edicao de Jogo De Tabuleiro.");
		model.addAttribute("JogoDeTabuleiro", itemService.getById(Integer.parseInt(id)));
		return "item/jogotabuleiro/editar";
    }
	
	/**
	 * Edita Jogo De Tabuleiro.
	 * @param item Jogo De Tabuleiro.
	 * @param erros
	 * @return view para jogos
	 */
	@PostMapping("/editar")
	@Override
	public String updateItem(JogoDeTabuleiro item, BindingResult errors) {
		
		if (errors.hasErrors()) {
			logger.error("Erro ao atualizar o registro de JogoDeTabuleiro: " + JogoDeTabuleiroController.class);
			return "item/jogotabuleiro/editar";
		}
		
		itemService.update(item.getId(), item);
		logger.info("Atualizando registro de JogoDeTabuleiro: " + item.getTitulo());
		return "redirect:/jogo_tabuleiro/jogos";
	}
	
	/**
	 * Lista itens e acordo com filtro
	 * @param model
	 * @param itemFiltro filtro a ser aplicado na bsuca
	 * @param errors
	 * @return Lista de itens de acordo com o filtro
	 */
	@PostMapping("/filtrar")
	public String buscaPorFiltro(Model model, @ModelAttribute @Valid ItemFiltro itemFiltro, BindingResult errors) {
		/**
		 * verifica se existe algum campo não preenchido corretamente
		 */
		if (errors.hasErrors()) {
			logger.error("Erro ao buscar o registro JogoDeTabuleiro pelo registro: " + JogoDeTabuleiroController.class);
			return "redirect:/jogo_tabuleiro/jogos";
		}

		model.addAttribute("jogos", itemService.buscaPorFiltro(itemFiltro.getTipoFiltro(), itemFiltro.getFiltro()));

		logger.info("Buscando item por filtro: " + itemFiltro.getFiltro());
		return "item/jogotabuleiro/listar";
	}
}
