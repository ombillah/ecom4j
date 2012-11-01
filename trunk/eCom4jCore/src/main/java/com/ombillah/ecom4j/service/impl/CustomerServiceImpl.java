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
package com.ombillah.ecom4j.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ombillah.ecom4j.dao.CustomerDAO;
import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.exception.CustomerNotFoundException;
import com.ombillah.ecom4j.service.CustomerService;


/**
 * Customer service Class that provides all the services related to a customer.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
@Service
@Transactional (readOnly = true)
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/**
	 * Retrieves a customer from the database.
	 * 
	 * @param userName The given email of the customer to be retrieved
	 * @return a customer representation from the database based on the given id
	 */
	@Transactional
	public Customer getCustomer(String email) {
		Customer customer = customerDao.getObject(Customer.class, email);
		return customer;

	}
	/**
	 * Log an existing customer into the web site.
	 * 
	 * @param userName The given customer's user name
	 * @param password the given customer's password
	 * @return The customer object that matches the given information
	 * @throws CustomerNotFoundException 
	 */
	@Transactional
	public Customer login(String userName, String password) throws CustomerNotFoundException{
		String hashedPassword = passwordEncoder.encodePassword(password, null);
		Customer customer = this.getCustomer(userName);
		if (customer != null && customer.getPassword().equals(hashedPassword)){
			return customer;
		} else{
			throw new CustomerNotFoundException("Invalid user name and/or password");
		}
	}

	
	/**
	 * Register the customer into the web site.
	 * 
	 * @param customer The Customer Object to be registered
	 * @throws CustomerExistsException 
	 */
	@Transactional(readOnly = false)
	public void register(Customer customer) {
		String hashedPassword = passwordEncoder.encodePassword(customer.getPassword(), null);
		String hashedSecretAnswer = passwordEncoder.encodePassword(customer.getSecretAnswer(), null);
		customer.setPassword(hashedPassword);
		customer.setSecretAnswer(hashedSecretAnswer);
		customerDao.saveObject(customer);
	}

	/**
	 * Update a customer's information in the database.
	 * 
	 * @param customer The Customer object that stores the new information
	 * @throws Exception 
	 */
	@Transactional (readOnly = false)
	public void updateCustomer(Customer customer){
		String oldEmailAddress = customer.getOldEmailAddress();
		String emailAddress = customer.getEmailAddress();
		
		boolean emailAddressUpdated = oldEmailAddress != null && !StringUtils.equals(emailAddress, oldEmailAddress);
		if(emailAddressUpdated) {
			customerDao.removeObject(Customer.class, oldEmailAddress);
			customerDao.saveObject(customer);
		}else {
			customerDao.updateObject(customer);
		}
	}

	/**
	 * Update a customer's password in the database.
	 * 
	 * @param customer The Customer object that stores the new information
	 * @throws Exception 
	 */
	@Transactional (readOnly = false)
	public void updatePassword(Customer customer) {
		String hashedPassword = passwordEncoder.encodePassword(customer.getPassword(), null);
		customer.setPassword(hashedPassword);
		customerDao.updateObject(customer);
	}
	
	/**
	 * Update a customer's secret question and answer in the database.
	 * 
	 * @param customer The Customer object that stores the new information
	 * @throws Exception 
	 */
	@Transactional (readOnly = false)
	public void updateSecretAnswer(Customer customer) {
		String hashedSecretAnswer = passwordEncoder.encodePassword(customer.getSecretAnswer(), null);
		customer.setSecretAnswer(hashedSecretAnswer);
		customerDao.updateObject(customer);
	}

	/**
	 * adding setter for Mocking purpose.
	 * @param customerDao
	 */
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
	
	/**
	 * adding setter for Mocking purpose.
	 * @param passwordEncoder
	 */
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


}
