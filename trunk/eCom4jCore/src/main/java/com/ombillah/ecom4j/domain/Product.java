package com.ombillah.ecom4j.domain;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Class representing product.
 * 
 * @author Oussama M Billah
 * @version 1.0
 */
public class Product extends BaseDomain {
	
	
	private static final long serialVersionUID = 1L;
	
	private Long productID;
	private String make;
	private String model;
	private String description;
	private String mainImageUrl;
	private String image1Url;
	private String image2Url;
	private String image3Url;
	private String image4Url;
	private ProductCategory category;
	private String status;
	private long quantity;
	private float unitPrice;
	private int featuredOrder;
	private Date createdDate;
	
	public Long getProductID() {
		return this.productID;
	}
	
	public void setProductID(Long productid) {
		this.productID = productid;
	}
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getMainImageUrl() {
		return mainImageUrl;
	}

	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public String getImage1Url() {
		return image1Url;
	}

	public void setImage1Url(String image1Url) {
		this.image1Url = image1Url;
	}

	public String getImage2Url() {
		return image2Url;
	}

	public void setImage2Url(String image2Url) {
		this.image2Url = image2Url;
	}

	public String getImage3Url() {
		return image3Url;
	}

	public void setImage3Url(String image3Url) {
		this.image3Url = image3Url;
	}

	public String getImage4Url() {
		return image4Url;
	}

	public void setImage4Url(String image4Url) {
		this.image4Url = image4Url;
	}

	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public float getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(float unitprice) {
		this.unitPrice = unitprice;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
	public int getFeaturedOrder() {
		return featuredOrder;
	}

	public void setFeaturedOrder(int featuredOrder) {
		this.featuredOrder = featuredOrder;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Product)) {
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
