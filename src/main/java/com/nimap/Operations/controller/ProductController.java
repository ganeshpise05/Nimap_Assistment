package com.nimap.Operations.controller;

import java.util.List;
import java.util.Map;

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

import com.nimap.Operations.dto.ResponseStructure;
import com.nimap.Operations.entity.Product;
import com.nimap.Operations.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// GET all products
	@GetMapping // http://localhost:8081/api/products?page=2
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "6") int size) {

		return productService.getAllProduct(page, size);
	}

	// POST - create a new product
	@PostMapping // http://localhost:8081/api/products
	public ResponseEntity<ResponseStructure<Product>> createProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	// GET - Get product by ID
	@GetMapping("{id}") // http://localhost:8081/api/products/1
	public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable long id) {
		return productService.getProduct(id);
	}

	// PUT - update product by id
	@PutMapping(value = "/{productId}", consumes = "application/json")
	public ResponseEntity<ResponseStructure<Product>> updateProductName(@PathVariable Long productId,
			@RequestBody Map<String, String> requestBody) {

		// Extract new product name from request body
		String newName = requestBody.get("name");

		if (newName == null || newName.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		// service method
		ResponseStructure<Product> response = productService.updateProductName(productId, newName);

		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
	}

	// DELETE - Delete product by id
	@DeleteMapping("/{id}") // http://localhost:8081/api/products/8
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable long id) {
		return productService.deleteProduct(id);
	}
}
