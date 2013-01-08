package com.ombillah.ecom4j.domain;

import java.util.Date;
import java.util.List;

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

	private Long productId;
	private String make;
	private String model;
	private String sku;
	private String name;
	private String shortDescriptionHtml;
	private String image;
	private ProductCategory category;
	private List<ProductRating> productRatings;
	private Long customerReviewCount;
	private Double customerReviewAverage;
	private Float regularPrice;
	private Float salePrice;
	private Boolean inStock;
	private Date createdDate;
	private String createdBy;
	private Date modifiedDate;
	private String modifiedBy;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescriptionHtml() {
		return shortDescriptionHtml;
	}

	public void setShortDescriptionHtml(String shortDescriptionHtml) {
		this.shortDescriptionHtml = shortDescriptionHtml;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public List<ProductRating> getProductRatings() {
		return productRatings;
	}

	public void setProductRatings(List<ProductRating> productRatings) {
		this.productRatings = productRatings;
	}

	public Long getCustomerReviewCount() {
		return customerReviewCount;
	}

	public void setCustomerReviewCount(Long customerReviewCount) {
		this.customerReviewCount = customerReviewCount;
	}

	public Double getCustomerReviewAverage() {
		return customerReviewAverage;
	}

	public void setCustomerReviewAverage(Double customerReviewAverage) {
		this.customerReviewAverage = customerReviewAverage;
	}

	public Float getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(Float regularPrice) {
		this.regularPrice = regularPrice;
	}

	public Float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
