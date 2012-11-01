package com.ombillah.ecom4j.exception;


/**
 * Exception thrown by business logic if login fails due to
 * invalid username or password.
 * 
 * @author Oussama M Billah
 * @version 3-19-09
 */
public class InvalidPasswordException extends Exception {

	static final long serialVersionUID = 1;
	
	/** The error message */
	private String error;
	
	/**
	 * Default constructor.
	 */
	public InvalidPasswordException() {
		super();
		error = "Invalid Password";
	}
	
	/**
	 * Returns the error message.
	 * 
	 * @return the error message
	 */
	public String getError() {
		return this.error;
	}
}
