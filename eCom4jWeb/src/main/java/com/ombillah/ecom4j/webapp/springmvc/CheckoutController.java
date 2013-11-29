package com.ombillah.ecom4j.webapp.springmvc;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.domain.CustomerOrder;
import com.ombillah.ecom4j.domain.ShoppingCart;
import com.ombillah.ecom4j.service.CustomerService;
import com.ombillah.ecom4j.service.OrderService;
import com.ombillah.ecom4j.utils.Constants;
import com.ombillah.ecom4j.webapp.security.SpringSecurityUtils;
import com.ombillah.ecom4j.webapp.springmvc.validator.SpringValidatorUtils;

/**
 * Controller to handle checkout functionality.
 * @author Oussama M Billah.
 *
 */
@Controller
@SessionAttributes("shoppingCart")
public class CheckoutController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	@Resource(name="states")
	private List<String> states;
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	@RequestMapping(value = "checkout-login.do",  method = RequestMethod.GET)
	public String checkout(@ModelAttribute("customer") Customer customer,
			@ModelAttribute("states") ArrayList<String> states) {
		states.addAll(this.states);
		return "checkout-login";
	}
	
	@RequestMapping(value = "checkout-guest.do",  method = RequestMethod.POST)
	public String checkoutAsGuest(
			@ModelAttribute("customer") Customer customer,
			Errors errors,
			HttpServletRequest request) {
		
		String invalidEmailAddressError = "NotEmpty.customer.emailAddress";		
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", invalidEmailAddressError);
		
		if (errors.hasErrors()) {
		    return "checkout-login";
		}
		loginAsGuest(request, customer.getEmailAddress());
		
		return "redirect:checkout-payment.do";
	}
	
	@RequestMapping(value = "checkout-register.do",  method = RequestMethod.POST)
	public String checkoutRegister(
			@ModelAttribute("customer") Customer customer,
			Errors errors,
			@ModelAttribute("states") ArrayList<String> states,
			HttpServletRequest request) {
		
		states.addAll(this.states);
		validateCheckoutRegistrationForm(errors);
		
		if (errors.hasErrors()) {
		   
		    return "checkout-login";
		}
		
		String secretAnswer = customer.getSecretAnswer();	
		String userName = customer.getEmailAddress();
		String password = customer.getPassword();
	
		try{
			customerService.register(customer);
		} catch(DataIntegrityViolationException ex){
			customer.setSecretAnswer(secretAnswer); // restoring original value before hashing.
			customer.setConfirmEmailAddress("");
			String duplicateEmailError = "Duplicate.customer.emailAddress";
			errors.rejectValue("emailAddress", duplicateEmailError);
			return "checkout-login";
		}
		
		doAutoLogin(userName, password, request);
		
		return "redirect:checkout-payment.do";
	}
	
	private void validateCheckoutRegistrationForm(Errors errors) {
		
		String invalidEmailAddressError = "NotEmpty.customer.emailAddress";		
		String invalidConfirmEmailAddressError = "NotEmpty.customer.confirmEmailAddress";
		String emailAddressNotMatch = "customer.emailAdress.notMatch";
		String passwordMatchError = "customer.updatePassword.notMatch";
		String invalidPasswordError = "Pattern.customer.password";
		String shortPasswordError = "Length.customer.password";
		String emptySecurityAnswerField = "NotEmpty.customer.secretAnswer";
		String emptyFirstNameField = "NotEmpty.customer.firstName";
		String emptyLastNameField = "NotEmpty.customer.lastName";
		String emptyAddressField = "NotEmpty.customer.address";
		String emptyCityField = "NotEmpty.customer.city";
		String emptyStateField = "NotEmpty.customer.state";
		String emptyZipField = "NotEmpty.customer.zipCode";
		
	    
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", invalidEmailAddressError);
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "confirmEmailAddress", invalidConfirmEmailAddressError);
		SpringValidatorUtils.rejectIfConfirmFieldNotMatch(errors, "emailAddress", "confirmEmailAddress", emailAddressNotMatch);
		SpringValidatorUtils.rejectIfInvalidPassword(errors, "password", invalidPasswordError);
		SpringValidatorUtils.rejectIfConfirmFieldNotMatch(errors, "password", "confirmPassword", passwordMatchError);
		SpringValidatorUtils.rejectIfFieldTooShort(errors, "password", Constants.PASSWORD_MIN_LENGTH, shortPasswordError);
	    SpringValidatorUtils.rejectIfEmpty(errors, "secretAnswer", emptySecurityAnswerField);
	    SpringValidatorUtils.rejectIfEmpty(errors, "firstName", emptyFirstNameField);
	    SpringValidatorUtils.rejectIfEmpty(errors, "lastName", emptyLastNameField);
	    SpringValidatorUtils.rejectIfEmpty(errors, "address", emptyAddressField);
	    SpringValidatorUtils.rejectIfEmpty(errors, "city", emptyCityField);
	    SpringValidatorUtils.rejectIfEmpty(errors, "state", emptyStateField);
	    SpringValidatorUtils.rejectIfEmpty(errors, "zipCode", emptyZipField);
		
		String emailAddress = errors.getFieldValue("emailAddress").toString();
		Customer customer = customerService.getCustomer(emailAddress);
		if (customer != null) {
			String duplicateEmailError = "Duplicate.customer.emailAddress";
			errors.rejectValue("emailAddress", duplicateEmailError);
		}
		
	}
	
	private void doAutoLogin(String username, String password, HttpServletRequest request) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);

	    token.setDetails(new WebAuthenticationDetails(request));
	    Authentication authenticatedUser = authenticationManager.authenticate(token);

	    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	    request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
	            SecurityContextHolder.getContext());
	}

	
	@RequestMapping(value = "checkout-payment.do",  method = RequestMethod.GET)
	public String checkout(
			@ModelAttribute("states") ArrayList<String> states,
			@ModelAttribute("order") CustomerOrder order) {
		
		states.addAll(this.states);
		
		return "checkout-payment";
	}
	
	private void loginAsGuest(HttpServletRequest request, String emailAddress) {

		Authentication guestUser = new UsernamePasswordAuthenticationToken(
				new User(emailAddress,
	                      "dummyPass",
	                      true,
	                      true,
	                      true,
	                      true,
	                      new ArrayList<GrantedAuthority>((Arrays.asList(new SimpleGrantedAuthority("ROLE_GUEST"))))
	                      ),
				"dummyPass", 
	    		new ArrayList<GrantedAuthority>((Arrays.asList(new SimpleGrantedAuthority("ROLE_GUEST")))));

		SecurityContextHolder.getContext().setAuthentication(guestUser);
	    request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
	            SecurityContextHolder.getContext());
	}
	
	@RequestMapping(value = "checkout-confirm.do",  method = RequestMethod.POST)
	public String confirmCheckout(
			@ModelAttribute("order") CustomerOrder order,
			Errors errors,
			@ModelAttribute("shoppingCart") ShoppingCart cart,
			@ModelAttribute("states") ArrayList<String> states,
			Principal principal,
			SessionStatus status) throws Exception {
		
		order.setCart(cart);
		states.addAll(this.states);
		validateCheckoutConfirmationForm(errors);
		
		if (errors.hasErrors()) {
		    return "checkout-payment";
		}
		
	    String userName = principal.getName();
	    Customer customer = SpringSecurityUtils.getAuthenticatedUser();
	    updateCustomerInfoIfNewRegistration(order, customer);
		Long orderId = orderService.checkout(cart, userName);
		order.setOrderID(orderId);
		status.setComplete();
		
		return "checkout-confirm";
	}

	private void updateCustomerInfoIfNewRegistration(CustomerOrder order, Customer customer) {
		if(customer.getFirstName() == null) {
	    	customer.setFirstName(order.getBillingAddress().getFirstName());
	    	customer.setLastName(order.getBillingAddress().getLastName());
	    	customer.setAddress(order.getBillingAddress().getStreetAddress());
	    	customer.setAddress2(order.getBillingAddress().getAddressLine2());
	    	customer.setCity(order.getBillingAddress().getCity());
	    	customer.setState(order.getBillingAddress().getState());
	    	customer.setZipCode(order.getBillingAddress().getZipCode());
	    	customer.setPhoneNumber(order.getBillingAddress().getTelephone());
	    	customerService.updateCustomer(customer);
	    }
	}

	private void validateCheckoutConfirmationForm(Errors errors) {
	    
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.firstName", "NotEmpty.customer.firstName");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.lastName", "NotEmpty.customer.lastName");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.streetAddress", "NotEmpty.customer.address");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.city", "NotEmpty.customer.city");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.state", "NotEmpty.customer.state");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.zipCode", "NotEmpty.customer.zipCode");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.emailAddress", "NotEmpty.customer.emailAddress");

		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.firstName", "NotEmpty.customer.firstName");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.lastName", "NotEmpty.customer.lastName");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.streetAddress", "NotEmpty.customer.address");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.city", "NotEmpty.customer.city");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.state", "NotEmpty.customer.state");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.zipCode", "NotEmpty.customer.zipCode");
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.emailAddress", "NotEmpty.customer.emailAddress");
		
		
	}
}
