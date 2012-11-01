package com.ombillah.ecom4j.validation;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator class to check if two field's values are equal.
 *
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    
	private String firstFieldName;
    private String secondFieldName;
	private static final Log LOG = LogFactory.getLog(FieldMatchValidator.class);

    
    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try{
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {
        	LOG.error(ignore);
        }
        return true;
    }
}