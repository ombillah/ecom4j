package com.ombillah.ecom4j.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
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

	
	@SuppressWarnings("unchecked")
	public List<ProductCategory> getParentCategories() {
		Criteria criteria = getSession().createCriteria(ProductCategory.class);
		criteria.add(Restrictions.isNull("parentCategory"));
		List<ProductCategory> categoryies = criteria.list();
		return categoryies;
	}

	@SuppressWarnings("rawtypes")
	public String[] getProductCategoryIds(String[] categoryNames) {
		String[] categoryIds = new String[categoryNames.length];
		Query query = getSession().getNamedQuery("getProductCategoryIds");
		query.setParameterList("categoryList", categoryNames);
		Iterator it = query.iterate();
		int i = 0;
		while (it.hasNext()) {  
			String categoryId = (String) it.next();  
			categoryIds[i] = categoryId;
			i++;
  		}  
		return categoryIds;
	}

}
