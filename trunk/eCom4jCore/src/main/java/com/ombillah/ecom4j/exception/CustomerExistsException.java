package com.ombillah.ecom4j.exception;


/**
 * Exception thrown by business logic when the users tries
 * to create a new Customer who's name is already in the database.
 * 
 * Optionally the message from any of the various exceptions thrown
 * by the DAO can be passed along through this exception by setting
 * the error String.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public class CustomerExistsException extends Exception {

	static final long serialVersionUID = 1;
	
	/** The name of the customer not found. */
	private String custName;
	
	/**
	 * Default constructor.
	 */
	public CustomerExistsException() {
		super();
		custName = "unknown";
	}
	
	/**
	 * Constructor that sets the error message.
	 * 
	 * @param custName the name of the duplicate customer
	 */
	public CustomerExistsException(String custName) {
		super("Customer already in system: " + custName);
		this.custName = custName;
	}
	
	/**
	 * Returns the error message.
	 * 
	 * @return the error message
	 */
	public String getError() {
		return "Customer already in system: " + this.custName;
	}
}
