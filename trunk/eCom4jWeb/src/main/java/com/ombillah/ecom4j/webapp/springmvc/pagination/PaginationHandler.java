package com.ombillah.ecom4j.webapp.springmvc.pagination;

import com.ombillah.ecom4j.webapp.springmvc.viewbean.Page;

public interface PaginationHandler {
	
	public int getPagesCount();
	
	public Page getFirstPage();
	
	public Page getLastPage();
	
	public Page getPreviousPage(int currentPage);
	
	public Page getNextPage(int currentPage);

	public Page getPage(int position);
	
	public Page sortPage(Page page);
	
}
