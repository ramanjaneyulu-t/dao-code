package com.taodigital.productservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taodigital.productservice.dto.ProductDto;
import com.taodigital.productservice.entity.Product;
import com.taodigital.productservice.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getActiveProducts() {
		List<Product> products = productService.getActiveProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String productName, 
			Double minPrice, Double maxPrice, 
			LocalDate minPostedDate, LocalDate maxPostedDate) {
		List<Product> products = productService.searchProducts(productName, minPrice, maxPrice, minPostedDate, maxPostedDate);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) throws Exception {
		Product product = productService.createProduct(productDto);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PutMapping("{productId}")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto,
			@PathVariable Long productId) {
		Product product = productService.updateProduct(productDto, productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@DeleteMapping("{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
		String message = productService.deleteProduct(productId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	
}
