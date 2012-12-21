package com.ombillah.ecom4j.domain;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Domain Object to hold product Rating properties.
 * @author Oussama M Billah
 *
 */
public class ProductRating extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private Long productId;
	private Long ratingOutOf5;
	private String reviewSummary;
	private String reviewDescription;
	private String creatorName;
	private String creatorEmail;
	private Date createdDate;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getRatingOutOf5() {
		return ratingOutOf5;
	}

	public void setRatingOutOf5(Long ratingOutOf5) {
		this.ratingOutOf5 = ratingOutOf5;
	}

	public String getReviewSummary() {
		return reviewSummary;
	}

	public void setReviewSummary(String reviewSummary) {
		this.reviewSummary = reviewSummary;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorEmail() {
		return creatorEmail;
	}

	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProductRating)) {
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
