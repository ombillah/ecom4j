package com.ombillah.ecom4j.helper;

import java.util.ArrayList;
import java.util.List;

import com.ombillah.ecom4j.domain.ProductCategory;
import com.ombillah.ecom4j.remix.domain.Category;
import com.ombillah.ecom4j.remix.domain.Category.SubCategories;

/**
 * Helper class to perform mapping functionality.
 * @author Oussama M Billah
 *
 */
public final class ObjectMapper {
	
	/**
	 * making constructor private to avoid initializing 
	 * final class with static methods only.
	 */
	private ObjectMapper() {
		
	}
	
	public static List<ProductCategory> mapProductCategories(List<Category> categories) {
		List<ProductCategory> mappedCategories = new ArrayList<ProductCategory>();
		for (Category category : categories) {
			ProductCategory parentCategory = new ProductCategory();
			parentCategory.setCategoryId(category.getId());
			parentCategory.setCategoryName(category.getName());
			
			SubCategories subCategories = category.getSubCategories();
			List<ProductCategory> childCategories = new ArrayList<ProductCategory>();
			for (com.ombillah.ecom4j.remix.domain.ProductCategory childCat : subCategories.getCategory()) {
				ProductCategory childCategory = new ProductCategory();
				childCategory.setCategoryId(childCat.getId());
				childCategory.setCategoryName(childCat.getName());
				childCategory.setParentCategory(parentCategory);
				childCategories.add(childCategory);
			}
			mappedCategories.add(parentCategory);
			mappedCategories.addAll(childCategories);
		}
		
		return mappedCategories;
	}
}
