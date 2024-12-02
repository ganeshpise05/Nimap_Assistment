package com.nimap.Operations.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 
import com.nimap.Operations.dao.ProductDao;
import com.nimap.Operations.dto.ResponseStructure; 
import com.nimap.Operations.entity.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao dao; 

	// GET all the products
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(int page, int size) {

		Pageable pageable = (Pageable) PageRequest.of(page - 1, size);

		Page<Product> returnedProduct = dao.getAllProductDao(pageable);

		List<Product> productList = returnedProduct.getContent();

		// Creating response structure
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("All Products fetched successfully with pagination.");
		structure.setData(productList);

		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	// POST - create a new product
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {

		Product saveCategoryDao = dao.saveProductDao(product);
		ResponseStructure<Product> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Category inserted successfully into the database.");
		response.setData(saveCategoryDao);

		return new ResponseEntity<ResponseStructure<Product>>(response, HttpStatus.CREATED);
	}

	// GET product by Id
	public ResponseEntity<ResponseStructure<Product>> getProduct(long id) {

		Optional<Product> returnedProduct = dao.getProductDao(id);

		ResponseStructure<Product> structure = new ResponseStructure<Product>();

		if (returnedProduct.isPresent()) {

			Product product = returnedProduct.get();

			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Data fetched Successfully along with Category!! ");
			structure.setData(product);

			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);

		} else {

			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Product not found.");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	// PUT - update product name
	public ResponseStructure<Product> updateProductName(long productId, String newName) {
		Optional<Product> productOptional = dao.getProductDao(productId);

		ResponseStructure<Product> response = new ResponseStructure<>();

		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			product.setName(newName);
			dao.updateProductDao(product); // Save updated product

			response.setStatusCode(200);
			response.setMessage("Product name updated successfully.");
			response.setData(product);
		} else {
			response.setStatusCode(404);
			response.setMessage("Product not found.");
			response.setData(null);
		}

		return response;
	}

	// DELETE - Delete Product by Id
	public ResponseEntity<ResponseStructure<String>> deleteProduct(long id) {
		String msg = dao.deleteProductDao(id);

		ResponseStructure<String> structure = new ResponseStructure<>();

		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Product not found.");
		structure.setData(msg);
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);

	}
}
