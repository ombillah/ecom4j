package com.ombillah.ecom4j.dao.hibernate;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ombillah.ecom4j.dao.ProductDAO;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ProductSpecificationMap;
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
	public List<Product> getFeaturedProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.ne("featuredOrder", Constants.UNFEATURED_PRODUCT))
				.addOrder(Order.asc("featuredOrder"));
		
		List<Product> productList = criteria.list();
		return productList;
	}

	@SuppressWarnings("unchecked")
	public List<ProductSpecificationMap> getProductSpecifications(Long productId) {
		Criteria criteria = getSession().createCriteria(ProductSpecificationMap.class);
		criteria.add(Restrictions.eq("productId", productId));
		List<ProductSpecificationMap> specList = criteria.list();
		return specList;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, Integer> getManufacturerList() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		Query query = getSession().getNamedQuery("getProductBrands");
		Iterator it = query.iterate();
		while (it.hasNext()) {  
			Object[] row = (Object[]) it.next();  
			String brand = row[0].toString();  
			Integer count = Integer.parseInt(row[1].toString()); 
			map.put(brand, count);  
  		}  
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, Integer> getProductCategories() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		Query query = getSession().getNamedQuery("getProductCategories");
		Iterator it = query.iterate();
		while (it.hasNext()) {  
			Object[] row = (Object[]) it.next();  
			String category = row[0].toString();  
			Integer count = Integer.parseInt(row[1].toString()); 
			map.put(category, count);  
  		}  
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, Integer> getProductPriceRange() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		Query query = getSession().getNamedQuery("getProductPriceRange");
		Iterator it = query.iterate();
		while (it.hasNext()) {  
			Object[] row = (Object[]) it.next();  
			String priceRange = row[0].toString();  
			Integer count = Integer.parseInt(row[1].toString()); 
			map.put(priceRange, count);  
  		}  
		return map;
	}


	@SuppressWarnings("unchecked")
	public List<Product> getProducts(Map<String, String[]> catalogFilters, Integer startIndex, Integer pageSize) {
		Criteria criteria = getSession().createCriteria(Product.class);
		for(String filterName : catalogFilters.keySet()) {
			String[] filterValues = catalogFilters.get(filterName);
			if(StringUtils.equals(filterValues[0], "all")) {
				continue;
			}
			if(StringUtils.equals(filterName, "price")) {
				Disjunction disj = Restrictions.disjunction();
				for(String priceRange : filterValues) {
					String[] priceRangeArray = priceRange.split(" - ");
					Float lowerLimit = Float.valueOf(priceRangeArray[0]);
					Float upperLimit = Float.valueOf(priceRangeArray[1]);
					disj.add(Restrictions.between("unitPrice", lowerLimit , upperLimit));
				}
				criteria.add(disj);
			} else if(StringUtils.equals(filterName, "category")){
				criteria.createAlias("category","category");
				criteria.add(Restrictions.in("category.categoryName", filterValues));
			} else {
				criteria.add(Restrictions.in(filterName, filterValues));
			}
		}
		criteria.setMaxResults(startIndex);
		criteria.setFirstResult(pageSize);
		List<Product> productList = criteria.list();
		return productList;
	}

}
