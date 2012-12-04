package com.ombillah.ecom4j.webapp.springmvc;

import java.util.ArrayList;
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

import com.ombillah.ecom4j.domain.Page;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.pagination.PaginationHandler;
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
	@Autowired
	private PaginationHandler paginationHandler;
	
	@Value("${CATALOG_PAGE_SIZE_OPTIONS}")
	private String availablePageSizes;
	
	@Value("${DEFAULT_PAGE_SIZE}")
	private Integer defaultPageSize;
	
	private static final int FIRST_PAGE = 1;
	
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
		
		setInitialPaginationProperties(catalogViewBean, true);
		retrieveProducts(catalogViewBean, category, brand);
		retrieveBrands(session, catalogViewBean);
		retrieveCategories(session, catalogViewBean);
		retrievePriceRanges(catalogViewBean);
		
		return "catalog";
	}

	private void setInitialPaginationProperties(CatalogViewBean catalogViewBean, boolean isNewPage) {
		Page page;
		if(isNewPage) {
			page = new Page();
		} else {
			page = catalogViewBean.getCurrentPage();
		}
		page.setPageSize(defaultPageSize);
		List<Integer> availablePageSizesArray = createAvailablePageSizes();
		page.setAvailablePageSizes(availablePageSizesArray);
		Long numberOfPages = paginationHandler.getPagesCount(page);
		page.setTotalNumberOfPages(numberOfPages.intValue());
		page.setCurrentPageNumber(FIRST_PAGE);
		catalogViewBean.setCurrentPage(page);
	}


	private List<Integer> createAvailablePageSizes() {
		String[] strArray = availablePageSizes.split(",");
		 List<Integer> sizesList = new ArrayList<Integer>();
		for(int i = 0; i < strArray.length; i++) {
			sizesList.add(Integer.parseInt(strArray[i]));
		}
		return sizesList;
	}

	private void setProductFilter(CatalogViewBean catalogViewBean, Map<String, String[]> filters) {
		Page page = catalogViewBean.getCurrentPage();
		page.setCatalogFilters(filters);
		catalogViewBean.setCurrentPage(page);
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
		Map<String, String[]> filters = catalogViewBean.getCurrentPage().getCatalogFilters();
	
		setProductFilter(catalogViewBean, filters);
		
		if (StringUtils.isNotBlank(category)){
			String[] filterValues = {category};
			filters.put("category", filterValues);
			setProductFilter(catalogViewBean, filters);
		}else if(StringUtils.isNotBlank(brand)){
			String[] filterValues = {brand};
			filters.put("make", filterValues);
			setProductFilter(catalogViewBean, filters);
		}
		
		retrieveFirstPage(catalogViewBean);
	}

	private void retrieveFirstPage(CatalogViewBean catalogViewBean) {
		List<Product> products = paginationHandler.getFirstPage(catalogViewBean.getCurrentPage());
		Page page = catalogViewBean.getCurrentPage();
		page.setProducts(products);
		catalogViewBean.setCurrentPage(page);
	}

	@RequestMapping(value = "/addfilter.do", method = RequestMethod.POST)
	public String addProductFilter(
			@RequestBody CatalogFilter catalogFilter,
			@ModelAttribute("catalogViewBean") CatalogViewBean catalogViewBean) {
		String filterName = catalogFilter.getName();
		String[] filterValues = catalogFilter.getValues();
		Map<String, String[]> currentFilters = catalogViewBean.getCurrentPage().getCatalogFilters();
		for(String filter : catalogViewBean.getCurrentPage().getCatalogFilters().keySet()) {
			if(StringUtils.equals(filterName, filter)) {
				currentFilters.put(filterName, filterValues);
			}
		}
		setInitialPaginationProperties(catalogViewBean, false);
		setProductFilter(catalogViewBean, currentFilters);
		retrieveFirstPage(catalogViewBean);
		
		return "catalogContent";
	}
	
	@RequestMapping(value = "/doPagination.do", method = RequestMethod.POST)
	public String doPagination(
			Integer pageNumber,
			@ModelAttribute("catalogViewBean") CatalogViewBean catalogViewBean) {
		
		Page page = catalogViewBean.getCurrentPage();
		List<Product> products = paginationHandler.getPage(page, pageNumber);
		page.setCurrentPageNumber(pageNumber);
		page.setProducts(products);
		catalogViewBean.setCurrentPage(page);
		
		return "catalogContent";
	}
	
	@RequestMapping(value = "/changePageSize.do", method = RequestMethod.POST)
	public String changePageSize(
			Integer pageSize,
			@ModelAttribute("catalogViewBean") CatalogViewBean catalogViewBean) {
		
		Page page = catalogViewBean.getCurrentPage();
		page.setPageSize(pageSize);
		List<Product> products = paginationHandler.getFirstPage(page);
		page.setProducts(products);
		Long numberOfPages = paginationHandler.getPagesCount(page);
		page.setTotalNumberOfPages(numberOfPages.intValue());
		page.setCurrentPageNumber(FIRST_PAGE);
		catalogViewBean.setCurrentPage(page);
		
		return "catalogContent";
	}
}
