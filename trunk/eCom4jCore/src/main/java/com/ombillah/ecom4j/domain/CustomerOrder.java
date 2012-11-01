package com.ombillah.ecom4j.domain;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Class representing a customer order.
 * 
 * @author Oussama El Mouatassim Billah
 * @version 1.0
 */
public class CustomerOrder extends BaseDomain {
	
	/** This attribute represents the serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** unique ID representing a customer order. */
	private long orderID;
	
	/** Name of the user who placed the order. */
	private Customer customer;
	
	/**
	 * Order status. Valid values are: "New", "Cancelled", "In Process",
	 * "Shipped".
	 */
	private String status;
	
	/** Total cost of the order including shipping cost. */
	private float totalCost;
	
	/** Date when order was placed. - DB persistance form will be in date format. */
	private Date orderDate;

	/**
	 * Default Constructor method.
	 */
	public CustomerOrder() {
		// default constructor
	}

	/**
	 * Explicit Constructor method. Creates a CustomerOrder with an orderID,
	 * a customer, a status, total cost, and order date.
	 * 
	 * @param orderid
	 * @param customer
	 * @param status
	 * @param totalcost
	 * @param date
	 */
	public CustomerOrder(long orderid, Customer customer, String status,
			float totalcost, Date date) {
		this.orderID = orderid;
		this.customer = customer;
		this.status = status;
		this.totalCost = totalcost;
		this.orderDate = date;
	}

	/**
	 * Gets the order ID.
	 * 
	 * @return orderID
	 */
	public long getOrderID() {
		return this.orderID;
	}

	/**
	 * Sets the order ID.
	 * 
	 * @param orderid
	 */
	public void setOrderID(long orderid) {
		this.orderID = orderid;
	}

	/**
	 * Gets the customer for which this order is created
	 * 
	 * @return customer
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * Sets the customer for which this order is created.
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the order status.
	 * 
	 * @return status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the order status.
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the total cost for the order.
	 * 
	 * @return totalCost
	 */
	public float getTotalCost() {
		return this.totalCost;
	}

	/**
	 * Sets the total cost for the order.
	 * 
	 * @param totalcost
	 */
	public void setTotalCost(float totalcost) {
		this.totalCost = totalcost;
	}

	/**
	 * Returns the order date instance variable. This method is not used
	 * for this project.
	 * 
	 * @return orderDate
	 */
	public Date getOrderDate() {
		return this.orderDate;
	}

	/**
	 * Sets the order date instance variable. This method is not 
	 * used for this project.
	 * 
	 * @param orderdate
	 */
	public void setOrderDate(Date orderdate) {
		this.orderDate = orderdate;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CustomerOrder)) {
			return false;
		}

		return EqualsBuilder.reflectionEquals(this, object);
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE);
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
