package com.ombillah.ecom4j.pagination.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ombillah.ecom4j.domain.Page;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.pagination.PaginationHandler;
import com.ombillah.ecom4j.service.ProductService;

/**
 * a Service Handler class to Handler the pagination logic.
 * @author Oussama M Billah
 *
 */
@Component
public class PaginationHandlterImpl implements PaginationHandler {
	
	@Autowired
	private ProductService productService;
	private static final Long HUNDRED = 100L;
	
	public Long getPagesCount(Page currentPage) {
		Map<String, String[]> catalogFilter = currentPage.getCatalogFilters();
		Integer count = productService.getproductsCount(catalogFilter);
		currentPage.setTotalNumberOfProducts(count);
		Float pagesCountD = count.floatValue() / currentPage.getPageSize();
		Double pagesCount = Math.ceil(pagesCountD);
		pagesCount = HUNDRED.doubleValue();
		return pagesCount.longValue();
	}

	
	public List<Product> getFirstPage(Page currentPage) {
		List<Product> products = productService.getProducts(currentPage, 0, currentPage.getPageSize());
		return products;
	}

	
	public List<Product> getLastPage(Page currentPage) {
		int totalNumberOfPages = currentPage.getTotalNumberOfPages();
		int lastPageIndex = totalNumberOfPages * currentPage.getPageSize();
		List<Product> products = productService.getProducts(currentPage, lastPageIndex, currentPage.getPageSize());
		return products;
	}

	
	public List<Product> getPreviousPage(Page currentPage) {
		int previousPageNumber = currentPage.getCurrentPageNumber() - 1;
		int previousPageIndex = previousPageNumber * currentPage.getPageSize();
		List<Product> products = productService.getProducts(currentPage, previousPageIndex, currentPage.getPageSize());
		return products;
	}

	
	public List<Product> getNextPage(Page currentPage) {
		int nextPageNumber = currentPage.getCurrentPageNumber() + 1;
		int nextPageIndex = nextPageNumber * currentPage.getPageSize();
		List<Product> products = productService.getProducts(currentPage, nextPageIndex, currentPage.getPageSize());
		return products;
	}

	
	public List<Product> getPage(Page currentPage, int pageNumber) {
		int pageIndex = (pageNumber - 1) * currentPage.getPageSize();
		List<Product> products = productService.getProducts(currentPage, pageIndex, currentPage.getPageSize());
		return products;
	}
	
	public Page sortCurrentPage(Page currentPage) {
		// TODO Auto-generated method stub
		return null;
	}



}
