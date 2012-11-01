package com.ombillah.ecom4j.webapp.springmvc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ombillah.ecom4j.service.ProductService;

/**
 * Interceptor class to intercept all requests and set the Navigation Bar, menu items...
 * in the ModelAndView Object.
 * @author Oussama M Billah
 *
 */
@Component("menuItemsInterceptorHandler")
public class MenuItemsInterceptorHandler extends HandlerInterceptorAdapter {
	
	@Autowired
	private ProductService productService;
	
	@SuppressWarnings("unchecked")
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, 
			ModelAndView modelAndView) throws Exception {
		
		List<String> productCategories = (List<String>) request.getSession().getAttribute("productCategories");
		List<String> brands = (List<String>) request.getSession().getAttribute("brands");
		
		if (CollectionUtils.isEmpty(productCategories)) {
			productCategories = productService.getProductCategories();
			brands = productService.getManufacturerList();
			
			request.getSession().setAttribute("productCategories", productCategories);
			request.getSession().setAttribute("brands", brands);			
		}
		
		modelAndView.addObject("productCategories", productCategories);
		modelAndView.addObject("brands", brands);
	}
}
