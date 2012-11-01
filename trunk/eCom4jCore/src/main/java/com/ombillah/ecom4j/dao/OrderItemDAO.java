package com.ombillah.ecom4j.dao;

import java.util.List;

import com.ombillah.ecom4j.domain.OrderItem;

/**
 * DAO for ORDER_ITEM table operations.
 * extends common CRUD operations from BaseDAOHibernate
 * @author Oussama M Billah
 *
 */
public interface OrderItemDAO extends BaseDAO<OrderItem> {

	/**
	 * Get All the order items for a given order.
	 * 
	 * @param orderID
	 * @return all the order items based on the given order ID
	 */
	public List<OrderItem> getOrderItemByOrderID(long orderID);
	
}
