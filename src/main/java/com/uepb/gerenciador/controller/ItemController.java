
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
import com.uepb.gerenciador.model.Item;
import com.uepb.gerenciador.model.ItemFiltro;
import com.uepb.gerenciador.service.impl.ItemServiceImpl;

/**
 * Controlador para operacoes com Itens genericos
 * 
 * @author Caio e Jose George
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController implements IItemController<Item> {
	private static final Logger logger = Logger.getLogger(ItemController.class);

	@Autowired
	ItemServiceImpl itemService;

	/**
	 * 
	 * Carrega formulario para cadastro.
	 * 
	 * @return view de cadastro
	 */
	@RequestMapping(path = "/adicionar", method = RequestMethod.GET)
	public String addItem() {
		logger.info("Criando item");
		return "item/outro/cadastro";
	}

	/**
	 * Salva item
	 * 
	 * @param item  objeto a ser salvo
	 * @param erros
	 * @return view para home do sistema
	 */
	@PostMapping("/adicionar")
	@Override
	public String saveItem(@ModelAttribute @Valid Item item, BindingResult errors) {
		/**
		 * verifica se existe algum campo não preenchido corretamente
		 */
		if (errors.hasErrors()) {
			logger.error("Erro ao salvar o registro Item: " + ItemController.class);
			return "item/outro/cadastro";
		}

		itemService.create(item);
		logger.info("Salvando registro de item: " + item.getTitulo());
		return "redirect:/home";
	}

	/**
	 * Deleta um item
	 * 
	 * @param id identificador do item
	 * @return lista de itens
	 */
	@RequestMapping(path = "/deletar/{id}", method = RequestMethod.GET)
	@Override
	public String deleteItem(@PathVariable(name = "id") String id) {
		try {
			logger.info("deletando item");
			itemService.delete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			logger.error("Erro ao deletar item " + ItemController.class);
			e.printStackTrace();
		} catch (ItemInexistenteException e) {
			logger.error("Item nao existe " + ItemController.class);
			e.printStackTrace();
		}
		return "redirect:/item/todos";
	}

	/**
	 * Lista todos os itens
	 * 
	 * @param model
	 * @return lista de itens
	 */
	@RequestMapping(path = "todos", method = RequestMethod.GET)
	@Override
	public String getAllItens(Model model) {
		logger.info("Listando todos os itens ");
		model.addAttribute("itens", itemService.getAll());
		return "item/outro/listar";
	}

	/**
	 * Lista todos os itens pela quantidade de Emprestimos
	 * 
	 * @param model
	 * @return Ranking
	 */
	@RequestMapping(path = "ranking", method = RequestMethod.GET)
	public String getRanking(Model model) {
		logger.info("Listando Ranking ");
		model.addAttribute("itens", itemService.getRanking());
		return "item/outro/ranking";
	}

	/**
	 * Lista todos os itens Repetidos
	 * 
	 * @param model
	 * @return itens repetidos
	 */
	@RequestMapping(path = "repetidos", method = RequestMethod.GET)
	public String getItensRepetidos(Model model) {
		logger.info("Listando Itens Repetidos ");
		model.addAttribute("itens", itemService.getItensRepetidos());
		return "item/outro/itensRepetidos";
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
			logger.error("Erro ao buscar o registro Item pelo registro: " + ItemController.class);
			return "redirect:/item/todos";
		}

		model.addAttribute("itens", itemService.buscaPorFiltro(itemFiltro.getTipoFiltro(), itemFiltro.getFiltro()));

		logger.info("Buscando item por filtro: " + itemFiltro.getFiltro());
		return "item/outro/listar";
	}

	@Override
	public String updateItem(Item item, BindingResult errors) {
		// TODO Auto-generated method stub
		return null;
	}

}