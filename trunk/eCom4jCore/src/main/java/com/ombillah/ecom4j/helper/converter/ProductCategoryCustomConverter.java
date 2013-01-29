package com.ombillah.ecom4j.helper.converter;

import java.util.List;

import org.dozer.CustomConverter;

import com.ombillah.ecom4j.domain.ProductCategory;
import com.ombillah.ecom4j.remix.domain.Products.Product.CategoryPath;
import com.ombillah.ecom4j.remix.domain.Products.Product.CategoryPath.Category;
/**
 * Custom Dozer converter to convert Remix category.
 * @author Oussama M Billah
 *
 */
public class ProductCategoryCustomConverter implements CustomConverter {

	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, 
			Class<?> destinationClass,
			Class<?> sourceClass) {
		
		CategoryPath categoryPath = (CategoryPath) sourceFieldValue;
		List<Category> categories = categoryPath.getCategory();
		
		Category category = categories.get(1);
		if (categories.size() > 2) {
			category = categories.get(2);
		}
	
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryId(category.getId());
		productCategory.setCategoryName(category.getName());
		return productCategory;
	}

}
