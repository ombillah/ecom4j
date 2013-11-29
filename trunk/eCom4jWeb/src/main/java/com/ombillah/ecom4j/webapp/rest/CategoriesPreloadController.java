package com.ombillah.ecom4j.webapp.rest;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ProductCategory;
import com.ombillah.ecom4j.helper.ObjectMapper;
import com.ombillah.ecom4j.remix.domain.Category;
import com.ombillah.ecom4j.remix.service.RemixService;
import com.ombillah.ecom4j.service.ProductCategoryService;
import com.ombillah.ecom4j.service.ProductService;

/**
 * Rest Service to Preload categories from Remix Service to Database.
 * @author Oussama.
 *
 */
@Controller
@RequestMapping(value="/rest/admin")
public class CategoriesPreloadController {
	
	@Resource(name="remixService")
	private RemixService remixService;
	
	@Resource(name="productService")
	private ProductService productService;
	
	@Resource(name="productCategoryService")
	private ProductCategoryService productCategoryService;
	
	@Resource(name="mapper")
	private Mapper mapper;
	
	private static final Log LOG = LogFactory.getLog(CategoriesPreloadController.class);
	
	private static final Integer BATCH_SIZE = 100;
	private static final Integer BATCH_WAIT_TIME = 250;
	
	@RequestMapping(value="categoriesPreload", method = RequestMethod.GET)
	@ResponseBody
	public String preloadProductCategories() {	
		
		long start = System.currentTimeMillis();
		boolean done = false;
		Integer pageNumber = 1;
		List<Category> categories;
		
		while (!done) {
			categories = remixService.getRemixCategories(BATCH_SIZE, pageNumber);
			List<ProductCategory> productCategories = ObjectMapper.mapProductCategories(categories);
			productCategoryService.createProductCategories(productCategories);
			pageNumber++;
			if (CollectionUtils.isEmpty(categories)) {
				done = true;
			}
			pause();
		}
		Integer count = productCategoryService.getCount();
		long end = System.currentTimeMillis();
		String message = String.format("Processed %d rows in %d ms", count, (end - start));
		return message;
	}

	@RequestMapping(value="productPreload", method = RequestMethod.GET)
	@ResponseBody
	public String preloadProducts() {	
		
		long start = System.currentTimeMillis();
		boolean done = false;
		Integer pageNumber = 1;
		List<com.ombillah.ecom4j.remix.domain.Products.Product> remixProducts;
		
		while (!done) {
			List<Product> products = new ArrayList<Product>();
			remixProducts = remixService.getRemixProducts(BATCH_SIZE, pageNumber);
			for(com.ombillah.ecom4j.remix.domain.Products.Product remixProduct : remixProducts) {
				if(remixProduct.getCategoryPath().getCategory().isEmpty()) {
					continue;
				}
				Product product = mapper.map(remixProduct, Product.class);
				products.add(product);
			}
			productService.createProducts(products);
			pageNumber++;
			if (CollectionUtils.isEmpty(remixProducts)) {
				done = true;
			}
			pause();
		}

		Integer count = productService.getCount();
		long end = System.currentTimeMillis();
		String message = String.format("Processed %d rows in %d ms", count, (end - start));
		return message;
	}

	private void pause() {
		try {
			Thread.sleep(BATCH_WAIT_TIME);
		} catch (InterruptedException e) {
			LOG.error("Exception in Thread sleep", e);
		}
	}
}