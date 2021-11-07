package com.rui.api.error.exception;

public class CpfInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfInvalidoException(String mensagem) {
		super(mensagem);
	}
	
	public CpfInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}