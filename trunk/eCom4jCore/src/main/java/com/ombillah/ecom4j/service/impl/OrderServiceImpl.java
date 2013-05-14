package com.ombillah.ecom4j.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ombillah.ecom4j.dao.CustomerDAO;
import com.ombillah.ecom4j.dao.OrderDAO;
import com.ombillah.ecom4j.domain.CartItem;
import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.domain.CustomerOrder;
import com.ombillah.ecom4j.domain.OrderItem;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ShoppingCart;
import com.ombillah.ecom4j.exception.OrderNotFoundException;
import com.ombillah.ecom4j.service.OrderService;

/**
 * Order BO Class that provides all the services related to an Order.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
@Service("orderService")
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private  CustomerDAO customerDao;

	/**
	 * Returns all the orders that the customer has placed based on the given
	 * customer user name.
	 * 
	 * @param userName
	 *            The user name of the customer
	 * @return all the orders that the customer has placed
	 * @throws Exception
	 */
	@Transactional
	public List<CustomerOrder> getOrderForCustomer(String userName)
			throws Exception {
		return orderDao.getOrderByUserName(userName);
	}

	/**
	 * Gets the status of a specific order based on the given orderId.
	 * 
	 * @param orderId
	 *            The given order Id for the Customer Order to be returned
	 * @return the status of a specific order based on the given orderId
	 * @throws Exception
	 */
	@Transactional
	public CustomerOrder getOrderDetail(long orderId) throws Exception {
		CustomerOrder order = orderDao.getObject(CustomerOrder.class, orderId);
		if (order == null) {
			throw new OrderNotFoundException();
		}
		return order;
	}

	/**
	 * Update the status of an order based on the given order object.
	 * 
	 * @param order
	 */
	@Transactional (readOnly = false)
	public void updateOrder(CustomerOrder order) throws Exception {
		orderDao.updateObject(order);
	}

	/**
	 * Check Out function.
	 */
	@Transactional (readOnly = false)
	public Long checkout(ShoppingCart cart, String email) throws Exception {
		List<CartItem> list = cart.getItems();
		List<OrderItem> items = new ArrayList<OrderItem>();
		long orderId = generateOrderId();
		CustomerOrder order = new CustomerOrder(orderId, customerDao.getObject(Customer.class, email), "IN PROCESS",
				cart.getTotal(), new Date());
		for (CartItem item : list) {
			Product product = item.getProduct();
			OrderItem orderItem = new OrderItem(null, product, order);
			items.add(orderItem);
		}
		orderDao.createDBOrder(order, items);
		return orderId;
	}

	private Long generateOrderId() throws Exception {
		Long maxOrderId = orderDao.getMaxId(CustomerOrder.class, "orderID");
		return maxOrderId + 1;
	}
	
	
	/**
	 * setter to be used for Mocking.
	 * @param orderDao
	 */
	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
	
	/**
	 * setter to be used for Mocking.
	 * @param customerDao
	 */
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
	
}
