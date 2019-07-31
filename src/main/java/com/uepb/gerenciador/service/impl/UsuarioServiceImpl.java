package com.uepb.gerenciador.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.exception.UsuarioInexistenteException;
import com.uepb.gerenciador.exception.UsuarioServicoException;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.repository.UsuarioRepository;
import com.uepb.gerenciador.service.IUsuarioService;

/**
 * <h1>Classe responsavel pelas operacoes de Usuario na camada de Servico</h1>
 * @author Caio e Jose George
 *
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	private static final Logger logger = 
		      Logger.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	
	/**
	 * Cria Usuario
	 * @param object usuario
	 * @return usuario criado
	 */
	@Override
	public Usuario create(Usuario object) throws UsuarioServicoException{
		
		try {
			validarUsuario(object);
			logger.info("Criando Usuario");
			object.setSenha(pe.encode(object.getSenha()));
			return usuarioRepo.save(object);
		}
		catch (UsuarioServicoException e) {
			logger.error("Erro ao persistir usuario "+UsuarioServiceImpl.class);
			throw new UsuarioServicoException("Erro ao inserir "+ e.getMessage());
		}	
	}
	
	/**
	 * Recupera usuario por id 
	 * @param id Id do usuario
	 * @return usuario 
	 */
	@Override
	public Usuario getById(Integer id) throws ObjectNotFoundException {
		
		Optional<Usuario> obj = usuarioRepo.findById(id);
		if(!obj.isPresent()) {
			logger.error("Usuario nao encontrado "+ UsuarioServiceImpl.class);
			throw new ObjectNotFoundException("Usuario nao encontrado, id: "+id);
		}
		logger.info("Recuperando Usuario por id "+id);
		return obj.get();
	}
	
	/**
	 * Edita usuario
	 * @param id Id Do usuario a ser modificado
	 * @param obj Objeto Usuario com dados novos
	 */
	@Override
	public Usuario update(Integer id, Usuario obj) {
		logger.info("Atualizando usuario");
		Usuario usuario = this.getById(id);
		
		usuario.setEmail(obj.getEmail());
		usuario.setId(obj.getId());
		usuario.setNome(obj.getNome());
		usuario.setSenha(obj.getSenha());
		
		Usuario usuarioUpdate = usuarioRepo.save(usuario);
		return usuarioUpdate;
	}
	
	/**
	 * Deleta usuario
	 * @param id Id do usuarios
	 */
	@Override
	public void delete(Integer id) throws UsuarioInexistenteException {
		
		try {
			logger.info("Deletando Usuario");
			usuarioRepo.deleteById(id);
		}catch (Exception e) {
			logger.error("Usuario nao encontrado "+ UsuarioServiceImpl.class);
			throw new UsuarioInexistenteException("Impossível deletar o obj que não existe " + e.getMessage());
		}
		
	}
	
	/**
	 * Lista todos os usuarios
	 * @return Lista de usuarios
	 */
	@Override
	public List<Usuario> getAll() {
		logger.info("Listando todos os usuarios");
		return usuarioRepo.findAll();
	}
	
	/**
	 * Busca usuario por email
	 * @param email
	 * @return usuario
	 */
	public Usuario findByEmail(String email){
		logger.info("Buscando usuario por email");
		return usuarioRepo.findByEmail(email);
	}
	
	/**
	 * Valida usuario
	 * @param usuario
	 * @throws UsuarioServicoException
	 */
	private void validarUsuario(Usuario usuario) throws UsuarioServicoException{
		logger.info("Validando usuario");	
		if(usuario == null) {
			logger.warn("O usuario não pode ser nulo");
			throw new UsuarioServicoException("O usuario não pode ser nulo");
		}
		
		if(usuario.getEmail() == null) {
			logger.warn("O e-mail não pode ser nulo");
			throw new UsuarioServicoException("O e-mail não pode ser nulo");
		}
		
		if(usuario.getNome() == null) {
			logger.warn("O nome não pode ser nulo");
			throw new UsuarioServicoException("O nome não pode ser nulo");
		}
		
		if(usuario.getSenha() == null) {
			logger.warn("O senha não pode ser nulo");
			throw new UsuarioServicoException("O senha não pode ser nulo");
		}
	}
	
	
	
}
