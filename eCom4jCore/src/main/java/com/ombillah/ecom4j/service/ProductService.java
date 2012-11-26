package com.ombillah.ecom4j.service;

import java.util.List;
import java.util.Map;

import com.ombillah.ecom4j.dao.ProductDAO;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ProductSpecificationMap;

/**
 * Product service Interface that provides all the services related to a Product.
 * 
 * @author Oussama M Billah
 */
public interface ProductService {
	
	public List<Product> getProducts();

	public Product getProductDetails(Long productId);

	public List<Product> searchForProducts(String keyword);
	
	public void updateProduct(Product product);
	
	public void createProduct(Product product) throws Exception;
		
	public List<Product> getFeaturedProducts();
	
	public List<ProductSpecificationMap> getProductSpecifications(Long productId);
	
	public void setProductDAO(ProductDAO productDao);
	
	public Map<String, Integer> getManufacturerList();
	
	public Map<String, Integer> getProductCategories();
	
	public Map<String, Integer> getProductPriceRange();
	
	public List<Product> getProducts(Map<String, String[]> catalogFilters, Integer startIndex, Integer pageSize);
}
