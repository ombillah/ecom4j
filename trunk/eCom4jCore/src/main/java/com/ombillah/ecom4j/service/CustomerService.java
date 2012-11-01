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
package com.ombillah.ecom4j.service;

import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.ombillah.ecom4j.dao.CustomerDAO;
import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.exception.CustomerNotFoundException;



/**
 * Customer Service Interface that provides all the services related to a customer.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public interface CustomerService {
	
	/**
	 * Log an existing customer into the web site.
	 * 
	 * @param userName The given customer's user name
	 * @param password the given customer's password
	 * @return The customer object that matches the given information
	 * @throws Exception
	 */
	public Customer login(String userName, String password) throws CustomerNotFoundException;
	
	
	/**
	 * Register the customer into the web site.
	 * 
	 * @param customer The Customer Object to be registered
	 * @throws Exception
	 */
	public void register(Customer customer);
	
	/**
	 * Retrieves a customer from the database.
	 * 
	 * @param userName The given user name of the customer to be retrieved
	 * @return a customer representation from the database based on the given id
	 */
	public Customer getCustomer(String userName);
	
	/**
	 * Update a customer's information in the database.
	 * 
	 * @param customer The Customer object that stores the new information
	 * @throws Exception 
	 */
	public void updateCustomer(Customer customer);
	
	/**
	 * Update a customer's password in the database.
	 * 
	 * @param customer The Customer object that stores the new information
	 * @throws Exception 
	 */
	public void updatePassword(Customer customer);
	
	/**
	 * Update a customer's secret question and answer in the database.
	 * 
	 * @param customer The Customer object that stores the new information
	 * @throws Exception 
	 */
	public void updateSecretAnswer(Customer customer);

	
	/**
	 * adding setter for Mocking purpose.
	 * @param customerDao
	 */
	public void setCustomerDao(CustomerDAO customerDao);
	
	/**
	 * adding setter for Mocking purpose.
	 * @param passwordEncoder
	 */
	public void setPasswordEncoder(PasswordEncoder passwordEncoder);
	
}
