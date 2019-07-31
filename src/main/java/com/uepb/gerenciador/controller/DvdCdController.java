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
import com.uepb.gerenciador.model.DvdCd;
import com.uepb.gerenciador.model.ItemFiltro;
import com.uepb.gerenciador.service.impl.DvdCdServiceImpl;
/**
 * Controlador para entidade DvdCd
 * @author Caio
 *
 */
@Controller
@RequestMapping("/dvd_cd")
public class DvdCdController implements IItemController<DvdCd> {

	private static final Logger logger = Logger.getLogger(DvdCdController.class);

	@Autowired
	private DvdCdServiceImpl itemService;
	
	
	/**
	 * Carrega formulario para cadastro de DvdCd.
	 * @return view de cadastro
	 */
	@RequestMapping(path = "/adicionar", method = RequestMethod.GET)
	public String loadForm() {
		logger.info("exibindo formulario para DvdCd");
		return "item/dvd/cadastro";
	}

	
	/**
	 * Salva DvdCd
	 * @param item dvd/cd a ser criado
	 * @param erros
	 * @return view para home do sistema
	 */
	@PostMapping("/adicionar")
	@Override
	public String saveItem(DvdCd item, BindingResult errors) {
		logger.info("Criando DvdCd");
		/**
		 * verifica se existe algum campo não preenchido corretamente
		 */
		if (errors.hasErrors()) {
			logger.error("Dados de DVD/CD nao preenchidos corretamente: " + item.getTitulo());
			return "item/dvd/cadastro";
		}

		itemService.create(item);
		logger.info("Criando registro de DVD/CD: " + item.getTitulo());
		return "redirect:/home";

	}

	/**
	 * Deleta um DvdCd
	 * @param id identificador do DvdCd
	 * @return view para dvds 
	 */
	@RequestMapping(path = "/deletar/{id}", method = RequestMethod.GET)
	@Override
	public String deleteItem(@PathVariable(name = "id") String id) {
		logger.info("deletando DvdCd");
		try {
			itemService.delete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			logger.warn("Erro ao deletar DvdCd "+ DvdCdController.class);
			e.printStackTrace();
		} catch (ItemInexistenteException e) {
			logger.warn("DvdCd nao existe "+ DvdCdController.class);
			e.printStackTrace();
		}
		return "redirect:/dvd_cd/dvds";
	}

	

	/**
	 * Lista todos os DvdCd
	 * @param model
	 * @return view para listagem de DvdCd
	 */
	@RequestMapping(path = "dvds", method = RequestMethod.GET)
	@Override
	public String getAllItens(Model model) {
		logger.info("Listando todos os DvdCd");
		model.addAttribute("dvds", itemService.getAll());
		return "item/dvd/listar";
	}
	
	/**
	 * Carrega formulario de edicao do DvdCd
	 * @param model
	 * @param id
	 * @return view para editar DvdCd
	 */
	@RequestMapping(path = "/editar/{id}", method = RequestMethod.GET)
    public String loadFormEdit(Model model, @PathVariable(value = "id") String id) {
		logger.info("Carregando formulario de edicao de DvdCd");
		model.addAttribute("DvdCd", itemService.getById(Integer.parseInt(id)));
		return "item/dvd/editar";
    }

	/**
	 * Atualiza DvdCd
	 * @param item DvdCd
	 * @param erros
	 * @return view para dvds
	 */
	@PostMapping("/editar")
	@Override
	public String updateItem(DvdCd item, BindingResult errors) {
		
		if (errors.hasErrors()) {
			logger.error("Erro ao atualizar o registro de DVD/CD: " + item.getTitulo());
			return "item/dvd/cadastro";
		}
		
		itemService.update(item.getId(), item);
		logger.info("Atualizando registro de DVD/CD: " + item.getTitulo());
		return "redirect:/dvd_cd/dvds";
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
			logger.error("Erro ao buscar o registro Item pelo registro: " + DvdCdController.class);
			return "redirect:/dvd_cd/dvds";
		}

		model.addAttribute("dvds", itemService.buscaPorFiltro(itemFiltro.getTipoFiltro(), itemFiltro.getFiltro()));

		logger.info("Buscando item por filtro: " + itemFiltro.getFiltro());
		return "item/dvd/listar";
	}
	
	
	
}
