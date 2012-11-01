package com.ombillah.ecom4j.exception;


/**
 * Exception thrown by business logic when the users requests
 * information on a product with no record in the database.
 * 
 * Optionally the message from any of the various exceptions thrown
 * by the DAO can be passed along through this exception by setting
 * the error String.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public class ProductNotFoundException extends Exception {

	static final long serialVersionUID = 1;
	
	/** The name of the customer not found. */
	private String prodName;
	
	/**
	 * Default constructor.
	 */
	public ProductNotFoundException() {
		super();
		prodName = "unknown";
	}
	
	/**
	 * Constructor that sets the error message.
	 * 
	 * @param prodName the product not found
	 */
	public ProductNotFoundException(String prodName) {
		super("Product not in database: " + prodName);
		this.prodName = prodName;
	}
	
	/**
	 * Returns the error message.
	 * 
	 * @return the error message
	 */
	public String getError() {
		return "Product not in database: " + this.prodName;
	}
}
