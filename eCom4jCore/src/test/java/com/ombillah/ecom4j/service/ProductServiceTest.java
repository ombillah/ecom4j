package com.ombillah.ecom4j.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.*;

import com.ombillah.ecom4j.dao.ProductDAO;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.exception.ProductNotFoundException;
import com.ombillah.ecom4j.service.impl.ProductServiceImpl;

/**
 * A class that test the all the Product BO productService.
 * 
 * @author Oussama M Billah
 */
public class ProductServiceTest {

	private ProductService productService;
	private ProductDAO productDaoMock;
	
	@Before
	public void Setup() {
		productService = new ProductServiceImpl();
		productDaoMock = createMock(ProductDAO.class);
		productService.setProductDAO(productDaoMock);
	}

	@Test
	public void testGetProductDetails() throws Exception {
		Product product = createMock(Product.class);
		
        expect(productDaoMock.getObject(Product.class, 11111L)).andReturn(product);
		replay(productDaoMock);
		assertEquals(product, productService.getProductDetails(11111L));
		verify(productDaoMock);
	}

	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetProducts() throws Exception {
		List<Product> list = createMock(List.class);
		expect(productDaoMock.getObjects(Product.class)).andReturn(list);
		replay(productDaoMock);
		assertEquals(list, productService.getProducts());
		verify(productDaoMock);

	}

	@SuppressWarnings("unchecked")
	@Test (expected= ProductNotFoundException.class)
	public void testSearchForProducts() throws Exception {
		// test for every description that contains the word "iphone5"
		List<Product> product = createMock(List.class);
		
        expect(productDaoMock.searchForProduct("iPhone5")).andReturn(product);
		replay(productDaoMock);
		assertEquals(product, productService.searchForProducts("iPhone5"));
		verify(productDaoMock);
		
		reset(productDaoMock);

		// test for no result.
		expect(productDaoMock.searchForProduct("Invalid")).andReturn(null);
		replay(productDaoMock);
		try {
			productService.searchForProducts("Invalid");
		} finally {
			verify(productDaoMock);
		}
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		
		List<String> list = new ArrayList<String>();
		list.add("HP");
		list.add("Apple");
		
		expect(productDaoMock.getManufacturerList()).andReturn(list);		
		replay(productDaoMock);
		assertEquals(list, productService.getManufacturerList());
		verify(productDaoMock);

	}
	
	@Test
	public void testGetManufacturerList() throws Exception {
		
		Product product = createMock(Product.class);
		productDaoMock.updateObject(product);
		expectLastCall();
		replay(productDaoMock);
		productService.updateProduct(product);
		verify(productDaoMock);

	}
	
	@Test
	public void testGetFeaturedProducts() throws Exception {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product());
		
        expect(productDaoMock.getFeaturedProducts()).andReturn(products);
		replay(productDaoMock);
		assertEquals(products, productService.getFeaturedProducts());
		verify(productDaoMock);
	}
}
