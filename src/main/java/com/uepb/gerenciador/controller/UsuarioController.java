package com.uepb.gerenciador.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.uepb.gerenciador.exception.UsuarioServicoException;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.service.impl.UsuarioServiceImpl;
/**
 * <h1>Controller para tratar Usuario</h1>
 * @author Caio e Jose George
 *
 */
@Controller
public class UsuarioController {
	
	private static final Logger logger = 
		      Logger.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	/**
	 * Exibe formulario para criar usuario
	 * @param user Usuario
	 * @return
	 */
	@GetMapping("usuario/cadastro")
	public String usuarioViewExibicao(Usuario user) {
		logger.info("Criando Usuario");
		return "usuario/cadastrar-usuario";
	}

	@PostMapping("/adduser")
	public String addUser(@Valid Usuario usuario, BindingResult result, Model model) throws UsuarioServicoException {
		if (result.hasErrors()) {
			logger.info("Erro ao cadastrar Usuario "+ UsuarioController.class);
			return "usuario/cadastrar-usuario";
		}
		
		logger.info("Salvando Usuario");
		usuarioService.create(usuario);
		return "usuario/msg-ok";
	}
	
}
