package com.ping.exception;

public class InvalidHostException extends Exception {

	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4108368937650906620L;
	
	public InvalidHostException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
