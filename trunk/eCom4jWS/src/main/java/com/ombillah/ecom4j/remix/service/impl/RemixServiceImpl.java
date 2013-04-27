package com.ombillah.ecom4j.remix.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ombillah.ecom4j.remix.domain.Categories;
import com.ombillah.ecom4j.remix.domain.Category;
import com.ombillah.ecom4j.remix.domain.Products;
import com.ombillah.ecom4j.remix.domain.Products.Product;
import com.ombillah.ecom4j.remix.domain.Reviews;
import com.ombillah.ecom4j.remix.domain.Reviews.Review;
import com.ombillah.ecom4j.remix.service.RemixService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *  Service to Interface to BestBuy Remix API 
 * @author Oussama M Billah
 *
 */
@Service("remixService")
public class RemixServiceImpl implements RemixService {
	
	private static final Logger LOGGER = Logger.getLogger(RemixServiceImpl.class);	

	@Value("${REMIX_SERVICE_PRODUCT_ENDPOINT}")
	private String remixServiceProductEndPoint;
	
	@Value("${REMIX_SERVICE_REVIEWS_ENDPOINT}")
	private String remixServiceReviewsEndPoint;
	
	@Value("${REMIX_SERVICE_CATEGORIES_ENDPOINT}")
	private String remixServiceCategoriesEndPoint;
	
	@Value("${REMIX_API_KEY}")
	private String apiKey;
	
	@Value("${PRODUCT_FIELDS}")
	private String fields;

	@Value("${TEST_CATEGORY_NAMES}")
	private String testCategories;
	
	@Value("${TEST_CATEGORY_IDS}")
	private String testCategoryIds;
	
	@Resource(name="restTemplate")
	private RestTemplate restTemplate;

	public List<Product> getRemixProducts(List<String> modelNumbers) {
		StringBuilder builder = new StringBuilder();
		for (String modelNumber : modelNumbers) {
			builder.append("'");
			builder.append(modelNumber);
			builder.append("',");
		}
		String models = builder.toString();
		
		String remixRequestUrl = String.format("%s(modelNumber in(%s))?apiKey={apiKey}&show={show}", remixServiceProductEndPoint, models);
		
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("apiKey", apiKey);
		vars.put("show", "all");
		List<Product> products = new ArrayList<Product>();
		try {
			Products result = restTemplate.getForObject(remixRequestUrl, Products.class, vars);
			products = result.getProduct();
		} catch (RestClientException e) {
			LOGGER.error("Exception in getRemixProducts()", e);
		}
		return products;
	}
	
	public List<Review> getRemixReviews(List<String> skuList) {
		StringBuilder builder = new StringBuilder();
		for (String sku : skuList) {
			builder.append(sku);
			builder.append(",");
		}
		String skus = builder.toString();
		
		String remixRequestUrl = String.format("%s(sku in(%s))?apiKey={apiKey}&show={show}", remixServiceReviewsEndPoint, skus);
		
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("apiKey", apiKey);
		vars.put("show", "all");
		List<Review> reviews = new ArrayList<Review>();
		try {
			Reviews result = restTemplate.getForObject(remixRequestUrl, Reviews.class, vars);
			reviews = result.getReview();
		} catch (RestClientException e) {
			LOGGER.error("Exception in getRemixReviews()", e);
		}
		return reviews;
	}
	
	public List<Category> getRemixCategories(Integer pageSize, Integer pageNumber) {
		String remixRequestUrl = String.format("%s(active=true&name in(%s))?pageSize={pageSize}&page={page}&apiKey={apiKey}&show={show}", 
				remixServiceCategoriesEndPoint, testCategories);
				
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("pageSize", pageSize.toString());
		vars.put("page", pageNumber.toString());
		vars.put("apiKey", apiKey);
		vars.put("apiKey", apiKey);
		vars.put("show", "all");
		
		List<Category> categories = new ArrayList<Category>();
		try {
			Categories result = restTemplate.getForObject(remixRequestUrl, Categories.class, vars);
			categories = result.getCategory();
		} catch (RestClientException e) {
			LOGGER.error("Exception in getRemixCategories()", e);
		}
		return categories;
	}
	
	public List<Product> getRemixProducts(Integer pageSize, Integer pageNumber) {
		String remixRequestUrl = String.format("%s(modelNumber='*'&active=true&categoryPath.id in(%s))" 
				+ "?pageSize={pageSize}&page={page}&apiKey={apiKey}&show={show}", 
				remixServiceProductEndPoint, testCategoryIds);

		Map<String, String> vars = new HashMap<String, String>();
		vars.put("pageSize", pageSize.toString());
		vars.put("page", pageNumber.toString());
		vars.put("apiKey", apiKey);
		vars.put("apiKey", apiKey);
		vars.put("show", fields);
		
		List<Product> products = new ArrayList<Product>();
		try {
			Products result = restTemplate.getForObject(remixRequestUrl, Products.class, vars);
			products = result.getProduct();
		} catch (RestClientException e) {
			LOGGER.error("Exception in getRemixProducts()", e);
		}
		return products;
	}
	
	public List<Product> getProductOfCategory(String make, String categoryId) {
		
		String remixRequestUrl = String.format("%s(manufacturer=%s&categoryPath.id=%s)?pageSize={pageSize}&page={page}&apiKey={apiKey}&show={show}", 
				remixServiceProductEndPoint, make, categoryId);
		
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("pageSize", "1");
		vars.put("page", "1");
		vars.put("apiKey", apiKey);
		vars.put("show", "all");
		List<Product> products = new ArrayList<Product>();
		try {
			Products result = restTemplate.getForObject(remixRequestUrl, Products.class, vars);
			products = result.getProduct();
		} catch (RestClientException e) {
			LOGGER.error("Exception in getRemixProducts()", e);
		}
		return products;
	}
	/**
	 * Setter for mocking purpose.
	 * @param restTemplateMock
	 */
	public void setRestTemplate(RestTemplate restTemplateMock) {
		this.restTemplate = restTemplateMock;
	}

	/**
	 * Setter for mocking purpose.
	 * @param apiKeyMock
	 */
	public void setApiKey(String apiKeyMock) {
		this.apiKey = apiKeyMock;
	}

	/**
	 * Setter for mocking purpose.
	 * @param remixServiceProductEndPointMock
	 */
	public void setRemixServiceProductEndPoint(String remixServiceProductEndPointMock) {
		this.remixServiceProductEndPoint = remixServiceProductEndPointMock;
	}
	
	/**
	 * Setter for mocking purpose.
	 * @param remixServiceProductEndPointMock
	 */
	public void setRemixServiceReviewsEndPoint(String remixServiceReviewsEndPointMock) {
		this.remixServiceReviewsEndPoint = remixServiceReviewsEndPointMock;
	}

	
	
}
