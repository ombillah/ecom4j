package com.ombillah.ecom4j.pagination;

import java.util.List;

import com.ombillah.ecom4j.domain.Page;
import com.ombillah.ecom4j.domain.Product;

/**
 * a Service Handler class to Handler the pagination logic.
 * @author Oussama M Billah
 *
 */
public interface PaginationHandler {
	
	public Long getPagesCount(Page currentPage);
	
	public List<Product> getFirstPage(Page currentPage);
	
	public List<Product> getLastPage(Page currentPage);
	
	public List<Product> getPreviousPage(Page currentPage);
	
	public List<Product> getNextPage(Page currentPage);

	public List<Product> getPage(Page currentPage, int pageNumber);
	
	public Page sortCurrentPage(Page currentPage);
			
}
