package com.ombillah.ecom4j.dao;

import java.util.List;
import java.util.Map;

import com.ombillah.ecom4j.domain.Page;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ProductSpecificationMap;


/**
 * DAO Interface to the product table.
 * @author Oussama M Billah
 *
 */
public interface ProductDAO extends BaseDAO<Product> {
	
	public List<Product> searchForProduct(String keyword);
		
	public List<Product> getFeaturedProducts();
	
	public List<ProductSpecificationMap> getProductSpecifications(Long productId);
	
	public Map<String, Integer> getManufacturerList();
	
	public Map<String, Integer> getProductCategories();
	
	public Map<String, Integer> getProductPriceRange();

	public List<Product> getProducts(Page currentPage, Integer startIndex, Integer pageSize);

	public Integer getProductsCount(Map<String, String[]> catalogFilter);
}
