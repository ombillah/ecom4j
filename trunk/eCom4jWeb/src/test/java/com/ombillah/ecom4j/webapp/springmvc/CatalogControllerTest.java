package com.ombillah.ecom4j.webapp.springmvc;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import com.ombillah.ecom4j.domain.Page;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.pagination.PaginationHandler;
import com.ombillah.ecom4j.webapp.springmvc.viewbean.CatalogViewBean;

/**
 * Test class for the Catalog controller.
 * @author Oussama M Billah
 *
 */
public class CatalogControllerTest extends AbstractControllerTest {

	private CatalogController catalogController;
	private PaginationHandler paginationHandlerMock;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		paginationHandlerMock = createMock(PaginationHandler.class);
		catalogController = new CatalogController();
		catalogController.setProductService(productServiceMock);
		catalogController.setPaginationHandler(paginationHandlerMock);
		catalogController.setAvailablePageSizes("10,20");
		catalogController.setDefaultPageSize(10);
		mockMvc = MockMvcBuilders.standaloneSetup(catalogController).build();
	}


	@Test
	public void displayProductCatalog() throws Exception  {
		
		List<Product> products = new ArrayList<Product>();
		products.add(new Product());
		products.add(new Product());
		
		Map<String, Integer> brands = new HashMap<String, Integer>();
		brands.put("Apple", 5);
		
		Map<String, Integer> categories = new HashMap<String, Integer>();
		categories.put("Laptop", 3);
		
		Map<String, Integer> priceRanges = new HashMap<String, Integer>();
		priceRanges.put("$199.99 - $599.99", 3);
		
		List<Integer> availableSizes = new ArrayList<Integer>();
		availableSizes.add(10);
		availableSizes.add(20);
		
		CatalogViewBean viewBean = new CatalogViewBean();
		
		expect(paginationHandlerMock.getPagesCount(EasyMock.anyObject(Page.class))).andReturn(new Long(3));
		expect(paginationHandlerMock.getFirstPage(EasyMock.anyObject(Page.class))).andReturn(products);

		expect(productServiceMock.getManufacturerList()).andReturn(brands);
		expect(productServiceMock.getProductCategories()).andReturn(categories);
		expect(productServiceMock.getProductPriceRange()).andReturn(priceRanges);
		
		viewBean.setBrands(brands);
		viewBean.setCategories(categories);
		viewBean.setPriceRanges(priceRanges);
		
		replay(paginationHandlerMock);
		replay(productServiceMock);
		
		mockMvc.perform(get("/catalog.do")
				.sessionAttr("catalogViewBean", viewBean))
		  .andExpect(status().isOk())
		  .andExpect(view().name("catalog"))
		  .andExpect(model().size(1));
		
		verify(paginationHandlerMock, productServiceMock);
	}
	
	@Test
	public void addProductFilter() throws Exception {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product());
		products.add(new Product());
		
		Map<String, Integer> brands = new HashMap<String, Integer>();
		brands.put("Apple", 5);
		
		Map<String, Integer> categories = new HashMap<String, Integer>();
		categories.put("Laptop", 3);
		
		Map<String, Integer> priceRanges = new HashMap<String, Integer>();
		priceRanges.put("$199.99 - $599.99", 3);
		
		List<Integer> availableSizes = new ArrayList<Integer>();
		availableSizes.add(10);
		availableSizes.add(20);
		
		CatalogViewBean viewBean = new CatalogViewBean();
		
		expect(paginationHandlerMock.getPagesCount(EasyMock.anyObject(Page.class))).andReturn(new Long(3));
		expect(paginationHandlerMock.getFirstPage(EasyMock.anyObject(Page.class))).andReturn(products);

		expect(productServiceMock.getManufacturerList()).andReturn(brands);
		expect(productServiceMock.getProductCategories()).andReturn(categories);
		expect(productServiceMock.getProductPriceRange()).andReturn(priceRanges);
		
		viewBean.setBrands(brands);
		viewBean.setCategories(categories);
		viewBean.setPriceRanges(priceRanges);
		
		replay(paginationHandlerMock);
		replay(productServiceMock);
		
		mockMvc.perform(get("/catalog.do")
				.sessionAttr("catalogViewBean", viewBean))
		  .andExpect(status().isOk())
		  .andExpect(view().name("catalog"))
		  .andExpect(model().size(1));
		
		verify(paginationHandlerMock, productServiceMock);
	}

}
