package com.ombillah.ecom4j.exception;


/**
 * Exception thrown if there is a general problem connecting to the
 * database or executing a statement.
 * 
 * Optionally the message from any of the various exceptions thrown
 * by the DAO can be passed along through this exception by setting
 * the error String.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public class DatabaseException extends Exception {

	static final long serialVersionUID = 1;
	
	/** The error message */
	private String error;
	
	/**
	 * Default constructor.
	 */
	public DatabaseException() {
		super();
		error = "unknown";
	}
	
	/**
	 * Constructor that sets the error message.
	 * 
	 * @param error the error message
	 */
	public DatabaseException(String error) {
		super(error);
		this.error = error;
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
