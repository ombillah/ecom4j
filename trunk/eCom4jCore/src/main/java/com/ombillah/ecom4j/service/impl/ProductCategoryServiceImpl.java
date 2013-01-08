package com.ombillah.ecom4j.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ombillah.ecom4j.dao.ProductCategoryDAO;
import com.ombillah.ecom4j.domain.ProductCategory;
import com.ombillah.ecom4j.service.ProductCategoryService;

/**
 * Service class for Product Categories functionality.
 * @author Oussama M Billah.
 *
 */
@Service("productCategoryService")
@Transactional (readOnly = true)
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Resource(name="productCategoryDAO")
	private ProductCategoryDAO productCategoryDao;

	@Transactional (readOnly = false)
	public void createProductCategories(List<ProductCategory> productCategories) {
		productCategoryDao.saveObjects(productCategories);
	}
	
	@Transactional
	public Integer getCount() {
		return productCategoryDao.getRowCount(ProductCategory.class);
	}
	
}
