package com.ombillah.ecom4j.webapp.springmvc;

import static org.powermock.api.easymock.PowerMock.*;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.anyObject;
import static org.junit.Assert.*;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.webapp.security.SpringSecurityUtils;

/**
 * Test class for the myAccount controller.
 * @author Oussama M Billah
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SpringSecurityUtils.class)
public class MyAccountControllerTest extends AbstractControllerTest {
	
	private MyAccountController myAccountController;
	private MockMvc mockMvc;
	private ReCaptcha recaptchaMock;
	private MessageSource messageSourceMock;
	private Md5PasswordEncoder passwordEncoderMock;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		myAccountController = new MyAccountController();
		recaptchaMock = createMock(ReCaptcha.class);
		messageSourceMock = createMock(MessageSource.class);
		passwordEncoderMock = createMock(Md5PasswordEncoder.class);
		
		myAccountController.setCustomerService(customerServiceMock);
		myAccountController.setReCaptcha(recaptchaMock);
		myAccountController.setMessageSource(messageSourceMock);
		myAccountController.setPasswordEncorder(passwordEncoderMock);
		
		mockMvc = MockMvcBuilders.standaloneSetup(myAccountController).build();
		
	}


	@Test
	public void showMyAccountPage() throws Exception {
		
		Customer customer = new Customer();
		mockStatic(SpringSecurityUtils.class);
		expect(SpringSecurityUtils.getAuthenticatedUser()).andReturn(customer);
		List<String> states = new ArrayList<String>();
		replayAll();
		String recaptcha = "Test Captcha Text";
		expect(recaptchaMock.createRecaptchaHtml(null, "white", null)).andReturn(recaptcha);
		replay(recaptchaMock);
		
		myAccountController.setStates(states);
		mockMvc.perform(get("/myaccount.do"))
		  .andExpect(status().isOk())
		  .andExpect(view().name("myaccount"))
		  .andExpect(model().size(3))
		  .andExpect(model().attribute("reCaptcha", recaptcha))
		  .andExpect(model().attribute("customer", customer))
		  .andExpect(model().attribute("states", states));
		
		verifyAll();
		verify(recaptchaMock);
	}
	
	@Test
	public void updateProfile() throws Exception {
		
		Customer customer = new Customer();
		customer.setAddress("1234 Karl Rd");
		customer.setAddress2(" APT B");
		customer.setCity("Columbus");
		customer.setState("OH");
		customer.setZipCode("43224");
		customer.setFirstName("Oussama");
		customer.setLastName("Billah");
		customer.setEmailAddress("test@test.com");
		customer.setOldEmailAddress("test@test.com");
		
		Customer customer2 = new Customer();
		customer2.setEmailAddress("test@test.com");
		customer2.setPassword("password");
		
		mockStatic(SpringSecurityUtils.class);
		expect(SpringSecurityUtils.getAuthenticatedUser()).andReturn(customer2);

		replayAll();
		
		customerServiceMock.updateCustomer(customer2);
		expectLastCall();
		
		Locale locale = Locale.ENGLISH;
		expect(messageSourceMock.getMessage(eq("customer.updateProfile.success"), anyObject(Object[].class), eq(locale))).andReturn("Personal information updated successfully.");
		replay(messageSourceMock, customerServiceMock);
		
		List<String> result = new ArrayList<String>();
		result.add("Personal information updated successfully.");
		
		mockMvc.perform(post("/updateprofile.do")
				.sessionAttr("customer", customer))
		.andExpect(jsonPath("$.success").value(result));
		
		verifyAll();
		verify(customerServiceMock, messageSourceMock);
		
		assertEquals(customer2.getAddress(), "1234 Karl Rd");
	}
	
	@Test
	public void updatePassword() throws Exception {
		
		Customer customer = new Customer();
		
		customer.setPassword("newPassword1");
		customer.setConfirmPassword("newPassword1");
		customer.setOldPassword("password");
		
		Customer customer2 = new Customer();
		customer2.setPassword("hf33hjdkd3333");
		
		ReCaptchaResponse reCaptchaResponseMock = createMock(ReCaptchaResponse.class);
		expect(recaptchaMock.checkAnswer("127.0.0.1", "field1", "response")).andReturn(reCaptchaResponseMock);
		expect(reCaptchaResponseMock.isValid()).andReturn(true);
		
		mockStatic(SpringSecurityUtils.class);
		expect(SpringSecurityUtils.getAuthenticatedUser()).andReturn(customer2).times(2);
		replayAll();
		
		 
		expect(passwordEncoderMock.encodePassword("password", null)).andReturn("hf33hjdkd3333");
		
		customerServiceMock.updatePassword(customer2);
		expectLastCall();
		
		Locale locale = Locale.ENGLISH;
		expect(messageSourceMock.getMessage(eq("customer.updatePassword.success"), anyObject(Object[].class), eq(locale))).andReturn("Password updated successfully.");
		replay(messageSourceMock, customerServiceMock, recaptchaMock, reCaptchaResponseMock, passwordEncoderMock);
		
		List<String> result = new ArrayList<String>();
		result.add("Password updated successfully.");
		
		mockMvc.perform(post("/updatepassword.do")
				.param("recaptcha_challenge_field", "field1")
				.param("recaptcha_response_field", "response")
				.sessionAttr("customer", customer))
		.andExpect(jsonPath("$.success").value(result));
		
		verifyAll();
		verify(messageSourceMock, customerServiceMock, recaptchaMock, reCaptchaResponseMock, passwordEncoderMock);
	}
	
	@Test
    public void updateSecretAnswer() throws Exception {
          
           Customer customer = new Customer();
          
           customer.setSecretAnswer("my answer");

           Customer customer2 = new Customer();
           customer2.setSecretAnswer("old answer");
   
           mockStatic(SpringSecurityUtils.class);
           expect(SpringSecurityUtils.getAuthenticatedUser()).andReturn(customer2);
           replayAll();
          
           customerServiceMock.updateSecretAnswer(customer2);
           expectLastCall();
          
           Locale locale = Locale.ENGLISH;
           expect(messageSourceMock.getMessage(eq("customer.updateSecretAnswer.success"), anyObject(Object[].class), eq(locale))).andReturn("Secret answer updated successfully.");
           replay(messageSourceMock, customerServiceMock, recaptchaMock, passwordEncoderMock);
          
           List<String> result = new ArrayList<String>();
           result.add("Secret answer updated successfully.");
          
           mockMvc.perform(post("/updatesecurityquestion.do")
                        .sessionAttr("customer", customer))
           .andExpect(jsonPath("$.success").value(result));
          
           verifyAll();
           verify(messageSourceMock, customerServiceMock, recaptchaMock, passwordEncoderMock);
    }
   
}
