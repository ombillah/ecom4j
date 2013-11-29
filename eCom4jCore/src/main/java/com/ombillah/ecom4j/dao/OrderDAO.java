package com.ombillah.ecom4j.dao;

import java.util.List;

import com.ombillah.ecom4j.domain.CustomerOrder;
import com.ombillah.ecom4j.domain.OrderItem;

/**
 * Interface to the ORDER table operations.
 * @author Oussama M Billah
 *
 */
public interface OrderDAO extends BaseDAO<CustomerOrder> {
	
	
	/**
	 * Creates a new Customer order in Database.
	 * 
	 * @param order
	 * @param orderItems
	 */
	public void createDBOrder(CustomerOrder order, List<OrderItem> orderItems);
	
	/**
	 * Gets all orders associated with a given Customer.
	 * 
	 * @param userName
	 * @return the list of orders associated with a given Customer
	 */
	public List<CustomerOrder> getOrderByUserName(String email);
	

}
