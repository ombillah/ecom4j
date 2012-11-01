package com.ombillah.ecom4j.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Spring Security User that defines security attributes
 * and wraps the Customer object.
 * @author Oussama M Billah
 *
 */
public class SpringSecurityUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Customer customer;
	private List<String> roles;
	private boolean isAccountEnabled;
	private boolean isCredentialsNonExpired;
	private boolean isAccountNonLocked;
	private boolean isAccountNonExpired;
	
	
	public SpringSecurityUser(Customer customer, 
			boolean enabled, 
			boolean accountNonExpired, 
			boolean credentialsNonExpired, 
			boolean accountNonLocked, 
			List<String> roles) {
		
		this.customer = customer;
		this.isAccountEnabled = enabled;
		this.isAccountNonExpired = accountNonExpired;
		this.isCredentialsNonExpired = credentialsNonExpired;
		this.isAccountNonLocked = accountNonLocked;
		this.roles = roles;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String getPassword() {
		if(customer != null) {
			return customer.getPassword().toLowerCase();
		}
		return null;
	}
	
	public String getUsername() {
		return customer.getEmailAddress();
	}

	
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public boolean isEnabled() {
		return isAccountEnabled;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	
}
