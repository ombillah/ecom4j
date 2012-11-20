package com.ombillah.ecom4j.webapp.springmvc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.service.ProductService;
import com.ombillah.ecom4j.webapp.springmvc.viewbean.CatalogViewBean;


/**
 * Controller class to handle catalog functionality.
 * @author Oussama M Billah.
 *
 */
@Controller
@SessionAttributes("catalogViewBean")
public class CatalogController {
	
	@Autowired
	private ProductService productService;
	
	@ModelAttribute("catalogViewBean")
	public CatalogViewBean createCatalogViewBean() {
		return new CatalogViewBean();
	}
	
	@RequestMapping(value = "/catalog.do", method = RequestMethod.GET)
	public String displayProductCatalog(
			HttpSession session,
			@ModelAttribute("catalogViewBean") CatalogViewBean catalogViewBean,
			@RequestParam(value="category", required=false) final String category,
			@RequestParam(value="brand", required=false) final String brand) {
		
		retrieveProducts(catalogViewBean, category, brand);
		retrieveBrands(session, catalogViewBean);
		retrieveCategories(session, catalogViewBean);
		retrievePriceRanges(catalogViewBean);
		
		return "catalog";
	}
	
	private void retrievePriceRanges(CatalogViewBean catalogViewBean) {
		Map<String, Integer> priceRanges = productService.getProductPriceRange();
		catalogViewBean.setPriceRanges(priceRanges);
	}
	
	@SuppressWarnings("unchecked")
	private void retrieveCategories(HttpSession session, CatalogViewBean catalogViewBean) {
		Map<String, Integer> categories = (Map<String, Integer>) session.getAttribute("productCategories");
		if (categories == null) {
			categories = productService.getProductCategories();
		}
		catalogViewBean.setCategories(categories);
	}
	
	@SuppressWarnings("unchecked")
	private void retrieveBrands(HttpSession session, CatalogViewBean catalogViewBean) {
		Map<String, Integer> brands = (Map<String, Integer>) session.getAttribute("brands");
		if (brands == null) {
			brands = productService.getManufacturerList();
		}
		catalogViewBean.setBrands(brands);
	}
	
	private void retrieveProducts(CatalogViewBean catalogViewBean, final String category, final String brand) {
		List<Product> products;
		if (StringUtils.isNotBlank(category)){
			products = productService.getProductsByCategory(category);
		}else if(StringUtils.isNotBlank(brand)){
			products = productService.getProductsByBrand(brand);
		} else {
			products = productService.getProducts(catalogViewBean.getCatalogFilters());
		}
		catalogViewBean.setProducts(products);
	}
}
