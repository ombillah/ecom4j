package com.ombillah.ecom4j.webapp.springmvc;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import static org.easymock.EasyMock.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;

import com.ombillah.ecom4j.domain.Customer;

/**
 * Test class for the home controller.
 * @author Oussama M Billah
 *
 */
public class RegistrationControllerTest extends AbstractControllerTest{
	
	private RegistrationController registrationController;
	private MockMvc mockMvc;
	private ReCaptcha recaptchaMock;
	private AuthenticationManager authenticationManagerMock;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		registrationController = new RegistrationController();
		recaptchaMock = createMock(ReCaptcha.class);
		authenticationManagerMock = createMock(AuthenticationManager.class);
		
		registrationController.setCustomerService(customerServiceMock);
		registrationController.setReCaptcha(recaptchaMock);
		registrationController.setAuthenticationManager(authenticationManagerMock);
		
		mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
		
	}


	@Test
	public void showRegistrationPage() throws Exception {
		String recaptcha = "Test Captcha Text";
		expect(recaptchaMock.createRecaptchaHtml(null, "white", null)).andReturn(recaptcha);
		replay(recaptchaMock);
		
		List<String> states = new ArrayList<String>();
		registrationController.setStates(states);
		
		mockMvc.perform(get("/register.do"))
		  .andExpect(status().isOk())
		  .andExpect(view().name("register"))
		  .andExpect(model().size(3))
		  .andExpect(model().attribute("reCaptcha", recaptcha))
		  .andExpect(model().attribute("customer", new Customer()))
		  .andExpect(model().attribute("states", states));
		verify(recaptchaMock);
	}
	
	@Test
	public void submitRegistration() throws Exception {
		
		Customer customer = new Customer();
		customer.setAddress("4444 Main St");
		customer.setCity("Columbus");
		customer.setState("OH");
		customer.setZipCode("43224");
		customer.setConfirmEmailAddress("test@test.com");
		customer.setEmailAddress("test@test.com");
		customer.setConfirmPassword("Password1");
		customer.setPassword("Password1");
		customer.setFirstName("Test");
		customer.setLastName("Tester");
		customer.setSecretAnswer("answer");
		customer.setSecretQuestion("question");
		
		ReCaptchaResponse reCaptchaResponseMock = createMock(ReCaptchaResponse.class);
		expect(recaptchaMock.checkAnswer("127.0.0.1", "field1", "response")).andReturn(reCaptchaResponseMock);
		expect(reCaptchaResponseMock.isValid()).andReturn(true);
		
		customerServiceMock.register(customer);
		expectLastCall();
		
		replay(recaptchaMock, customerServiceMock, reCaptchaResponseMock);
		
		mockMvc.perform(post("/register.do")
				.param("recaptcha_challenge_field", "field1")
				.param("recaptcha_response_field", "response")
				.sessionAttr("customer", customer))
		  .andExpect(status().isOk())
		  .andExpect(view().name("redirect:/myaccount.do"));

		verify(recaptchaMock, customerServiceMock, reCaptchaResponseMock);

	}
}
