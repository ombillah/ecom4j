package com.ombillah.ecom4j.service;

import java.util.List;

import com.ombillah.ecom4j.dao.ProductDAO;
import com.ombillah.ecom4j.domain.Product;

/**
 * Product service Interface that provides all the services related to a Product.
 * 
 * @author Oussama M Billah
 */
public interface ProductService {
	
	public List<Product> getProducts() throws Exception;

	public Product getProductDetails(Long productId) throws Exception;

	public List<Product> searchForProducts(String keyword) throws Exception;
	
	public void updateProduct(Product product) throws Exception;
	
	public void createProduct(Product product) throws Exception;
	
	public List<Product> getProductsByCategory(String category);

	public List<String> getManufacturerList();
	
	public List<Product> getFeaturedProducts();

	public List<String> getProductCategories();
	
	public void setProductDAO(ProductDAO productDao);

	
}
