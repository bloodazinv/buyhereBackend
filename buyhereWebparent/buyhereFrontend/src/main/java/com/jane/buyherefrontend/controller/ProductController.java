package com.jane.buyherefrontend.controller;

import com.jane.buyherecommon.entity.Category;
import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.entity.Review;
import com.jane.buyherecommon.entity.product.Product;
import com.jane.buyherecommon.exception.CategoryNotFoundException;
import com.jane.buyherecommon.exception.CustomerNotFoundException;
import com.jane.buyherecommon.exception.ProductNotFoundException;
import com.jane.buyherefrontend.service.CategoryService;
import com.jane.buyherefrontend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	private CategoryService categoryService;
	
	private ProductService productService;
	

	
	@Autowired
	public ProductController(CategoryService categoryService, ProductService productService){
		super();
		this.categoryService = categoryService;
		this.productService = productService;

	}

	@GetMapping("/c/{category_alias}")
	public String viewCategoryFirstPage(@PathVariable("category_alias") String alias,
			Model model) {
		
		LOGGER.info("ProductController | viewCategoryFirstPage is called");
		
		return viewCategoryByPage(alias, 1, model);
	}
	
	@GetMapping("/c/{category_alias}/page/{pageNum}")
	public String viewCategoryByPage(@PathVariable("category_alias") String alias,
			@PathVariable("pageNum") int pageNum,
			Model model) {
		
		try {
			
			LOGGER.info("ProductController | viewCategoryByPage is called");
			
			Category category = categoryService.getCategory(alias);
			
			LOGGER.info("ProductController | viewCategoryByPage | category : " + category.toString());
			

			List<Category> listCategoryParents = categoryService.getCategoryParents(category);
			
			LOGGER.info("ProductController | viewCategoryByPage | listCategoryParents : " + listCategoryParents.toString());

			Page<Product> pageProducts = productService.listByCategory(pageNum, category.getId());
			
			List<Product> listProducts = pageProducts.getContent();
			
			LOGGER.info("ProductController | viewCategoryByPage | listProducts : " + listProducts.toString());

			long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
			long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
			
			LOGGER.info("ProductController | viewCategoryByPage | startCount : " + startCount);
			LOGGER.info("ProductController | viewCategoryByPage | endCount : " + endCount);
			
			LOGGER.info("ProductController | viewCategoryByPage | endCount > pageProducts.getTotalElements()");
			if (endCount > pageProducts.getTotalElements()) {
				LOGGER.info("ProductController | viewCategoryByPage | endCount > pageProducts.getTotalElements() | endCount : " + endCount);
				LOGGER.info("ProductController | viewCategoryByPage | endCount > pageProducts.getTotalElements() | pageProducts.getTotalElements() : " + pageProducts.getTotalElements());
				LOGGER.info("ProductController | viewCategoryByPage | endCount > pageProducts.getTotalElements()");
				endCount = pageProducts.getTotalElements();
			}

			LOGGER.info("ProductController | viewCategoryByPage | currentPage : " + pageNum);
			LOGGER.info("ProductController | viewCategoryByPage | totalPages : " + pageProducts.getTotalPages());
			LOGGER.info("ProductController | viewCategoryByPage | startCount : " + startCount);
			LOGGER.info("ProductController | viewCategoryByPage | endCount : " + endCount);
			LOGGER.info("ProductController | viewCategoryByPage | totalItems : " + pageProducts.getTotalElements());
			LOGGER.info("ProductController | viewCategoryByPage | pageTitle : " + category.getName());
			LOGGER.info("ProductController | viewCategoryByPage | listCategoryParents : " + listCategoryParents);
			LOGGER.info("ProductController | viewCategoryByPage | listProducts : " + listProducts);
			LOGGER.info("ProductController | viewCategoryByPage | category : " + category.toString());
			
			model.addAttribute("currentPage", pageNum);
			model.addAttribute("totalPages", pageProducts.getTotalPages());
			model.addAttribute("startCount", startCount);
			model.addAttribute("endCount", endCount);
			model.addAttribute("totalItems", pageProducts.getTotalElements());
			model.addAttribute("pageTitle", category.getName());
			model.addAttribute("listCategoryParents", listCategoryParents);
			model.addAttribute("listProducts", listProducts);
			model.addAttribute("category", category);

			return "product/products_by_category";
			
			
		} catch (CategoryNotFoundException ex) {
			return "error/404";
		}
		
	}
	
	@GetMapping("/p/{product_alias}")
	public String viewProductDetail(@PathVariable("product_alias") String alias, Model model, HttpServletRequest request) throws CustomerNotFoundException {
		
		LOGGER.info("ProductController | viewProductDetail is called");
		
		try {
			Product product = productService.getProduct(alias);
			
			List<Category> listCategoryParents = categoryService.getCategoryParents(product.getCategory());

			
			LOGGER.info("ProductController | viewProductDetail | listCategoryParents : " + listCategoryParents.toString());
			LOGGER.info("ProductController | viewProductDetail | product : " + product.toString());
			LOGGER.info("ProductController | viewProductDetail | pageTitle : " + product.getShortName());

			model.addAttribute("listCategoryParents", listCategoryParents);
			model.addAttribute("product", product);
			model.addAttribute("pageTitle", product.getShortName());

			return "product/product_detail";
		} catch (ProductNotFoundException e) {
			return "error/404";
		}
	}
	
	@GetMapping("/search")
	public String searchFirstPage(@RequestParam("keyword") String keyword, Model model) {
		
		LOGGER.info("ProductController | searchFirstPage is called");
		
		return searchByPage(keyword, 1, model);
	}

	@GetMapping("/search/page/{pageNum}")
	public String searchByPage(@RequestParam("keyword") String keyword,
			@PathVariable("pageNum") int pageNum,
			Model model) {
		
		LOGGER.info("ProductController | searchByPage is called");
		
		Page<Product> pageProducts = productService.search(keyword, pageNum);
		List<Product> listResult = pageProducts.getContent();
		
		LOGGER.info("ProductController | searchByPage | pageProducts : " + pageProducts.toString());
		LOGGER.info("ProductController | searchByPage | listResult : "  + listResult.toString());

		long startCount = (pageNum - 1) * ProductService.SEARCH_RESULTS_PER_PAGE + 1;
		long endCount = startCount + ProductService.SEARCH_RESULTS_PER_PAGE - 1;
		
		
		LOGGER.info("ProductController | searchByPage | startCount : " + startCount);
		LOGGER.info("ProductController | searchByPage | endCount : " + endCount);
		
		if (endCount > pageProducts.getTotalElements()) {
			
			LOGGER.info("ProductController | searchByPage | endCount > pageProducts.getTotalElements() | endCount : " + endCount);
			LOGGER.info("ProductController | searchByPage | endCount > pageProducts.getTotalElements() | pageProducts.getTotalElements() : " + pageProducts.getTotalElements());
			LOGGER.info("ProductController | searchByPage | endCount > pageProducts.getTotalElements()");
			
			endCount = pageProducts.getTotalElements();
		}
		
		LOGGER.info("ProductController | viewCategoryByPage | currentPage : " + pageNum);
		LOGGER.info("ProductController | viewCategoryByPage | totalPages : " + pageProducts.getTotalPages());
		LOGGER.info("ProductController | viewCategoryByPage | startCount : " + startCount);
		LOGGER.info("ProductController | viewCategoryByPage | endCount : " + endCount);
		LOGGER.info("ProductController | viewCategoryByPage | totalItems : " + pageProducts.getTotalElements());
		LOGGER.info("ProductController | viewCategoryByPage | pageTitle : " + keyword + " - Search Result");

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", pageProducts.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", pageProducts.getTotalElements());
		model.addAttribute("pageTitle", keyword + " - Search Result");

		LOGGER.info("ProductController | viewCategoryByPage | keyword : " + keyword);
		LOGGER.info("ProductController | searchByPage | listResult : "  + listResult.toString());
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchKeyword", keyword);
		model.addAttribute("listResult", listResult);

		return "product/search_result";
	}
}