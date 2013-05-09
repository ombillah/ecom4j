package com.ombillah.ecom4j.domain;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ombillah.ecom4j.utils.Constants;

/**
 * Domain object for Shopping Cart
 * 
 * @author Oussama M Billah
 * 
 */
public class ShoppingCart extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private List<CartItem> items;
	private float shippingCost;
	private String shippingOption;

	public ShoppingCart() {
		items = new ArrayList<CartItem>();
		shippingOption = Constants.STANDARD_SHIPPING;
	}

	public List<CartItem> getItems() {
		return this.items;
	}

	public boolean addToCart(CartItem item) {
		return items.add(item);
	}

	public boolean removeFromCart(CartItem item) {
		return items.remove(item);
	}

	public CartItem getCartItem(Long productId) {
		for (CartItem item : items) {
			if (item.getProduct().getProductId().equals(productId)) {
				return item;
			}
		}
		return null;
	}

	public float getProductSubtotal() {
		float total = 0;
		for (CartItem item : items) {
			total += item.getTotalPrice();
		}
		return total;
	}

	public float getTotal() {
		float total = getProductSubtotal() + shippingCost;
		return total;
	}
	public int getSize() {
		return items.size();
	}

	public float getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getShippingOption() {
		return shippingOption;
	}

	public void setShippingOption(String shippingOption) {
		this.shippingOption = shippingOption;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ShoppingCart)) {
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
