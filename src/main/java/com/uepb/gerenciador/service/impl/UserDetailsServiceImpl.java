package com.uepb.gerenciador.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.config.security.UserSS;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository repo;

	/**
	 * Recupera o usuario logado, verifica se ele existe e caso exista preeenche o Objeto UserSS
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario user = repo.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException(email); 
		}
		
		return new UserSS(user.getId(), user.getEmail(), user.getSenha(), user.getPerfis());

	}

	
}
