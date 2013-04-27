package com.ombillah.ecom4j.webapp.springmvc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.remix.domain.Reviews.Review;
import com.ombillah.ecom4j.remix.service.RemixService;
import com.ombillah.ecom4j.service.ProductService;

/**
 * Controller class to handle Product Details functionality.
 * @author Oussama M Billah.
 *
 */
@Controller
public class ProductDetailsController {

	@Resource(name="productService")
	private ProductService productService;

	@Resource(name="remixService")
	private RemixService remixService;
	
	@RequestMapping(value = "/productDetails.do",  method = RequestMethod.GET)
	public String viewProductDetails(
			@RequestParam(value="productId", required=true) final Long productId,
			ModelMap modelMap) {
		Product product = productService.getProductDetails(productId);
		
		List<String> models = new ArrayList<String>();
		
		models.add(product.getModel());
		
		
		List<com.ombillah.ecom4j.remix.domain.Products.Product> products = remixService.getRemixProducts(models);
		
		if(CollectionUtils.isEmpty(products)) {
			// this is for demonstration only to display a similar result!
			products = remixService.getProductOfCategory(product.getMake(), product.getCategory().getCategoryId());
		}
		
		if(!CollectionUtils.isEmpty(products)) {
			List<String> skus = new ArrayList<String>();
			skus.add(Integer.toString(products.get(0).getSku()));
			List<Review> reviews = remixService.getRemixReviews(skus);
			
			modelMap.put("remixProduct", products.get(0));
			modelMap.put("reviews", reviews);

		}
		
		return "productDetails";
	}
	
}
