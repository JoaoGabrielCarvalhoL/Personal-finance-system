package br.com.carv.finances.exceptions;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AuthenticationException(String messageError) {
		super(messageError);
	}
	

}
