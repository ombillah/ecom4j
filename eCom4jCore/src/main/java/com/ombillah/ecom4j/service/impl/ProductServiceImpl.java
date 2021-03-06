package com.ombillah.ecom4j.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ombillah.ecom4j.dao.ProductDAO;
import com.ombillah.ecom4j.domain.Page;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ProductRating;
import com.ombillah.ecom4j.domain.ProductSpecificationMap;
import com.ombillah.ecom4j.exception.ProductExistsException;
import com.ombillah.ecom4j.service.ProductService;


/**
 * Product Service Class that provides all the services related to a Product.
 * 
 * @author Oussama M Billah
 */
@Service("productService")
@Transactional (readOnly = true)
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDao;
	
	
	@Transactional
	public Product getProductDetails(Long productId) {
		return productDao.getObject(Product.class, productId);
	}

	@Transactional
	public List<Product> getProducts() {
		return productDao.getObjects(Product.class);
	}

	@Transactional
	public List<Product> searchForProducts(String keyword) {
		List<Product> products = productDao.searchForProduct(keyword);
		return products;
	}

	@Transactional (readOnly = false)
	public void updateProduct(Product product) {
		productDao.updateObject(product);
	}
	
	@Transactional (readOnly = false)
	public void createProduct(Product product) throws Exception{
		try{
			product.setCreatedDate(new Date());
			productDao.saveObject(product);
		} catch(NonUniqueObjectException ex){
			throw new ProductExistsException("product already exist");
		}
	}
	
	@Transactional (readOnly = false)
	public void createProducts(List<Product> products) {
		productDao.saveObjects(products);
	}

	@Transactional
	public Map<String, Integer> getManufacturerList(String categoryId) {
		return productDao.getManufacturerList(categoryId);
	}
	
	@Transactional
	public Map<String, Integer> getProductCategories(String categoryId) {
		return productDao.getProductCategories(categoryId);
	}
	
	@Transactional
	public List<Product> getFeaturedProducts() {
		return productDao.getFeaturedProducts();
	}
	
	@Transactional
	public List<ProductSpecificationMap> getProductSpecifications(Long productId) {
		return productDao.getProductSpecifications(productId);
	}

	@Transactional
	public Map<String, Integer> getProductPriceRange(String categoryId) {
		return productDao.getProductPriceRange(categoryId);
	}

	@Transactional
	public List<Product> getProducts(Page currentPage, Integer startIndex, Integer pageSize) {
		return productDao.getProducts(currentPage, startIndex, pageSize);
	}
	
	@Transactional
	public Integer getproductsCount(Page currentPage) {
		return productDao.getProductsCount(currentPage);
	}
	
	@Transactional (readOnly = false)
	public void createProductReview(ProductRating rating) {
		productDao.createProductReview(rating);
	}
	
	@Transactional
	public Integer getCount() {
		return productDao.getRowCount(Product.class);
	}
	
	/**
	 * setter to be used for Mocking.
	 * @param productDao
	 */
	public void setProductDAO(ProductDAO productDao) {
		this.productDao = productDao;
	}

	




}
