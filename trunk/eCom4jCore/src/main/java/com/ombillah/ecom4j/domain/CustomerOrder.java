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

	private static final long serialVersionUID = 1L;

	private long orderID;
	private ShoppingCart cart;
	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private CreditCard paymentCard;
	private String status;
	private float totalCost;
	private Date orderDate;

	public CustomerOrder() {
		// default Constructor.
	}

	public CustomerOrder(long orderid, Customer customer, String status,
			float totalcost, Date date) {
		this.orderID = orderid;
		this.customer = customer;
		this.status = status;
		this.totalCost = totalcost;
		this.orderDate = date;
	}

	public long getOrderID() {
		return this.orderID;
	}

	public void setOrderID(long orderid) {
		this.orderID = orderid;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public CreditCard getPaymentCard() {
		return paymentCard;
	}

	public void setPaymentCard(CreditCard paymentCard) {
		this.paymentCard = paymentCard;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(float totalcost) {
		this.totalCost = totalcost;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

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
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
