package com.eiva.propuesta01.services.exceptions;

public class EivaException extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	public EivaException() {
		super();
	}
	
	public EivaException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
