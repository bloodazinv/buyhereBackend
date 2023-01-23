package com.jane.buyherebackend.controller;


import com.jane.buyherebackend.dto.ProductDTO;
import com.jane.buyherebackend.service.ProductService;
import com.jane.buyherecommon.entity.product.Product;
import com.jane.buyherecommon.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

	@Autowired 
	private ProductService service;

	@PostMapping("/products/check_unique")
	public String checkUnique(Integer id, @RequestParam("name") String name) {
		LOGGER.info("ProductRestController | checkUnique is started");
		return service.checkUnique(id, name);
	}
	
	@GetMapping("/products/get/{id}")
	public ProductDTO getProductInfo(@PathVariable("id") Integer id)
			throws ProductNotFoundException {
		
		Product product = service.get(id);
		
		return new ProductDTO(product.getName(), product.getMainImagePath(), 
				product.getDiscountPrice(), product.getCost());
	}
}