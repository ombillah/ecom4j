package com.ombillah.ecom4j.exception;

/**
 * Custom exception for non-existing orders.
 * @author Oussama M Billah
 *
 */
public class OrderNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderNotFoundException(){
		super();
	}
	public OrderNotFoundException(String message){
		super(message);
	}

}
