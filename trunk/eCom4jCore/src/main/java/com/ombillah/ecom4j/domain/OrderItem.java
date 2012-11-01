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
	
	/** unique ID representing order item. */
	private long itemID;
	
	/** Product which is ordered for this order item. */
	private Product product;
	
	/** Order of which this order item is part of. */
	private CustomerOrder customerOrder;
	
	/** quantity ordered for the item. */
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
	public OrderItem(long itemid, Product product, CustomerOrder customerorder) {
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
	public OrderItem(long itemid, Product product,
			CustomerOrder customerorder, long quantity) {
		this.itemID = itemid;
		this.product = product;
		this.customerOrder = customerorder;
		this.quantity = quantity;
	}
	
	/**
	 * Gets Order Item ID.
	 *  
	 * @return itemID
	 */
	public long getItemID() {
		return this.itemID;
	}
	
	/**
	 * Sets Order Item ID. This method is not used for this project.
	 * 
	 * @param itemid
	 */
	@SuppressWarnings("unused")
	private void setItemID(long itemid) {
		this.itemID = itemid;
	}

	/**
	 * Gets the Product associated with the order Item.
	 * 
	 * @return product
	 */
	public Product getProduct() {
		return this.product;
	}
	
	/**
	 * Sets the Product associated with the order Item. This method
	 * is not used for this project.
	 * 
	 * @param product
	 */
	@SuppressWarnings("unused")
	private void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * Gets the Order of which this order item is part of.
	 * 
	 * @return customerOrder
	 */
	public CustomerOrder getCustomerOrder() {
		return this.customerOrder;
	}
	
	/**
	 * Sets the Order of which this order item is part of. This method is 
	 * not used for this project.
	 * 
	 * @param customerorder
	 */
	@SuppressWarnings("unused")
	private void setCustomerOrder(CustomerOrder customerorder) {
		this.customerOrder = customerorder;
	}
	
	/**
	 * Gets the quantity order for an order item.
	 * 
	 * @return quantity
	 */
	public long getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Sets the Quantity for order item 
	 * @param quantity
	 */
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
