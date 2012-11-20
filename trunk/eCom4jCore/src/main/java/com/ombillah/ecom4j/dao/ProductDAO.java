package com.ombillah.ecom4j.dao;

import java.util.List;
import java.util.Map;

import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ProductSpecificationMap;


/**
 * DAO Interface to the product table.
 * @author Oussama M Billah
 *
 */
public interface ProductDAO extends BaseDAO<Product> {
	
	public List<Product> getProductsByCat(String category);
	
	public List<Product> searchForProduct(String keyword);
		
	public List<Product> getFeaturedProducts();
	
	public List<ProductSpecificationMap> getProductSpecifications(Long productId);

	public List<Product> getProductsByBrand(String brand);
	
	public Map<String, Integer> getManufacturerList();
	
	public Map<String, Integer> getProductCategories();
	
	public Map<String, Integer> getProductPriceRange();

	public List<Product> getProducts(Map<String, String[]> catalogFilters);
}
