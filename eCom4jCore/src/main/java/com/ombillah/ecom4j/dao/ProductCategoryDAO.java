package com.ombillah.ecom4j.dao;


import java.util.List;

import com.ombillah.ecom4j.domain.ProductCategory;

/**
 * DAO for product Category functionality.
 * @author Oussama M Billah.
 *
 */
public interface ProductCategoryDAO extends BaseDAO<ProductCategory>  {

	List<ProductCategory> getParentCategories();

	String[] getProductCategoryIds(String[] categoryNames);

}
