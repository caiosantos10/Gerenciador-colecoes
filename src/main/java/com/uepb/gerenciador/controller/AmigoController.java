package com.uepb.gerenciador.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.model.Amigo;
import com.uepb.gerenciador.model.Endereco;
import com.uepb.gerenciador.service.impl.AmigoServiceImpl;
import com.uepb.gerenciador.service.impl.CidadeServiceImpl;
import com.uepb.gerenciador.service.impl.EnderecoServiceImpl;
import com.uepb.gerenciador.service.impl.EstadoServiceImpl;

@Controller
@RequestMapping("/amigo")
public class AmigoController {
	private static final Logger logger = Logger.getLogger(AmigoController.class);

	@Autowired
	private AmigoServiceImpl amigoService;
	
	@Autowired
	private CidadeServiceImpl cidade; 
	
	@Autowired 
	private EstadoServiceImpl estado; 
	
	@Autowired
	private EnderecoServiceImpl enderecoService;

	
	/**
	 * Carrega o form de cadastro de amigo
	 * @param model
	 * @return view de cadastro de amigo
	 */
	@GetMapping("cadastro")
	public String loadForm(Model model) {
		logger.info("Carregando form para cadastro de amigo");
		model.addAttribute("cidades", cidade.getAll());
		model.addAttribute("estados", estado.findAll());
		return "amigo/cadastro";
	}

	/**
	 * Realiza o cadastro de um amigo no sistema
	 * @param amigo
	 * @return a view de home
	 */
	@PostMapping("/adicionar")
	public String saveAmigo(Amigo amigo) {
		logger.info("Adicionando amigo...");
		amigoService.create(amigo);
		return "redirect:/amigo/cadastro/endereco";
	}
	
	/**
	 * Carrega formulario para preencher endereco
	 * @param model
	 * @return view para cadastro de endereco
	 */
	@GetMapping("/cadastro/endereco")
	public String loadFormEndereco(Model model) {
		logger.info("Carregando formulario de endereço do amigo");
		model.addAttribute("amigos", amigoService.getAll());
		model.addAttribute("cidades", cidade.getAll());
		model.addAttribute("estados", estado.findAll());
		return "amigo/cadastro-endereco";
	}
	
	/**
	 * Salva endereco 
	 * @param endereco
	 * @return view para a home do sistema
	 */
	@PostMapping("/cadastro/endereco")
	public String saveEndereco(Endereco endereco) {
		logger.info("Adicionando endereco");
		enderecoService.create(endereco);
		return "redirect:/home";
	}
	
	/**
	 * Exibe lista de todos os amigos
	 * @param model
	 * @return view para lista de amigos
	 */
	@RequestMapping(path = "amigos", method = RequestMethod.GET)
	public String getAllAmigos(Model model) {
		logger.info("Retornando lista de todos os amigos");
		model.addAttribute("amigos", amigoService.getAll());
		return "amigo/listar";
	}
	
	/**
	 * Deleta amigo
	 * @param id do amigo
	 * @return view para amigos
	 */
	@RequestMapping(path = "/deletar/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable(name = "id") String id) {
		logger.info("deletando amigo... "+ id );
		try {
			amigoService.delete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			logger.warn("Erro ao deletar amigo "+ AmigoController.class);
			e.printStackTrace();
		} catch (ItemInexistenteException e) {
			logger.warn("Nenhum amigo com o id informado "+AmigoController.class);
			e.printStackTrace();
		}
		return "redirect:/amigo/amigos";
	}
	
}
