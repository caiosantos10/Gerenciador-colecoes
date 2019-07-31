package com.uepb.gerenciador.exception;

/**
 * Excecao para operacoes de Usuario na camada de Servico
 * @author Caio e George
 *
 */
public class UsuarioServicoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UsuarioServicoException(String msg) {
		super(msg); 
	}
	
	public UsuarioServicoException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
