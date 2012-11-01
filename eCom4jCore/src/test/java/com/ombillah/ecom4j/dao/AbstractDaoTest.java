package com.ombillah.ecom4j.dao;

import static org.junit.Assert.*;

import java.util.*;
import java.sql.Date;

import org.hibernate.NonUniqueObjectException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.domain.CustomerOrder;
import com.ombillah.ecom4j.domain.OrderItem;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ProductSpecificationMap;
/**
 * Unit test for DAO class.
 * 
 * @author Oussama El Mouatassim Billah
 * @version 1.0
 */

@ContextConfiguration(locations={"applicationContext-dao.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests  {
	
	/** hold reference to the DAO object. */
	@Autowired
    private ProductDAO productDao;
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private OrderItemDAO orderItemDao;

	
	/**
	 * Test to ensure that getOrderByUserName method works properly.
	 */
	@Test
	public void testGetOrderByUserName() 
	{
		List<CustomerOrder> result = orderDao.getOrderByUserName("omb1986@hotmail.com");
		assertEquals(1, result.size());
		CustomerOrder order = result.get(0);
		assertEquals(431, order.getOrderID());
		assertEquals("omb1986@hotmail.com", order.getCustomer().getEmailAddress());
		assertEquals("IN PROCESS", order.getStatus());
		assertEquals(479.00, order.getTotalCost(), 0.01);
		
	}
	
	/**
	 * Test to ensure that getOrderByID method works properly.
	 */
	@Test
	public void testGetObjectByID() throws NoSuchElementException 
	{
		CustomerOrder order = orderDao.getObject(CustomerOrder.class, 431L);
		assertEquals(431, order.getOrderID());
		assertEquals(431, order.getOrderID());
		assertEquals("omb1986@hotmail.com", order.getCustomer().getEmailAddress());
		assertEquals("IN PROCESS", order.getStatus());
		assertEquals(479.00, order.getTotalCost(), 0.01);
		assertNull(orderDao.getObject(CustomerOrder.class, 555555L));
	}

	/**
	 * Test to ensure that getProducts method works properly.
	 */
	@Test
	public void testGetObjects() 
	{
		List<Product> result = productDao.getObjects(Product.class);
		assertEquals(2, result.size());
		assertEquals(new Long(1111), result.get(0).getProductID());
		assertEquals(new Long(2222), result.get(1).getProductID());
	}


	/**
	 * Test to ensure that createDBOrder method works properly.
	 */
	@Test (expected = NonUniqueObjectException.class)
	@Rollback(true)
	public void testCreateDBOrder() 
	{
		int ordersCount = countRowsInTable("CUSTOMERORDER");
		int orderItemsCount = countRowsInTable("ORDERITEM");
		// Check for row count before insertion.
		assertEquals(1, ordersCount);
		assertEquals(1, orderItemsCount);
		
		CustomerOrder order = new CustomerOrder(3, customerDao
				.getObject(Customer.class, "omb1986@hotmail.com"), "PENDING", 200, new Date(new Long(
				"61191781200000")));
		OrderItem item = new OrderItem(333, productDao.getObject(Product.class, 1111L), order, 3);
		OrderItem item2 = new OrderItem(444, productDao.getObject(Product.class, 1111L), order, 2);
		List<OrderItem> items = new ArrayList<OrderItem>();
		items.add(item);
		items.add(item2);
		orderDao.createDBOrder(order, items);
		ordersCount = countRowsInTable("CUSTOMERORDER");
		orderItemsCount = countRowsInTable("ORDERITEM");
		// Check for row count after insertion.
		assertEquals(2, ordersCount);
		assertEquals(3, orderItemsCount);
		assertEquals(3, orderDao.getObject(CustomerOrder.class, 3L).getOrderID());
		assertEquals(2, orderItemDao.getOrderItemByOrderID(3L).size());
		
		CustomerOrder order2 = new CustomerOrder(3, customerDao
				.getObject(Customer.class, "omb1986@hotmail.com"), "PENDING", 200, new Date(new Long(
				"61191781200000")));

		// Check for creating a order with the same ID.
		orderDao.createDBOrder(order2, items);
		
	}

	/**
	 * Test to ensure that createDBCustomer method works properly.
	 */
	@Test (expected = NonUniqueObjectException.class)
	@Rollback(true)
	public void testCreateObject() 
	{
		Customer customer = new Customer();

		customer.setEmailAddress("test@test.com");
		customer.setPassword("password");
		customer.setSecretQuestion("question");
		customer.setSecretAnswer("answer");
		customer.setFirstName("Oussama");
		customer.setLastName("M Billah");
		customer.setAddress("4433 Test Rd");
		customer.setAddress2("Apt B");
		customer.setCity("Columbus");
		customer.setState("OH");
		customer.setZipCode("43224");
		customer.setPhoneNumber("614-333-0333");
		
		customerDao.saveObject(customer);
		assertEquals("test@test.com", customerDao.getObject(Customer.class, "test@test.com").getEmailAddress());
		
		// Test Inserting the same ID
		Customer customer2 = new Customer();
		customer2.setEmailAddress("test@test.com");
		customer2.setPassword("password");
		customer2.setFirstName("Oussama");
		customer2.setLastName("M Billah");
		customer2.setAddress("4433 Test Rd");
		customer2.setAddress2("Apt B");
		customer2.setCity("Columbus");
		customer2.setState("OH");
		customer2.setZipCode("43224");
		customer2.setPhoneNumber("614-333-0333");
		
		customerDao.saveObject(customer2);
	}

	/**
	 * Test to ensure that updateCustomer method works properly.
	 */
	@Test
	public void testUpdateObject() 
	{
		Customer customer = customerDao.getObject(Customer.class, "omb1986@hotmail.com");
		assertEquals("Franklin", customer.getPassword());
		customer.setPassword("newPassword");
		customerDao.updateObject(customer);
		assertEquals("newPassword", customerDao.getObject(Customer.class, "omb1986@hotmail.com").getPassword());
	}

	/**
	 * Test to ensure that getProductByCat() method.
	 */
	
	@Test
	public void testGetProductByCat() {
		assertEquals(2, productDao.getProductsByCat("Laptops").size());
		assertEquals(0, productDao.getProductsByCat("No Category").size());
	}
	
	/**
	 * Test to ensure that searchForProduct() method.
	 */
	
	@Test
	public void testSearchForProduct() {
		List<Product> list = productDao.searchForProduct("DV2000");
		assertEquals(1, list.size());
		assertEquals("HP Pavilion DV2000T", list.get(0).getDescription());
		assertEquals(0, productDao.searchForProduct("Invalid").size());

	}
	
	/**
	 * Test for getting list of manufacturers/
	 */
	@Test
	public void testgetManufacturerList() {
		List<String> list = productDao.getManufacturerList();
		assertEquals(2, list.size());
		assertEquals("Apple", list.get(0));
		assertEquals("HP", list.get(1));

	}
	
	@Test
	public void testgetProductCategories() {
		List<String> list = productDao.getProductCategories();
		assertEquals(2, list.size());
		assertEquals("Desktops", list.get(0));
		assertEquals("Laptops", list.get(1));

	}
	
	@Test
	public void testgetFeaturedPage() {
		List<Product> list = productDao.getFeaturedProducts();
		assertEquals(1, list.size());
		assertEquals(new Long(1111), list.get(0).getProductID());

	}
	
	@Test
	public void testGetProductSpecifications() {
		List<ProductSpecificationMap> list = productDao.getProductSpecifications(1111L);
		assertEquals(3, list.size());
		assertEquals("Model Number", list.get(0).getSpecification().getName());
		assertEquals("2000-425NR", list.get(0).getSpecification().getDescription());
		assertEquals("General Information", list.get(0).getSpecification().getCategory());
		
	}
}
