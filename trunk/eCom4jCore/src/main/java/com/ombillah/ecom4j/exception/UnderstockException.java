/*******************************************************************************
 *  Copyright 2010 Oussama M Billah
 *   
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *   
 *     http://www.apache.org/licenses/LICENSE-2.0
 *   
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 ******************************************************************************/
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
	private String productID;
	
	/**
	 * Default constructor.
	 */
	public UnderstockException() {
		super();
		productID = "unknown";
	}
	
	/**
	 * Constructor that sets the error message.
	 * 
	 * @param productID the error message
	 */
	public UnderstockException(String productID) {
		super("Insufficiant Quantity: " + productID);
		this.productID = productID;
	}
	
	/**
	 * Returns the error message.
	 * 
	 * @return the error message
	 */
	public String getError() {
		return "Insufficiant Quantity: " + productID;
	}
}
