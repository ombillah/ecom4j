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
