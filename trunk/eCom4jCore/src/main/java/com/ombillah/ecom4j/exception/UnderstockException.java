package com.ombillah.ecom4j.exception;


/**
 * Exception thrown by business logic when customer attempts to order
 * more of an item than what is in stock.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public class UnderstockException extends Exception {

	static final long serialVersionUID = 1;
	
	/** The error message */
	private String productId;
	
	/**
	 * Default constructor.
	 */
	public UnderstockException() {
		super();
		productId = "unknown";
	}
	
	/**
	 * Constructor that sets the error message.
	 * 
	 * @param productId the error message
	 */
	public UnderstockException(String productId) {
		super("Insufficiant Quantity: " + productId);
		this.productId = productId;
	}
	
	/**
	 * Returns the error message.
	 * 
	 * @return the error message
	 */
	public String getError() {
		return "Insufficiant Quantity: " + productId;
	}
}
