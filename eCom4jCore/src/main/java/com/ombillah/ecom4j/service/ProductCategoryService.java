package com.ombillah.ecom4j.service;

import java.util.List;

import com.ombillah.ecom4j.domain.ProductCategory;

/**
 * Service class for Product Categories functionality.
 * @author Oussama M Billah.
 *
 */
public interface ProductCategoryService {
	
	public void createProductCategories(List<ProductCategory> productCategories);

	public Integer getCount();

}
