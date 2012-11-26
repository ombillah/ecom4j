package com.ombillah.ecom4j.webapp.springmvc.pagination.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ombillah.ecom4j.service.ProductService;
import com.ombillah.ecom4j.webapp.springmvc.pagination.PaginationHandler;
import com.ombillah.ecom4j.webapp.springmvc.viewbean.Page;

@Component
public class PaginationHandlterImpl implements PaginationHandler {
	
	@Autowired
	ProductService productService;
	
	@Override
	public int getPagesCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page getFirstPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page getLastPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page getPreviousPage(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page getNextPage(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page getPage(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page sortPage(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
