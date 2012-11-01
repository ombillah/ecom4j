package com.ombillah.ecom4j.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ombillah.ecom4j.dao.ProductDAO;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ProductSpecificationMap;
import com.ombillah.ecom4j.exception.ProductExistsException;
import com.ombillah.ecom4j.exception.ProductNotFoundException;
import com.ombillah.ecom4j.service.ProductService;


/**
 * Product Service Class that provides all the services related to a Product.
 * 
 * @author Oussama M Billah
 */
@Service
@Transactional (readOnly = true)
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDao;
	
	
	@Transactional
	public Product getProductDetails(Long productId) throws Exception {
		return productDao.getObject(Product.class, productId);
	}

	@Transactional
	public List<Product> getProducts() throws Exception {
		return productDao.getObjects(Product.class);
	}

	@Transactional
	public List<Product> searchForProducts(String keyword) throws Exception {
		List<Product> products = productDao.searchForProduct(keyword);
		
		if (CollectionUtils.isEmpty(products)){
			throw new ProductNotFoundException("No result found, please use a different keyword");
		}
		return products;
	}

	@Transactional (readOnly = false)
	public void updateProduct(Product product) throws Exception {
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
	
	@Transactional
	public List<Product> getProductsByCategory(String category){
		return productDao.getProductsByCat(category);
	}

	@Transactional
	public List<String> getManufacturerList() {
		return productDao.getManufacturerList();
	}

	@Transactional
	public List<Product> getFeaturedProducts() {
		return productDao.getFeaturedProducts();
	}
	
	@Transactional
	public List<String> getProductCategories() {
		return productDao.getProductCategories();
	}
	
	@Transactional
	public List<ProductSpecificationMap> getProductSpecifications(Long productId) {
		return productDao.getProductSpecifications(productId);
	}
	
	/**
	 * setter to be used for Mocking.
	 * @param productDao
	 */
	public void setProductDAO(ProductDAO productDao) {
		this.productDao = productDao;
	}

	
	



}
