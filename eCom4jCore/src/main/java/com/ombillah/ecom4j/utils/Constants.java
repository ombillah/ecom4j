
package com.ombillah.ecom4j.utils;

/**
 * Class to hold project Constants.
 * @author Oussama M Billah
 */
public final class Constants {
	
	/**
	 * making constructor private
	 * to restrict creating instance for static only members.
	 */
	private Constants() {
		
	}
	
	public static final Long LATEST_PROD_LIMIT = new Long(3);
	public static final int RESULTS_PER_PAGE = 6;
	public static final int ORDER_NBR_LIMIT = 1000;
	public static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d).+$";
	public static final int PASSWORD_MIN_LENGTH = 8;
	public static final int UNFEATURED_PRODUCT = 0;
	public static final String STANDARD_SHIPPING = "standard";
	public static final String EXPRESS_SHIPPING = "express";
	public static final Float EXPRESS_SHIPPING_COST = 19.95f;
	public static final String GUEST_ROLE = "ROLE_GUEST";
	
}
