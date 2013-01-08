package com.ombillah.ecom4j.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Domain to hold Cart item properties.
 * @author Oussama M Billah.
 *
 */
public class CartItem extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Product product;
	private long quantity;
	
	public CartItem() {
		
	}
	
	public CartItem(Product product, long quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return this.product;
	}

	public float getTotalPrice() {
		return product.getSalePrice() * quantity;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CartItem)) {
			return false;
		}

		return EqualsBuilder.reflectionEquals(this, object);
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE);
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
