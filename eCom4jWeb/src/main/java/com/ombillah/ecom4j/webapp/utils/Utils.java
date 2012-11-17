package com.ombillah.ecom4j.webapp.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Utility Class.
 * @author Oussama M Billah.
 *
 */
public final class Utils {
	
	/**
	 * making constructor private
	 * to restrict creating instance for static only members.
	 */
	private Utils() {
		
	}
	
	public static String urlEncode(String value) throws UnsupportedEncodingException {
	    return URLEncoder.encode(value, "UTF-8");
	}
}
