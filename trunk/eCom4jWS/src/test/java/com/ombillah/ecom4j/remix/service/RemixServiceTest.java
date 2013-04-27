package com.ombillah.ecom4j.remix.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.ombillah.ecom4j.remix.domain.Products;
import com.ombillah.ecom4j.remix.domain.Reviews;
import com.ombillah.ecom4j.remix.service.impl.RemixServiceImpl;

public class RemixServiceTest {

	private RemixService remixService;
	private RestTemplate restTemplateMock;
	
	@Before
	public void Setup() {
		remixService = new RemixServiceImpl();
		restTemplateMock = createMock(RestTemplate.class);
		remixService.setRestTemplate(restTemplateMock);
		remixService.setApiKey("11112222aaaaaaaa");
		remixService.setRemixServiceProductEndPoint("http://localhost:8080/rest/products/");
		remixService.setRemixServiceReviewsEndPoint("http://localhost:8080/rest/reviews/");
	}

	@Test
	public void testgetProducts() {
		List<String> modelNumbers = new ArrayList<String>();
		modelNumbers.add("2000-2b22dx");
		Products products = new Products();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("apiKey", "11112222aaaaaaaa");
		vars.put("show", "all");
		String url = "http://localhost:8080/rest/products/(modelNumber in(2000-2b22dx,))?apiKey={apiKey}&show={show}";
		
		expect(restTemplateMock.getForObject(url, Products.class, vars)).andReturn(products);
		replay(restTemplateMock);
		remixService.getRemixProducts(modelNumbers);
		verify(restTemplateMock);
		
	}
	
	@Test
	public void testgetReviews() {
		List<String> skus = new ArrayList<String>();
		skus.add("11111");
		Reviews reviews = new Reviews();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("apiKey", "11112222aaaaaaaa");
		vars.put("show", "all");
		String url = "http://localhost:8080/rest/reviews/(sku in(11111,))?apiKey={apiKey}&show={show}";
		
		expect(restTemplateMock.getForObject(url, Reviews.class, vars)).andReturn(reviews);
		replay(restTemplateMock);
		remixService.getRemixReviews(skus);
		verify(restTemplateMock);
		
	}
	
}
