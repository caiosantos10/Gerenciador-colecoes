package com.uepb.gerenciador.exception;

/**
 * Lança uma exceção caso o item não exista
 * @author Jose George e Caio
 *
 */

public class ItemInexistenteException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ItemInexistenteException(String msg) {
		super(msg);
	}
	
	public ItemInexistenteException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
