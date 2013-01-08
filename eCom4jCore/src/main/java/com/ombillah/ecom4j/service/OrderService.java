package com.ombillah.ecom4j.service;

import java.util.List;

import com.ombillah.ecom4j.dao.CustomerDAO;
import com.ombillah.ecom4j.dao.OrderDAO;
import com.ombillah.ecom4j.dao.OrderItemDAO;
import com.ombillah.ecom4j.domain.CustomerOrder;
import com.ombillah.ecom4j.domain.ShoppingCart;

/**
 * Order Service Interface that provides all the services related to an order.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public interface OrderService {
	
	/**
	 * Returns all the orders that the customer has placed based on the given customer user name.
	 * 
	 * @param userName The user name of the customer
	 * @return all the orders that the customer has placed
	 * @throws Exception
	 */
	public List<CustomerOrder> getOrderForCustomer(String userName) throws Exception;
	
	/**
	 * Gets the status of a specific order based on the given orderId.
	 * 
	 * @param orderId The given order Id for the Customer Order to be returned
	 * @return the specific order based on the given orderId
	 * @throws Exception
	 */
	public CustomerOrder getOrderDetail(long orderId) throws Exception;
	
	/**
	 * Update the status of an order based on the given order.
	 * 
	 * @param order The given order to be updated 
	 */
	public void updateOrder(CustomerOrder order) throws Exception;
	
	/**
	 * Check Out function.
	 */
	public void checkout(ShoppingCart cart, String userName) throws Exception;
	
	/**
	 * setter to be used for Mocking.
	 * @param orderDao
	 */
	public void setOrderDao(OrderDAO orderDao);
	
	/**
	 * setter to be used for Mocking.
	 * @param customerDao
	 */
	public void setCustomerDao(CustomerDAO customerDao);

	/**
	 * setter to be used for Mocking.
	 * @param orderItemDao
	 */
	public void setOrderItemDao(OrderItemDAO orderItemDao);
}
