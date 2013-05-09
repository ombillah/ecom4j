package com.ombillah.ecom4j.webapp.springmvc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.ombillah.ecom4j.utils.Constants;

/**
 * Custom validator class that extends base spring validator
 * @author Oussama M Billah
 *
 */
@Component
public class SpringValidatorUtils extends ValidationUtils {

	public static void rejectIfConfirmFieldNotMatch(Errors errors, String first, String confirm, String errorCode) {
		String firstField = errors.getFieldValue(first).toString();
		String confirmField = errors.getFieldValue(confirm).toString();

		if (!StringUtils.equals(firstField, confirmField)) {
			errors.rejectValue(first, errorCode);
		}
	}
	
	public static void rejectIfInvalidCurrentPassword(Errors errors, String currentPassword, String newPassword, String errorCode) {
		String currentPasswordField = errors.getFieldValue(currentPassword).toString();
		String newPasswordField = errors.getFieldValue(newPassword).toString();

		if (!StringUtils.equals(currentPasswordField, newPasswordField)) {
			errors.rejectValue(currentPassword, errorCode);
		}
	}
	
	public static void rejectIfInvalidPassword(Errors errors, String password, String errorCode) {
		String passwordField = errors.getFieldValue(password).toString();
		Pattern pattern = Pattern.compile(Constants.PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(passwordField);
		  if (!matcher.matches()) {
			  errors.rejectValue(password, errorCode);
		  }
		
	}
	public static void rejectIfFieldTooShort(Errors errors, String field, int minLength, String errorCode) {
		String fieldValue = errors.getFieldValue(field).toString();
		if(StringUtils.length(fieldValue) < minLength) {
			errors.rejectValue(field, errorCode);
		}
	}
}
