package com.uepb.gerenciador.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.UsuarioInexistenteException;
import com.uepb.gerenciador.exception.UsuarioServicoException;
import com.uepb.gerenciador.model.Usuario;
/**
 * Interface para tratar operacoes de Usuario na camada de servico
 * @author Caio e Jose George
 *
 */
@Service
public interface IUsuarioService {
	
	Usuario create(Usuario user) throws UsuarioServicoException; 
	
	Usuario getById(Integer id);
	
	Usuario update(Integer id, Usuario object);
	
	void delete(Integer id) throws UsuarioInexistenteException;
	
	List<Usuario> getAll();

}
