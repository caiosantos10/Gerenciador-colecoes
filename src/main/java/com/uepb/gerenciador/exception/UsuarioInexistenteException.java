package com.uepb.gerenciador.exception;

/**
 * Excecao para Usuario Inexistente
 * @author Caio e Jose George
 *
 */
public class UsuarioInexistenteException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UsuarioInexistenteException(String msg) {
		super(msg);
	}
	
	public UsuarioInexistenteException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
