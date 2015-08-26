package eatery.exceptions;

public class AppException extends Exception{


	private static final long serialVersionUID = 3023811645001912151L;

	public AppException(String message){
		super(message);
	}
	
	public AppException(String message, Throwable cause){
		super(message, cause);
	}
	
}
