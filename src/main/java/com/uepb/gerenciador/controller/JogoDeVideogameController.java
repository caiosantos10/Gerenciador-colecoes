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
import com.uepb.gerenciador.model.JogoDeVideogame;
import com.uepb.gerenciador.service.impl.JogoDeVideogameServiceImpl;
/**
 * <h1>Controle para operacoes com Jogo de VideoGame</h1>
 * @author Caio e Jose George
 */
@Controller
@RequestMapping("/jogo_videogame")
public class JogoDeVideogameController implements IItemController<JogoDeVideogame> {
	
	private static final Logger logger = 
		      Logger.getLogger(JogoDeVideogameController.class);
	
	@Autowired
	JogoDeVideogameServiceImpl itemService;

	/**
	 * 
	 * Carrega form para cadastro de Jogo de VideoGame
	 * @return view de cadastro
	 */
	@RequestMapping(path = "/adicionar", method = RequestMethod.GET)
	public String loadForm() {
		logger.info("Carregando formulario de Jogo de VideoGame");
		return "item/jogovideogame/cadastro";
	}


	/**
	 * Salva Jogo de VideoGame
	 * @param item Jogo de VideoGame
	 * @param erros
	 * @return view para home
	 */
	@PostMapping("/adicionar")
	@Override
	public String saveItem(JogoDeVideogame item, BindingResult errors) {
		
		if(errors.hasErrors()) {
			logger.error("Erro ao salvar Jogo de VideoGame "+JogoDeVideogameController.class);
			return "item/jogovideogame/cadastro";
		}
		
		itemService.create(item); 
		logger.info("Salvando registro de jogo videogame: " + item.getTitulo());
		return "redirect:/home";
	}

	
	/**
	 * Deleta um jogo de videogame
	 * @param id identificador do Jogo de VideoGame
	 * @return view jogos para Jogo de VideoGame
	 */
	@RequestMapping(path = "/deletar/{id}", method = RequestMethod.GET)
	@Override
	public String deleteItem(@PathVariable(name = "id") String id) {
		try {
			logger.info("Deletando Jogo de VideoGame");
			itemService.delete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			logger.info("Erro ao deletar Jogo de VideoGame "+ JogoDeVideogameController.class);
			e.printStackTrace();
		} catch (ItemInexistenteException e) {
			logger.info("Jogo de VideoGame inexistente"+ JogoDeVideogameController.class);
			e.printStackTrace();
		}
		return "redirect:/jogo_videogame/jogos";
	}

	/**
	 * Lista todos os jogos
	 * @param model
	 * @return view para lista de Jogo de VideoGame
	 */
	@RequestMapping(path = "jogos", method = RequestMethod.GET)
	@Override
	public String getAllItens(Model model) {
		logger.info("Listando Jogos de VideoGame");
		model.addAttribute("jogos", itemService.getAll());
		return "item/jogovideogame/listar";
	}

	/**
	 * Carrega form para editar Jogo de VideoGame
	 * @param model
	 * @param id identificador do Jogo de VideoGame
	 * @return view de formulario para edicao
	 */
	@RequestMapping(path = "/editar/{id}", method = RequestMethod.GET)
    public String loadFormEdit(Model model, @PathVariable(value = "id") String id) {
		logger.info("Carregando form para editar Jogo de VideoGame");
		model.addAttribute("JogoDeVideogame", itemService.getById(Integer.parseInt(id)));
		return "item/jogovideogame/editar";
    }
	
	/**
	 * Edita Jogo de VideoGame
	 * @param item Jogo de VideoGame
	 * @param erros
	 * @return view para jogos de Jogo de VideoGame
	 */
	@PostMapping("/editar")
	@Override
	public String updateItem(JogoDeVideogame item, BindingResult errors) {

		if (errors.hasErrors()) {
			logger.error("Erro ao atualizar o registro de JogoDeVideogame: " + JogoDeVideogameController.class);
			return "item/jogovideogame/cadastro";
		}
		
		itemService.update(item.getId(), item);
		logger.info("Atualizando registro de JogoDeVideogame: " + item.getTitulo());
		
		return "redirect:/jogo_videogame/jogos";
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
			logger.error("Erro ao buscar o registro JogoVideoGame pelo registro: " + JogoDeVideogameController.class);
			return "redirect:/jogo_videogame/jogos";
		}

		model.addAttribute("jogos", itemService.buscaPorFiltro(itemFiltro.getTipoFiltro(), itemFiltro.getFiltro()));

		logger.info("Buscando item por filtro: " + itemFiltro.getFiltro());
		return "item/jogovideogame/listar";
	}
	
}
