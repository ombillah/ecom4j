package com.ombillah.ecom4j.webapp.springmvc;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.service.CustomerService;

/**
 * Controller to handle Customer Registration.
 * @author Oussama M Billah.
 *
 */
@Controller
@SessionAttributes({"states", "customer", "reCaptcha"})
public class RegistrationController {
		
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ReCaptcha reCaptcha;
	@Resource(name="states")
	private List<String> states;
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String displayRegistrationForm(
			ModelMap map) {
		map.put("customer", new Customer());
		map.put("states", this.states);
		map.put("reCaptcha", reCaptcha.createRecaptchaHtml(null, "white", null));
		return "register";
	}
	
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String performRegistration(
			@Valid @ModelAttribute("customer") Customer customer,
			Errors errors,
			HttpServletRequest request,
			RedirectAttributes redirectAttrs,
			@RequestParam("recaptcha_challenge_field") final String reCaptchaChallenge,  
			@RequestParam("recaptcha_response_field") final String reCaptchaResponse) throws Exception {
		
		String userName = customer.getEmailAddress();
		String password = customer.getPassword();
		String secretAnswer = customer.getSecretAnswer();
		
		ReCaptchaResponse response = reCaptcha.checkAnswer(request.getRemoteAddr(), reCaptchaChallenge, reCaptchaResponse);
		if (!response.isValid()) {
			String invalidCaptcha = "customer.captcha.invalid";
			errors.rejectValue("captcha", invalidCaptcha);
		}
		
		if (errors.hasErrors()){
			return "register";
		}

		try{
			customerService.register(customer);
		} catch(DataIntegrityViolationException ex){
			customer.setSecretAnswer(secretAnswer); // restoring original value before hashing.
			customer.setConfirmEmailAddress("");
			String duplicateEmailError = "Duplicate.customer.emailAddress";
			errors.rejectValue("emailAddress", duplicateEmailError);
			return "register";
		}
		doAutoLogin(userName, password, request);
		redirectAttrs.addFlashAttribute("", ""); // adding this to prevent redirect from passing model attributes to url.
		return "redirect:/myaccount.do";
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
	
	/**
	 * setter method for mocking purpose.
	 * @param customerServiceMock
	 */
	public void setCustomerService(CustomerService customerServiceMock) {
		this.customerService = customerServiceMock;
	}
	
	/**
	 * setter method for mocking purpose.
	 * @param recaptchaMock
	 */
	public void setReCaptcha(ReCaptcha recaptchaMock) {
		this.reCaptcha = recaptchaMock;	
	}
	
	/**
	 * setter method for mocking purpose.
	 * @param statesMock
	 */
	public void setStates(List<String> statesMock) {
		this.states = statesMock;	
	}
	
	/**
	 * setter method for mocking purpose.
	 * @param authenticationManagerMock
	 */
	public void setAuthenticationManager(AuthenticationManager authenticationManagerMock) {
		this.authenticationManager = authenticationManagerMock;
	}
	
}
