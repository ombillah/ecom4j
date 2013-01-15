package com.ombillah.ecom4j.webapp.springmvc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ombillah.ecom4j.domain.ProductCategory;
import com.ombillah.ecom4j.service.ProductCategoryService;

/**
 * Interceptor class to intercept all requests and set the Navigation Bar, menu items...
 * in the ModelAndView Object.
 * @author Oussama M Billah
 *
 */
@Component("menuItemsInterceptorHandler")
public class MenuItemsInterceptorHandler extends HandlerInterceptorAdapter {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@SuppressWarnings("unchecked")
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, 
			ModelAndView modelAndView) throws Exception {
		
		if (modelAndView == null) {
			return;
		}
		List<ProductCategory> productCategories = (List<ProductCategory>) request.getSession().getAttribute("productCategories");
		
		if (CollectionUtils.isEmpty(productCategories)) {
			productCategories = productCategoryService.getProductCategories();
			
			request.getSession().setAttribute("productCategories", productCategories);
		}
		
		modelAndView.addObject("productCategories", productCategories);
	}
}
