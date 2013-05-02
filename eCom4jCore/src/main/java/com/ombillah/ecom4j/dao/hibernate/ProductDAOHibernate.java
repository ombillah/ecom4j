
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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ombillah.ecom4j.dao.ProductDAO;
import com.ombillah.ecom4j.domain.Page;
import com.ombillah.ecom4j.domain.Product;

import com.ombillah.ecom4j.domain.ProductRating;
import com.ombillah.ecom4j.domain.ProductSpecificationMap;

/**
 * DAO for PRODUCT table operations.
 * extends common CRUD operations from BaseDAOHibernate
 * @author Oussama M Billah
 *
 */
@Repository("productDAO")
public class ProductDAOHibernate extends BaseDAOHibernate<Product> implements ProductDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<Product> searchForProduct(String keyword) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.createAlias("category","category");
		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("make", keyword))
				.add(Restrictions.like("model", keyword))
				.add(Restrictions.like("name", keyword))
				.add(Restrictions.like("shortDescriptionHtml", keyword))
				.add(Restrictions.like("category.categoryName", keyword)));
		
		List<Product> productList = criteria.list();
		return productList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getFeaturedProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("homePageDisplay", true));
		criteria.addOrder(Order.desc("customerReviewCount"));
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
	public Map<String, Integer> getManufacturerList(String categoryId) {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		Query query = getSession().getNamedQuery("getProductBrands");
		query.setString("categoryId", categoryId);
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
	public Map<String, Integer> getProductCategories(String categoryId) {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		Query query = getSession().getNamedQuery("getProductCategories");
		query.setString("categoryId", categoryId);
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
	public Map<String, Integer> getProductPriceRange(String categoryId) {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		Query query = getSession().getNamedQuery("getProductPriceRange");
		query.setString("categoryId", categoryId);
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
	public List<Product> getProducts(Page currentPage, Integer startIndex, Integer pageSize) {
		Map<String, String[]> catalogFilters = currentPage.getCatalogFilters();
		Criteria criteria = getSession().createCriteria(Product.class);
		setProductFiltersCriteria(catalogFilters, criteria, currentPage.isParentCategory());
		if(StringUtils.isNotBlank(currentPage.getSearchKeyword())) {
			createSearchCriteria(currentPage, criteria);
		}
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(startIndex);
		
		addSortingCriteria(currentPage, criteria);
		List<Product> productList = criteria.list();
		return productList;
	}

	private void createSearchCriteria(Page currentPage, Criteria criteria) {
		String keyword = currentPage.getSearchKeyword();
		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("make", keyword))
				.add(Restrictions.like("model", keyword))
				.add(Restrictions.like("name", keyword))
				.add(Restrictions.like("shortDescriptionHtml", keyword))
				.add(Restrictions.like("category.categoryName", keyword)));
	}

	private void setProductFiltersCriteria(Map<String, String[]> catalogFilters, Criteria criteria, boolean isParentCategory) {
		for(String filterName : catalogFilters.keySet()) {
			String[] filterValues = catalogFilters.get(filterName);
			if(StringUtils.equals(filterValues[0], "all")) {
				continue;
			}
			if(StringUtils.equals(filterName, "price")) {
				createPricingCriteria(criteria, filterValues);
			} else if(StringUtils.equals(filterName, "category")){
				criteria.createAlias("category","category");
				if(isParentCategory) {
					criteria.add(Restrictions.disjunction()
					        .add(Restrictions.in("category.categoryId", filterValues))
					        .add(Restrictions.in("category.parentCategory.categoryId", filterValues)));
				} else {
					criteria.add(Restrictions.in("category.categoryId", filterValues));
				}
				      
			} else {
				criteria.add(Restrictions.in(filterName, filterValues));
			}
		}
	}

	private void addSortingCriteria(Page currentPage, Criteria criteria) {
		
		String sortField = currentPage.getSortBy();
		if(currentPage.isSortAsc()) {
			criteria.addOrder(Order.asc(sortField));
		} else {
			criteria.addOrder(Order.desc(sortField));
		}
	}
	

	private void createPricingCriteria(Criteria criteria, String[] filterValues) {
		Disjunction disj = Restrictions.disjunction();
		for(String priceRange : filterValues) {
			String[] priceRangeArray = priceRange.split(" - ");
			Float lowerLimit = Float.valueOf(priceRangeArray[0]);
			Float upperLimit = Float.valueOf(priceRangeArray[1]);
			disj.add(Restrictions.between("salePrice", lowerLimit , upperLimit));
		}
		criteria.add(disj);
	}
	
	public Integer getProductsCount(Page currentPage) {
		Criteria criteria = getSession().createCriteria(Product.class);
		Map<String, String[]> catalogFilters = currentPage.getCatalogFilters();
		boolean isParentCategory = currentPage.isParentCategory();
		setProductFiltersCriteria(catalogFilters, criteria, isParentCategory);
		if(StringUtils.isNotBlank(currentPage.getSearchKeyword())) {
			createSearchCriteria(currentPage, criteria);
		}
		Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count;	
	}

	
	public void createProductReview(ProductRating rating) {
		getSession().save(rating);
	}


}
