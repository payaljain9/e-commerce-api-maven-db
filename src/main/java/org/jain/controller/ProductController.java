package org.jain.controller;

import java.util.List;

import org.jain.product.dto.Product;
import org.jain.product.dto.ProductResponse;
import org.jain.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping(path="/product/{id}", produces= {"application/json"})
	public ProductResponse searchProduct(@PathVariable int id)
	{
		return productService.searchProduct(id);
	}
	
	@PostMapping(path="/product", produces= {"application/json"}, consumes= {"application/json"})
	public ProductResponse addProduct(@RequestBody Product product)
	{
		return productService.saveProduct(product);
	}
	
	@GetMapping(path="/product", produces= {"application/json"})
	public List<ProductResponse> getAllProducts()
	{
		return productService.getAllProducts();
	}
}
