package com.ombillah.ecom4j.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Domain object to hold pagination related properties.
 * @author Oussama M Billah
 *
 */
public class Page extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private List<Product> products;
	private Map<String, String[]> catalogFilters;
	private List<Integer> availablePageSizes;
	private int totalNumberOfPages;
	private int totalNumberOfProducts;
	private int currentPageNumber;
	private String sortBy;
	private boolean sortAsc;
	private int pageSize;
	private String searchKeyword;
	private boolean isParentCategory;
	
	
	public Page() {
		currentPageNumber = 1;
		sortBy = "customerReviewAverage";
		sortAsc = false;
		Map<String, String[]> filters = new HashMap<String, String[]>();
		String[] all = {"all"};
		filters.put("category", all);
		filters.put("make", all);
		filters.put("price", all);
		this.catalogFilters = filters;
		
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Map<String, String[]> getCatalogFilters() {
		return catalogFilters;
	}

	public void setCatalogFilters(Map<String, String[]> catalogFilters) {
		this.catalogFilters = catalogFilters;
	}
	
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public void setTotalNumberOfPages(int totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isSortAsc() {
		return sortAsc;
	}

	public void setSortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
	}
	
	public int getTotalNumberOfProducts() {
		return totalNumberOfProducts;
	}

	public void setTotalNumberOfProducts(int totalNumberOfProducts) {
		this.totalNumberOfProducts = totalNumberOfProducts;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Integer> getAvailablePageSizes() {
		return availablePageSizes;
	}

	public void setAvailablePageSizes(List<Integer> availablePageSizes) {
		this.availablePageSizes = availablePageSizes;
	}

	public int getfistItemsIndex() {
		int pageIndex = currentPageNumber - 1;
		return (pageIndex  * pageSize) + 1;
	}
	
	public int getLastItemsIndex() {
		return getfistItemsIndex() + products.size() - 1;
	}
	
	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public boolean isParentCategory() {
		return isParentCategory;
	}

	public void setParentCategory(boolean isParentCategory) {
		this.isParentCategory = isParentCategory;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Page)) {
			return false;
		}
		return EqualsBuilder.reflectionEquals(this, object);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
