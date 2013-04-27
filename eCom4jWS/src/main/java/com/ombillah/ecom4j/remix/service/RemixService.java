package com.ombillah.ecom4j.remix.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.ombillah.ecom4j.remix.domain.Category;
import com.ombillah.ecom4j.remix.domain.Products.Product;
import com.ombillah.ecom4j.remix.domain.Reviews.Review;

/**
 *  Service to Interface to BestBuy Remix API 
 * @author Oussama M Billah
 *
 */
public interface RemixService {
	
	public List<Product> getRemixProducts(List<String> modelNumbers);
	
	public List<Review> getRemixReviews(List<String> skuList);
	
	public List<Category> getRemixCategories(Integer pageSize, Integer pageNumber);
	
	public List<Product> getRemixProducts(Integer pageSize, Integer pageNumber);

	public void setRestTemplate(RestTemplate restTemplateMock);

	public void setApiKey(String apiKey);
	
	public void setRemixServiceProductEndPoint(String remixServiceProductEndPointMock);
	
	public void setRemixServiceReviewsEndPoint(String remixServiceReviewsEndPointMock);

	public List<Product> getProductOfCategory(String make, String categoryId);
}
