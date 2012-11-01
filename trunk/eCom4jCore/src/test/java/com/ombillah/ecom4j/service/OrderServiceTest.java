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


import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import org.junit.Before;
import org.junit.Test;

import com.ombillah.ecom4j.dao.CustomerDAO;
import com.ombillah.ecom4j.dao.OrderDAO;
import com.ombillah.ecom4j.dao.OrderItemDAO;
import com.ombillah.ecom4j.dao.ProductDAO;
import com.ombillah.ecom4j.domain.CartItem;
import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.domain.CustomerOrder;
import com.ombillah.ecom4j.domain.OrderItem;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ShoppingCart;
import com.ombillah.ecom4j.exception.OrderNotFoundException;
import com.ombillah.ecom4j.service.impl.OrderServiceImpl;


/**
 * A class that test the all the Order BO orderService.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public class OrderServiceTest {

	private OrderService orderService;
	private OrderDAO orderDaoMock;
	private CustomerDAO customerDaoMock;
	private ProductDAO productDaoMock;
	private OrderItemDAO orderItemDaoMock;

	@Before
	public void setUp() {
		orderService = new OrderServiceImpl();
		
		orderDaoMock = createMock(OrderDAO.class);
		customerDaoMock = createMock(CustomerDAO.class);
		productDaoMock = createMock(ProductDAO.class);
		orderItemDaoMock = createMock(OrderItemDAO.class);
		
		orderService.setOrderDao(orderDaoMock);
		orderService.setCustomerDao(customerDaoMock);
		orderService.setProductDao(productDaoMock);
		orderService.setOrderItemDao(orderItemDaoMock);
	}
	
	/**
	 * Test to ensure that getOrderForCustomer() works properly.
	 * @throws Exception
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testGetOrderForCustomer() throws Exception{
	   
		List<CustomerOrder> orders = createMock(List.class);
		
        expect(orderDaoMock.getOrderByUserName("Oussama")).andReturn(orders);
		replay(orderDaoMock);
		assertEquals(orders, orderService.getOrderForCustomer("Oussama"));
		verify(orderDaoMock);

	}
	
	/**
	 * Test the getOrderDetail() functionality.
	 * @throws Exception
	 */
	@Test (expected = OrderNotFoundException.class)
	public void testGetOrderDetail() throws Exception{
		
		// test for good order.
		CustomerOrder order = createMock(CustomerOrder.class);
		
        expect(orderDaoMock.getObject(CustomerOrder.class, 9999L)).andReturn(order);
		replay(orderDaoMock);
		assertEquals(order, orderService.getOrderDetail(9999));
		verify(orderDaoMock);
		
		// test for no orders
		reset(orderDaoMock);
		
		expect(orderDaoMock.getObject(CustomerOrder.class, 5555L)).andReturn(null);
		replay(orderDaoMock);
		
		try {
			orderService.getOrderDetail(5555);
		} finally {
			verify(orderDaoMock);
		}

	}
	
	/**
	 * Test updateOrder() functionality.
	 * @throws Exception
	 */
	@Test
	public void testUpdateOrder() throws Exception{
		CustomerOrder order = new CustomerOrder();
		orderDaoMock.updateObject(order);
		expectLastCall();
		replay(customerDaoMock);
		orderService.updateOrder(order);
		verify(customerDaoMock);
	}
	
	/**
	 * Test Check out.
	 * @throws Exception
	 */
	@Test
	public void testCheckout() throws Exception{
		
		Product product1 = new Product();
		Product product2 = new Product();
		
		product1.setQuantity(5);
		product2.setQuantity(10);
		CartItem item1 = new CartItem(product1, 2);
		CartItem item2 = new CartItem(product2, 1);
		ShoppingCart cart = new ShoppingCart();
		cart.addToCart(item1);
		cart.addToCart(item2);
		
		Customer customer = new Customer();
		customer.setEmailAddress("omb1986@hotmail.com");
		expect(orderDaoMock.getObject(eq(CustomerOrder.class), anyLong())).andReturn(null);
		expect(customerDaoMock.getObject(Customer.class, "omb1986@hotmail.com")).andReturn(customer);
		expect(orderItemDaoMock.getObject(eq(OrderItem.class), anyLong())).andReturn(null).times(2);
		productDaoMock.updateObject((Product) anyObject());
		expectLastCall().times(2);
		
		CustomerOrder order = new CustomerOrder(anyLong(), customer, "IN PROCESS",
				0, (Date) anyObject());
		
		OrderItem orderItem1 = new OrderItem(1, item1.getProduct(), order);
		OrderItem orderItem2 = new OrderItem(2, item2.getProduct(), order);
		List<OrderItem> list = new ArrayList<OrderItem>();
		list.add(orderItem1);
		list.add(orderItem2);
		
		orderDaoMock.createDBOrder(order, list);
		expectLastCall();
		
   	    replay(customerDaoMock);
		replay(productDaoMock);
		replay(orderDaoMock);
		replay(orderItemDaoMock);
		
		orderService.checkout(cart, "omb1986@hotmail.com");
		
		verify(customerDaoMock);
		verify(productDaoMock);
		verify(orderDaoMock);
		verify(orderItemDaoMock);
	}

}
