package com.ombillah.ecom4j.webapp.springmvc;

import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ombillah.ecom4j.service.CustomerService;
import com.ombillah.ecom4j.service.OrderService;
import com.ombillah.ecom4j.service.ProductService;

/**
 * Abstract class to have basic setup and configuration files inject.
 * @author Oussama M Billah
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext-webapp.xml"})
public abstract class AbstractControllerTest {

	protected ProductService productServiceMock;
	protected OrderService orderServiceNock;
	protected CustomerService customerServiceMock;

	@Before
	public void setUp() throws Exception {
		productServiceMock = createMock(ProductService.class);
		orderServiceNock = createMock(OrderService.class);
		customerServiceMock = createMock(CustomerService.class);
		
	}
}
