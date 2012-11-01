package com.ombillah.ecom4j.dao;

import java.util.List;

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
	
	public List<String> getManufacturerList();
	
	public List<Product> getFeaturedProducts();

	public List<String> getProductCategories();
	
	public List<ProductSpecificationMap> getProductSpecifications(Long productId);
}
