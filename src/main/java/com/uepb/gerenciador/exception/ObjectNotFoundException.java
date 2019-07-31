package com.uepb.gerenciador.exception;

/**
 * Excecao para Objeto nao encontrado
 * @author Caio e Jose George
 *
 */
public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
