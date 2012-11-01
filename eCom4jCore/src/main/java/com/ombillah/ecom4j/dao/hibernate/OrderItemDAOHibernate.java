package com.ombillah.ecom4j.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ombillah.ecom4j.dao.OrderItemDAO;
import com.ombillah.ecom4j.domain.OrderItem;

/**
 * DAO for ORDER_ITEM table operations.
 * extends common CRUD operations from BaseDAOHibernate
 * @author Oussama M Billah
 *
 */
@Repository
public class OrderItemDAOHibernate extends BaseDAOHibernate<OrderItem> implements OrderItemDAO {

	/**
	 * Get All the order items for a given order.
	 * 
	 * @param orderID
	 * @return all the order items based on the given order ID
	 */
	@SuppressWarnings("unchecked")
	public List<OrderItem> getOrderItemByOrderID(long orderID) {
		Criteria criteria = getSession().createCriteria(OrderItem.class);
		criteria.createAlias("customerOrder", "order");
		criteria.add(Restrictions.eq("order.orderID", orderID));
		List<OrderItem> list = criteria.list();
		return list;
	}

}
