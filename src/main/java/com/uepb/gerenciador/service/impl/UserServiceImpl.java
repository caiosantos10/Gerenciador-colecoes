package com.uepb.gerenciador.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;

import com.uepb.gerenciador.config.security.UserSS;

public class UserServiceImpl {

	/**
	 * Responsável por informa dados do usuario que está logado.
	 * @return o usuario logado
	 */
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

}
