package com.ombillah.ecom4j.webapp.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.domain.SpringSecurityUser;

/**
 * Utility Class to Access Spring security related functions.
 * @author Oussama M Billah.
 *
 */
@Component
public final class SpringSecurityUtils {
	
    private static AuthenticationManager authenticationManager;
	
    /**
     * work around to inject static fields as spring beans.
     * @param authenticationManager
     */
	@Autowired(required = true)
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		SpringSecurityUtils.authenticationManager = authenticationManager;
	}
	
	/**
	 * making constructor private
	 * to restrict creating instance for static only members.
	 */
	private SpringSecurityUtils() {
		
	}
	
	public static Customer getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SpringSecurityUser currentUser = (SpringSecurityUser) authentication.getPrincipal();
		Customer customer = currentUser.getCustomer();
		return customer;
	}
	
	public static void doAutoLogin(String username, String password, HttpServletRequest request) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);

	    token.setDetails(new WebAuthenticationDetails(request));
	    Authentication authenticatedUser = authenticationManager.authenticate(token);

	    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	    request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
	            SecurityContextHolder.getContext());
	}
}
