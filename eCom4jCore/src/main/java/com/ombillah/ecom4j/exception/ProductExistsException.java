package com.ombillah.ecom4j.exception;


/**
 * Exception thrown by business logic when the admin tries
 * to create a new Product that is already in the database.
 * 
 * Optionally the message from any of the various exceptions thrown
 * by the DAO can be passed along through this exception by setting
 * the error String.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public class ProductExistsException extends Exception {

	static final long serialVersionUID = 1;
	
	
	/**
	 * Default constructor.
	 */
	public ProductExistsException() {
		super();
	}
	
	/**
	 * Constructor that sets the error message.
	 * 
	 * @param productId the name of the duplicate product
	 */
	public ProductExistsException(String productId) {
		super("Product already in system: " + productId);
	}
	

}
