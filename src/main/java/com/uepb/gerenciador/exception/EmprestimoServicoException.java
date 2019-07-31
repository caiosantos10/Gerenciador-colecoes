package com.uepb.gerenciador.exception;

public class EmprestimoServicoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public EmprestimoServicoException(String msg) {
		super(msg); 
	}
	
	public EmprestimoServicoException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
