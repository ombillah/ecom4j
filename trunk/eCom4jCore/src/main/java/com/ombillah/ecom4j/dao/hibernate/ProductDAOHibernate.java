package com.ombillah.ecom4j.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ombillah.ecom4j.dao.ProductDAO;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.utils.Constants;

/**
 * DAO for PRODUCT table operations.
 * extends common CRUD operations from BaseDAOHibernate
 * @author Oussama M Billah
 *
 */
@Repository
public class ProductDAOHibernate extends BaseDAOHibernate<Product> implements ProductDAO {
	

	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCat(String category) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.createAlias("category","category");
		criteria.add(Restrictions.eq("category.categoryName", category));
		
		List<Product> productList = criteria.list();
		return productList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> searchForProduct(String keyword) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.createAlias("category","category");
		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("make", keyword))
				.add(Restrictions.like("model", keyword))
				.add(Restrictions.like("description", keyword))
				.add(Restrictions.like("category.categoryName", keyword)));
		
		List<Product> productList = criteria.list();
		return productList;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getManufacturerList() {
		String hquery = "select distinct make from Product order by make";
		Query query = getSession().createQuery(hquery);
		List<String> list = query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getProductCategories() {
		String hquery = "select distinct categoryName from ProductCategory order by categoryName";
		Query query = getSession().createQuery(hquery);
		List<String> list = query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getFeaturedProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.ne("featuredOrder", Constants.UNFEATURED_PRODUCT))
				.addOrder(Order.asc("featuredOrder"));
		
		List<Product> productList = criteria.list();
		return productList;
	}

	


}
