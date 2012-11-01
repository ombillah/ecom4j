/*******************************************************************************
 *  Copyright 2010 Oussama M Billah
 *   
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *   
 *     http://www.apache.org/licenses/LICENSE-2.0
 *   
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 ******************************************************************************/
package com.ombillah.ecom4j.domain;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Domain object for Shopping Cart
 * @author Oussama M Billah
 *
 */
public class ShoppingCart extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	private List<CartItem> items;

	public ShoppingCart(){
		items = new ArrayList<CartItem>();
	}
	public List<CartItem> getItems(){
		return this.items;
	}
	
	public boolean addToCart(CartItem item){
		return items.add(item);
	}
	
	public boolean removeFromCart(CartItem item){
		return items.remove(item);
	}
	
	public CartItem getCartItem(Long productID){
		for(CartItem item : items){
			if (item.getProduct().getProductID().equals(productID)){
				return item;
			}
		}
		return null;
	}
	
	public float getTotal() {
		float total = 0;
		for(CartItem item : items){
			total += item.getTotalPrice();
		}
		return total;
	}
	
	public int getSize(){
		return items.size();
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
