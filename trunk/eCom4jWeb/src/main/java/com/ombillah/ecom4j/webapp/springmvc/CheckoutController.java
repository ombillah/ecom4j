package com.ombillah.ecom4j.webapp.springmvc;

import java.util.ArrayList;
import java.util.Arrays;

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

import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.domain.CustomerOrder;
import com.ombillah.ecom4j.domain.ShoppingCart;
import com.ombillah.ecom4j.service.CustomerService;
import com.ombillah.ecom4j.utils.Constants;
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
	
	@RequestMapping(value = "checkout-login.do",  method = RequestMethod.GET)
	public String checkout(@ModelAttribute("customer") Customer customer) {
		
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
			HttpServletRequest request) {
		
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
	    
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", invalidEmailAddressError);
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "confirmEmailAddress", invalidConfirmEmailAddressError);
		SpringValidatorUtils.rejectIfConfirmFieldNotMatch(errors, "emailAddress", "confirmEmailAddress", emailAddressNotMatch);
		SpringValidatorUtils.rejectIfInvalidPassword(errors, "password", invalidPasswordError);
		SpringValidatorUtils.rejectIfConfirmFieldNotMatch(errors, "password", "confirmPassword", passwordMatchError);
		SpringValidatorUtils.rejectIfFieldTooShort(errors, "password", Constants.PASSWORD_MIN_LENGTH, shortPasswordError);
	    SpringValidatorUtils.rejectIfEmpty(errors, "secretAnswer", emptySecurityAnswerField);
		
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
	public String checkout(@ModelAttribute("shoppingCart") ShoppingCart cart,
			@ModelAttribute("order") CustomerOrder order) {
		
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
}
