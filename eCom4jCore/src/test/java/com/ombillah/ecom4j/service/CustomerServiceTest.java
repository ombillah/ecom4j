package com.ombillah.ecom4j.service;


import static org.junit.Assert.assertEquals;

import static org.easymock.EasyMock.*;

import org.easymock.EasyMock;
import org.hibernate.NonUniqueObjectException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;


import com.ombillah.ecom4j.dao.CustomerDAO;
import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.exception.CustomerNotFoundException;
import com.ombillah.ecom4j.service.impl.CustomerServiceImpl;
/**
 * A class that test the all the Customer BO customerService.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public class CustomerServiceTest {
	
	private CustomerService customerService;
	private CustomerDAO customerDaoMock;
	private PasswordEncoder passwordEncoder;
	
	@Before
	public void setup() {
		customerService = new CustomerServiceImpl();
		customerDaoMock = createMock(CustomerDAO.class);
		passwordEncoder = new Md5PasswordEncoder();
		customerService.setCustomerDao(customerDaoMock);
		customerService.setPasswordEncoder(passwordEncoder);
		
	}
	

	/**
	 * Test the login() functionality.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidLogin() throws Exception{

        Customer customer = new Customer();
        customer.setEmailAddress("omb1986@hotmail.com");
        customer.setPassword("098f6bcd4621d373cade4e832627b4f6");
		
        expect(customerDaoMock.getObject(Customer.class, "omb1986@hotmail.com")).andReturn(customer);
		replay(customerDaoMock);
		assertEquals(customer, customerService.login("omb1986@hotmail.com", "test"));
		verify(customerDaoMock);


	}
	
	/**
	 * Test the login() functionality.
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomerNotFoundException.class)
	public void testInvalidUserName() throws Exception{
		
		expect(customerDaoMock.getObject(Customer.class, "omb1986@hotmail.com")).andReturn(null);

		replay(customerDaoMock);
		try {
			customerService.login("omb1986@hotmail.com", "Franklin");
		} finally {
			verify(customerDaoMock);
		}


	}
	
	
	/**
	 * Test the login() functionality.
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomerNotFoundException.class)
	public void testInvalidPassword() throws Exception{
		
		Customer customer = new Customer();
		customer.setPassword("valid");
		
		expect(customerDaoMock.getObject(Customer.class, "omb1986@hotmail.com")).andReturn(customer);
		replay(customerDaoMock);
		try {
			customerService.login("omb1986@hotmail.com", "Franklin");
		} finally {
			verify(customerDaoMock);
		}


	}

	/**
	 * Test register() method functionality.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSuccesfullRegistration() throws Exception{
		
		Customer customer = new Customer();
		
		customer.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
		customer.setSecretAnswer("a363b8d13575101a0226e8d0d054f2e7");
		
		customerDaoMock.saveObject(customer);
		EasyMock.expectLastCall();
		replay(customerDaoMock);
		
		Customer customer2 = new Customer();
		customer2.setPassword("password");
		customer2.setSecretAnswer("answer");
		
		customerService.register(customer2);
		verify(customerDaoMock);
	}
	
	/**
	 * Test update() method functionality.
	 * 
	 * @throws Exception
	 */
	@Test (expected = NonUniqueObjectException.class)
	public void testFailedRegistration() throws Exception{
		
		Customer customer = new Customer();
		customer.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
		customer.setSecretAnswer("a363b8d13575101a0226e8d0d054f2e7");
		
		customerDaoMock.saveObject(customer);
		expectLastCall().andThrow(new NonUniqueObjectException(customer, null));
		
		replay(customerDaoMock);
		
		try {
			Customer customer2 = new Customer();
			customer2.setPassword("password");
			customer2.setSecretAnswer("answer");
			customerService.register(customer2);
		} finally {
			verify(customerDaoMock);
		}
	}
	
	/**
	 * Test the updateCustomer() method functionalities.
	 * @throws Exception
	 */
	@Test
	public void testUpdatePassword() throws Exception{
		Customer customer = new Customer();
		
		customer.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
		
		customerDaoMock.updateObject(customer);
		EasyMock.expectLastCall();
		replay(customerDaoMock);
		
		Customer customer2 = new Customer();
		customer2.setPassword("password");
		
		customerService.updatePassword(customer2);
		verify(customerDaoMock);
	}
	
	/**
	 * Test the updateCustomer() method functionalities.
	 * @throws Exception
	 */
	@Test
	public void testUpdateSecretAnswer() throws Exception{
		Customer customer = new Customer();
		
		customer.setSecretAnswer("a363b8d13575101a0226e8d0d054f2e7");
		
		customerDaoMock.updateObject(customer);
		EasyMock.expectLastCall();
		replay(customerDaoMock);
		
		Customer customer2 = new Customer();
		customer2.setSecretAnswer("answer");
		
		customerService.updateSecretAnswer(customer2);
		verify(customerDaoMock);
	}

}
