package com.uepb.gerenciador.exception;

/**
 * Excecao para Validacao de Objetos
 * @author Caio e Jose George
 *
 */
public class ObjectValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ObjectValidationException(String msg) {
		super(msg);
	}
	
	public ObjectValidationException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
	
}
