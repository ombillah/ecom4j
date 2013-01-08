package com.ombillah.ecom4j.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.ombillah.ecom4j.dao.ProductCategoryDAO;
import com.ombillah.ecom4j.domain.ProductCategory;


/**
 * DAO for PRODUCT_CATEGORY table operations.
 * extends common CRUD operations from BaseDAOHibernate
 * @author Oussama M Billah
 *
 */
@Repository("productCategoryDAO")
public class ProductCategoryDAOHibernate extends BaseDAOHibernate<ProductCategory> implements ProductCategoryDAO {

}
