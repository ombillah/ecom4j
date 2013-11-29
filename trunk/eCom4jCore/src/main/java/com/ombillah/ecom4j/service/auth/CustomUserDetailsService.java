package com.ombillah.ecom4j.service.auth;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ombillah.ecom4j.dao.CustomerDAO;
import com.ombillah.ecom4j.domain.Customer;
import com.ombillah.ecom4j.domain.SpringSecurityUser;

/**
 * A custom {@link UserDetailsService} where user information
 * is retrieved from DB
 * @author Oussama M Billah
 * 
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerDAO customerDAO;

	/**
	 * Returns a populated {@link UserDetails} object. 
	 * The username is first retrieved from the database and then mapped to 
	 * a {@link UserDetails} object.
	 */
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Customer customer = customerDAO.getObject(Customer.class, email);
			
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			String role = "ROLE_USER";
			List<String> roles = new ArrayList<String>();
			roles.add(role);
			
			UserDetails userDetails =  new SpringSecurityUser(
					customer, 
					enabled,
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked, 
					roles);
			
			return userDetails;
			
		} catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}
	

}
