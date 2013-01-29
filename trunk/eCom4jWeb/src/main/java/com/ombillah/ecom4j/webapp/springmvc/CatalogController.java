package com.ombillah.ecom4j.webapp.springmvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import com.ombillah.ecom4j.service.ProductCategoryService;
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
	
	@Resource(name="productService")
	private ProductService productService;
	
	@Resource(name="productCategoryService")
	private ProductCategoryService productCategoryService;
	
	@Resource(name="paginationHandler")
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
			@ModelAttribute("catalogViewBean") CatalogViewBean catalogViewBean,
			@RequestParam(value="category", required=false) final String category,
			@RequestParam(value="isParent", required=false) final boolean isParentCategory) {
		
		setInitialPaginationProperties(catalogViewBean, category);
		retrieveProducts(catalogViewBean);
		retrieveBrands(catalogViewBean);
		retrieveCategories(catalogViewBean, category, isParentCategory);
		retrievePriceRanges(catalogViewBean);
		
		return "catalog";
	}

	private void setInitialPaginationProperties(CatalogViewBean catalogViewBean,
			String category) {
		
		Map<String, String[]> filters = catalogViewBean.getCurrentPage().getCatalogFilters();
		
		setProductFilter(catalogViewBean, filters);
		
		if (StringUtils.isNotBlank(category)){
			String[] filterValues = {category};
			filters.put("category", filterValues);
			setProductFilter(catalogViewBean, filters);
		}
		
		Page page = catalogViewBean.getCurrentPage();
		
		if(page == null) {
			page = new Page();
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
		Map<String, String[]> filters = catalogViewBean.getCurrentPage().getCatalogFilters();
		String[] categoryFilter = filters.get("category");
		String categoryId = categoryFilter[0];
		Map<String, Integer> priceRanges = productService.getProductPriceRange(categoryId);
		catalogViewBean.setPriceRanges(priceRanges);
	}
	
	private void retrieveCategories(CatalogViewBean catalogViewBean, String category, boolean isParentCategory) {
		if(isParentCategory) {
			Map<String, Integer> categories = productService.getProductCategories(category);
			catalogViewBean.setCategories(categories);
		}
		
	}
	
	
	private void retrieveBrands(CatalogViewBean catalogViewBean) {
		Map<String, String[]> filters = catalogViewBean.getCurrentPage().getCatalogFilters();
		String[] categoryFilter = filters.get("category");
		String categoryId = categoryFilter[0];
		Map<String, Integer> brands = productService.getManufacturerList(categoryId);
		catalogViewBean.setBrands(brands);
	}
	
	private void retrieveProducts(CatalogViewBean catalogViewBean) {
		
		retrieveFirstPage(catalogViewBean);
	}

	private void retrieveFirstPage(CatalogViewBean catalogViewBean) {
		Page page = catalogViewBean.getCurrentPage();
		List<Product> products = paginationHandler.getFirstPage(page);
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
				if(StringUtils.equals(filterName, "category")) {
					String[] categoryNames = filterValues;
					String[] categoryIds = productCategoryService.getProductCategoryIds(categoryNames);
					currentFilters.put(filterName, categoryIds);
				}else {
					currentFilters.put(filterName, filterValues);
				}
				
			}
		}
		setInitialPaginationProperties(catalogViewBean, null);
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
	
	@RequestMapping(value = "/sortResults.do", method = RequestMethod.POST)
	public String sortResults(
			String sortField,
			String sortOrder,
			@ModelAttribute("catalogViewBean") CatalogViewBean catalogViewBean) {
		
		Page page = catalogViewBean.getCurrentPage();
		page.setSortBy(sortField);
		boolean isAsc = StringUtils.equals(sortOrder, "asc");
		page.setSortAsc(isAsc);
		List<Product> products = paginationHandler.getFirstPage(page);
		page.setProducts(products);
		Long numberOfPages = paginationHandler.getPagesCount(page);
		page.setTotalNumberOfPages(numberOfPages.intValue());
		page.setCurrentPageNumber(FIRST_PAGE);
		catalogViewBean.setCurrentPage(page);
		
		return "catalogContent";
	}
	
	/**
	 * setter method for Mocking purpose.
	 * @param productServiceMock the productServiceMock to set
	 */
	public void setProductService(ProductService productServiceMock) {
		this.productService = productServiceMock;
	}
	
	/**
	 * setter method for Mocking purpose.
	 * @param paginationHandlerMock 
	 */
	public void setPaginationHandler(PaginationHandler paginationHandlerMock) {
		this.paginationHandler = paginationHandlerMock;
	}
	
	/**
	 * setter method for Mocking purpose.
	 * @param availablePageSizesMock
	 */
	public void setAvailablePageSizes(String availablePageSizesMock) {
		this.availablePageSizes = availablePageSizesMock;
	}

	/**
	 * setter method for Mocking purpose.
	 * the defaultPageSizeMock to set
	 */
	public void setDefaultPageSize(Integer defaultPageSizeMock) {
		this.defaultPageSize = defaultPageSizeMock;
	}
}
