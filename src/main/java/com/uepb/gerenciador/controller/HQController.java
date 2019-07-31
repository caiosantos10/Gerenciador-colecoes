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
import com.uepb.gerenciador.model.HQ;
import com.uepb.gerenciador.model.ItemFiltro;
import com.uepb.gerenciador.service.impl.HqServiceImpl;

/**
 * <h1>Controlador para servi�os de HQ</h1>
 * 
 * @author Caio e Jose George
 *
 */
@Controller
@RequestMapping("/hq")
public class HQController implements IItemController<HQ> {
	private static final Logger logger = Logger.getLogger(HQController.class);

	@Autowired
	HqServiceImpl itemService;

	/**
	 * Formulario para cadastro de HQ
	 * 
	 * @return view de cadastro
	 */
	@RequestMapping(path = "/adicionar", method = RequestMethod.GET)
	public String addHq() {
		logger.info("Cadastrando HQ");
		return "item/hq/cadastro";
	}

	/**
	 * Salva informacoes de HQ
	 * 
	 * @param item  Hq a ser salvo
	 * @param erros
	 * @return view para home do sistema
	 */
	@PostMapping("/adicionar")
	@Override
	public String saveItem(HQ item, BindingResult errors) {
		/**
		 * verifica se existe algum campo não preenchido corretamente
		 */
		if (errors.hasErrors()) {
			logger.error("Erro ao criar o registro de HQ: " + item.getTitulo());
			return "item/hq/cadastro";
		}

		itemService.create(item);
		logger.info("Criando registro de HQ: " + item.getTitulo());
		return "redirect:/home";
	}

	/**
	 * Deletar um hq
	 * 
	 * @param id identificador do hq
	 * @return view para hqs
	 */
	@RequestMapping(path = "/deletar/{id}", method = RequestMethod.GET)
	@Override
	public String deleteItem(@PathVariable(name = "id")String id) {
		try {
			logger.info("Deletando HQ");
			itemService.delete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			logger.error("Erro ao deletar HQ " + HQController.class);
			e.printStackTrace();
		} catch (ItemInexistenteException e) {
			logger.error("HQ inexistente " + HQController.class);
			e.printStackTrace();
		}
		return "redirect:/hq/hqs";
	}

	/**
	 * Lista todos os hq
	 * 
	 * @param model
	 * @return view para lista de hqs
	 */
	@RequestMapping(path = "hqs", method = RequestMethod.GET)
	@Override
	public String getAllItens(Model model) {
		logger.info("Listando HQs");
		model.addAttribute("hqs", itemService.getAll());
		return "item/hq/listar";
	}

	/**
	 * Carrega formulario para editar HQ
	 * 
	 * @param model
	 * @param id    do hq
	 * @return view para editar hq
	 */
	@RequestMapping(path = "/editar/{id}", method = RequestMethod.GET)
	public String loadFormEdit(Model model, @PathVariable(value = "id") String id) {
		model.addAttribute("HQ", itemService.getById(Integer.parseInt(id)));
		return "item/hq/editar";
	}

	/**
	 * Edita dados de HQ
	 * @param item HQ
	 * @param erros
	 * @return view para hqs
	 */
	@PostMapping("editar")
	@Override
	public String updateItem(HQ item, BindingResult errors) {

		if (errors.hasErrors()) {
			logger.error("Erro ao atualizar o registro de HQ: " + item.getTitulo());
			return "item/hq/cadastro";
		}

		itemService.update(item.getId(), item);
		logger.info("Atualizando registro de HQ: " + item.getTitulo());
		return "redirect:/hq/hqs";

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
			logger.error("Erro ao buscar o registro Item pelo registro: " + HQController.class);
			return "redirect:/hq/hqs";
		}

		model.addAttribute("hqs", itemService.buscaPorFiltro(itemFiltro.getTipoFiltro(), itemFiltro.getFiltro()));

		logger.info("Buscando item por filtro: " + itemFiltro.getFiltro());
		return "item/hq/listar";
	}
}
