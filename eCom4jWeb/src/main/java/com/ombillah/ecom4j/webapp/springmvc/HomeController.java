package com.ombillah.ecom4j.webapp.springmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.service.ProductService;

/**
 * Controller to render home page content.
 * @author Oussama M Billah
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = {"/home.do", "/"})
	public String displayHomePage(ModelMap map) {
		
		List<Product> featuredProductsList = productService.getFeaturedProducts();
		
		Product mainProduct = featuredProductsList.get(0);
		List<Product> featuredProducts = featuredProductsList.subList(1, featuredProductsList.size());
		
		map.put("mainProduct", mainProduct);
		map.put("featuredProducts", featuredProducts);
		
		return "homePage";
	}
	

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
}
