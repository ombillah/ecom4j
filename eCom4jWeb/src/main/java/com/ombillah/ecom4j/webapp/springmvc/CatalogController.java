package com.ombillah.ecom4j.webapp.springmvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.service.ProductService;
import com.ombillah.ecom4j.webapp.springmvc.viewbean.CatalogFilter;
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
	
	@Value(value="#{'${CATALOG_PAGE_SIZE}'}")
    private Integer catalogPageSize;
	
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
		
		resetProductFilter(catalogViewBean);
		retrieveProducts(catalogViewBean, category, brand);
		retrieveBrands(session, catalogViewBean);
		retrieveCategories(session, catalogViewBean);
		retrievePriceRanges(catalogViewBean);
		
		return "catalog";
	}
	
	private void resetProductFilter(CatalogViewBean catalogViewBean) {
		Map<String, String[]> filters = new HashMap<String, String[]>();
		String[] all = {"all"};
		filters.put("category", all);
		filters.put("make", all);
		filters.put("price", all);
		catalogViewBean.setCatalogFilters(filters);
		
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
		Integer startIndex = 0;
		Map<String, String[]> filters = catalogViewBean.getCatalogFilters();
	
		catalogViewBean.setCatalogFilters(filters);
		if (StringUtils.isNotBlank(category)){
			String[] filterValues = {category};
			filters.put("category", filterValues);
			catalogViewBean.setCatalogFilters(filters);
		}else if(StringUtils.isNotBlank(brand)){
			String[] filterValues = {brand};
			filters.put("make", filterValues);
			catalogViewBean.setCatalogFilters(filters);
		}
		products = productService.getProducts(catalogViewBean.getCatalogFilters(), startIndex, catalogPageSize);
		//catalogViewBean.setProducts(products);
	}

	@RequestMapping(value = "/addfilter.do", method = RequestMethod.POST)
	public String addProductFilter(
			@RequestBody CatalogFilter catalogFilter,
			@ModelAttribute("catalogViewBean") CatalogViewBean catalogViewBean) {
		String filterName = catalogFilter.getName();
		String[] filterValues = catalogFilter.getValues();
		Map<String, String[]> currentFilters = catalogViewBean.getCatalogFilters();
		for(String filter : catalogViewBean.getCatalogFilters().keySet()) {
			if(StringUtils.equals(filterName, filter)) {
				currentFilters.put(filterName, filterValues);
			}
		}
		catalogViewBean.setCatalogFilters(currentFilters);
		List<Product> products = productService.getProducts(currentFilters, 0, catalogPageSize);
		//catalogViewBean.setProducts(products);
		return "catalogContent";
	}
}
