package com.ombillah.ecom4j.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Class representing an order item.
 * 
 * @author Oussama El Mouatassim Billah
 * @version 1.0
 */
public class OrderItem extends  BaseDomain {
	
	/** This attribute represents the serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	private Long itemID;
	private Product product;
	private CustomerOrder customerOrder;
	private long quantity;
	
	/**
	 * Default Constructor method.
	 */
	public OrderItem() {
		// default constructor
	}
	
	/**
	 * Explicit Constructor 1. Creates OrderItem with an itemID, product,
	 * and CustomerOrder.
	 * 
	 * @param itemid
	 * @param product
	 * @param customerorder
	 */
	public OrderItem(Long itemid, Product product, CustomerOrder customerorder) {
		this.itemID = itemid;
		this.product = product;
		this.customerOrder = customerorder;
	}
	
	/**
	 * Explicit Constructor 2. Creates OrderItem with an itemID, product,
	 * CustomerOrder, and quantity.
	 * 
	 * @param itemid
	 * @param product
	 * @param customerorder
	 * @param quantity
	 */
	public OrderItem(Long itemid, Product product,
			CustomerOrder customerorder, long quantity) {
		this.itemID = itemid;
		this.product = product;
		this.customerOrder = customerorder;
		this.quantity = quantity;
	}
	
	public Long getItemID() {
		return this.itemID;
	}
	
	public void setItemID(Long itemid) {
		this.itemID = itemid;
	}

	public Product getProduct() {
		return this.product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public CustomerOrder getCustomerOrder() {
		return this.customerOrder;
	}
	
	public void setCustomerOrder(CustomerOrder customerorder) {
		this.customerOrder = customerorder;
	}
	
	public long getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof OrderItem)) {
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
