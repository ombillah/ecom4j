package com.ombillah.ecom4j.webapp.springmvc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.service.CustomerService;
import com.ombillah.ecom4j.utils.Constants;
import com.ombillah.ecom4j.webapp.security.SpringSecurityUtils;
import com.ombillah.ecom4j.webapp.springmvc.validator.SpringValidatorUtils;


/**
 * Controller to allow access to My account page.
 * @author Oussama M Billah.
 *
 */
@Controller
@SessionAttributes({"states", "customer", "reCaptcha"})
public class MyAccountController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ReCaptcha reCaptcha;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Resource(name="states")
	private List<String> states;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	
	@RequestMapping(value = "/myaccount.do", method = RequestMethod.GET)
	public String displayMyAccountForm(ModelMap map) {
		
		Customer currentUser = SpringSecurityUtils.getAuthenticatedUser();
		Customer customer = new Customer();
		// cloning the object so security user doesn't get changed.
		BeanUtils.copyProperties(currentUser, customer);
		
		map.put("states", this.states);
		map.put("reCaptcha", reCaptcha.createRecaptchaHtml(null, "white", null));
		map.put("customer", customer);

		return "myaccount";
	}
	
	@RequestMapping(value = "/updateprofile.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<String>> updateProfile(
			@ModelAttribute Customer customer,
			Errors errors) {
		
		Map<String, List<String>> responseMap = new HashMap<String, List<String>>();
		List<String> responseList = new ArrayList<String>();
		
		Locale locale = LocaleContextHolder.getLocale(); 
		Customer currentUser = SpringSecurityUtils.getAuthenticatedUser();
		Customer backupCopy = new Customer();
		// cloning the object in case we have to restore security object back.
		BeanUtils.copyProperties(currentUser, backupCopy);
		
		validateProfileUpdateForm(errors);
		if (errors.hasErrors()) {
			for (ObjectError error : errors.getAllErrors()) {
				String errorCode = error.getCode();
				String errorMessage = messageSource.getMessage(errorCode, new Object[0], locale);
				responseList.add(errorMessage);
			}
			responseMap.put("validation", responseList);
			return responseMap;
		}
		currentUser.setEmailAddress(customer.getEmailAddress());
		currentUser.setOldEmailAddress(customer.getOldEmailAddress());
		currentUser.setFirstName(customer.getFirstName());
		currentUser.setLastName(customer.getLastName());
		currentUser.setAddress(customer.getAddress());
		currentUser.setAddress2(customer.getAddress2());
		currentUser.setCity(customer.getCity());
		currentUser.setState(customer.getState());
		currentUser.setZipCode(customer.getZipCode());
		currentUser.setPhoneNumber(customer.getPhoneNumber());

		customerService.updateCustomer(currentUser);
	
		String successMessage = messageSource.getMessage("customer.updateProfile.success", new Object[0], locale);
		responseList.add(successMessage);
		responseMap.put("success", responseList);
		return responseMap;
	}
	
	private void validateProfileUpdateForm(Errors errors) {
		String invalidFirstNameError = "NotEmpty.customer.firstName";
		String invalidLastNameError = "NotEmpty.customer.lastName";
		String invalidAddress = "NotEmpty.customer.address";
		String invalidCity = "NotEmpty.customer.city";
		String invalidState = "NotEmpty.customer.state";
		String invalidZipCode = "NotEmpty.customer.zipCode";
		
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "firstName", invalidFirstNameError);
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "lastName", invalidLastNameError);
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "address", invalidAddress);
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "city", invalidCity);
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "state", invalidState);
		SpringValidatorUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", invalidZipCode);
		
		String oldEmailAddress = errors.getFieldValue("oldEmailAddress").toString();
		String emailAddress = errors.getFieldValue("emailAddress").toString();
		
		if (!StringUtils.equals(oldEmailAddress, emailAddress)) {
			Customer customer = customerService.getCustomer(emailAddress);
			if (customer != null) {
				String duplicateEmailError = "Duplicate.customer.emailAddress";
				errors.rejectValue("emailAddress", duplicateEmailError);
			}
		}
	}
	
	@RequestMapping(value = "/updatepassword.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<String>> updatePassword(
			@ModelAttribute Customer customer, 
			Errors errors,
			HttpServletRequest request,
			@RequestParam("recaptcha_challenge_field") final String reCaptchaChallenge,  
			@RequestParam("recaptcha_response_field") final String reCaptchaResponse) {

		Map<String, List<String>> responseMap = new HashMap<String, List<String>>();
		List<String> responseList = new ArrayList<String>();
		
		Locale locale = LocaleContextHolder.getLocale(); 
		ReCaptchaResponse response = reCaptcha.checkAnswer(request.getRemoteAddr(), reCaptchaChallenge, reCaptchaResponse);
		
		validatePasswordForm(errors, response);

		
		if (errors.hasErrors()) {
			for (ObjectError error : errors.getAllErrors()) {
				String errorCode = error.getCode();
				String errorMessage = messageSource.getMessage(errorCode, new Object[0], locale);
				responseList.add(errorMessage);
			}
			responseMap.put("validation", responseList);
			return responseMap;
		}
		
		Customer currentUser = SpringSecurityUtils.getAuthenticatedUser();
		currentUser.setPassword(customer.getPassword());
		customerService.updatePassword(currentUser);
		
		String successMessage = messageSource.getMessage("customer.updatePassword.success", new Object[0], locale);
		responseList.add(successMessage);
		responseMap.put("success", responseList);
		return responseMap;
	}

	private void validatePasswordForm(Errors errors, ReCaptchaResponse response) {
		String passwordMatchError = "customer.updatePassword.notMatch";
		String invalidPasswordError = "Pattern.customer.password";
		String shortPasswordError = "Length.customer.password";
		String invalidCaptcha = "customer.captcha.invalid";
		
		SpringValidatorUtils.rejectIfInvalidPassword(errors, "password", invalidPasswordError);
		SpringValidatorUtils.rejectIfPasswordNotMatch(errors, "password", "confirmPassword", passwordMatchError);
		SpringValidatorUtils.rejectIfFieldTooShort(errors, "password", Constants.PASSWORD_MIN_LENGTH, shortPasswordError);
		
		String oldPassword = errors.getFieldValue("oldPassword").toString();
		String hashedPassword = passwordEncoder.encodePassword(oldPassword, null);
		Customer currentUser = SpringSecurityUtils.getAuthenticatedUser();
		
		if (!StringUtils.equals(hashedPassword, currentUser.getPassword())) {
			String invalidCurrentPasswordError = "customer.updatePassword.invalid";
			errors.rejectValue("oldPassword", invalidCurrentPasswordError);
		}
		
		if (!response.isValid()) {
			errors.rejectValue("captcha", invalidCaptcha);
		}
	}
	
	@RequestMapping(value = "/updatesecurityquestion.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List<String>> updateSecurityQuestion(
                  @ModelAttribute Customer customer,
                  Errors errors) {
		Map<String, List<String>> responseMap = new HashMap<String, List<String>>();
		List<String> responseList = new ArrayList<String>();
		  
		Locale locale = LocaleContextHolder.getLocale();
		
		validateSecurityForm(errors);
		  
		if (errors.hasErrors()) {
		    for (ObjectError error : errors.getAllErrors()) {
		          String errorCode = error.getCode();
		          String errorMessage = messageSource.getMessage(errorCode, new Object[0], locale);
		          responseList.add(errorMessage);
		    }
		    responseMap.put("validation", responseList);
		    return responseMap;
		}
		Customer currentUser = SpringSecurityUtils.getAuthenticatedUser();
		
		currentUser.setSecretAnswer(customer.getSecretAnswer());
		currentUser.setSecretQuestion(customer.getSecretQuestion());
		  
		customerService.updateSecretAnswer(currentUser);
		  
		String successMessage = messageSource.getMessage("customer.updateSecretAnswer.success", new Object[0], locale);
		responseList.add(successMessage);
		responseMap.put("success", responseList);
		return responseMap;
    }

    private void validateSecurityForm(Errors errors) {
        String emptySecurityAnswerField = "NotEmpty.customer.secretAnswer";
        SpringValidatorUtils.rejectIfEmpty(errors, "secretAnswer", emptySecurityAnswerField);
    }
    
	/**
	 * setter for mocking purposes.
	 * @param customerServiceMock
	 */
	public void setCustomerService(CustomerService customerServiceMock) {
		this.customerService = customerServiceMock;
	}
	
	/**
	 * setter for mocking purposes.
	 * @param recaptchaMock
	 */
	public void setReCaptcha(ReCaptcha recaptchaMock) {
		this.reCaptcha = recaptchaMock;
		
	}
	
	/**
	 * setter for mocking purposes.
	 * @param recaptchaMock
	 */
	public void setStates(List<String> statesMock) {
		this.states = statesMock;
	}
	
	/**
	 * Setter for mocking purpose
	 * @param messageSource
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/**
	 * Setter for mocking purpose
	 * @param passwordEncoderMock
	 */
	public void setPasswordEncorder(Md5PasswordEncoder passwordEncoderMock) {
		this.passwordEncoder = passwordEncoderMock;
	}

}
