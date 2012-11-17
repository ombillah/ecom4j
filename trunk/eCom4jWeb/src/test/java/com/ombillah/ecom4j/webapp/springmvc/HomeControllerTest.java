package com.ombillah.ecom4j.webapp.springmvc;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;

import com.ombillah.ecom4j.domain.Product;

/**
 * Test class for the home controller.
 * @author Oussama M Billah
 *
 */
public class HomeControllerTest extends AbstractControllerTest{
	
	private HomeController homeController;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		homeController = new HomeController();
		
		homeController.setProductService(productServiceMock);
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}


	@Test
	public void getHomepgae() throws Exception  {
		
		List<Product> featured = new ArrayList<Product>();
		featured.add(new Product());
		featured.add(new Product());
		
		expect(productServiceMock.getFeaturedProducts()).andReturn(featured);
		replay(productServiceMock);
		
		mockMvc.perform(get("/home.do"))
		  .andExpect(status().isOk())
		  .andExpect(view().name("homePage"))
		  .andExpect(model().size(1))
		  .andExpect(model().attribute("featuredProducts", featured));
		
		verify(productServiceMock);

	}
}
