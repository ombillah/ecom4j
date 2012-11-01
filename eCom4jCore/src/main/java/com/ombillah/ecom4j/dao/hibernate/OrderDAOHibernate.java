package com.ombillah.ecom4j.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ombillah.ecom4j.dao.OrderDAO;
import com.ombillah.ecom4j.domain.CustomerOrder;
import com.ombillah.ecom4j.domain.OrderItem;

/**
 * DAO to handle ORDER table operations.
 * @author Oussama M Billah
 *
 */
@Repository
public class OrderDAOHibernate extends BaseDAOHibernate<CustomerOrder> implements OrderDAO {

	
	/**
	 * Creates a new Customer order in Database.
	 * 
	 * @param order
	 * @param orderItems
	 */
	public void createDBOrder(CustomerOrder order, List<OrderItem> orderItems) {
		getSession().save(order);
		for (OrderItem item : orderItems) {
			getSession().save(item);
		}
		getSession().flush();
	}
	
	/**
	 * Gets all orders associated with a given Customer.
	 * 
	 * @param userName
	 * @return the list of orders associated with a given Customer
	 */
	@SuppressWarnings("unchecked")
	public List<CustomerOrder> getOrderByUserName(String email) {
		Criteria criteria = getSession().createCriteria(CustomerOrder.class);
		criteria.add(Restrictions.eq("customer.emailAddress", email));
		List<CustomerOrder> list = criteria.list();
		return list;
	}

}
