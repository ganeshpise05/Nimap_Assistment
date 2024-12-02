package com.nimap.Operations.dao;
 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
 
import com.nimap.Operations.entity.Product;
import com.nimap.Operations.repository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository repository;

	// GET all the products
	public Page<Product> getAllProductDao(Pageable pageable) {
		return repository.findAll(pageable);
	}

	// POST - create a new product
	public Product saveProductDao(Product product) {

		return repository.save(product);
	}

	// GET product by Id
	public Optional<Product> getProductDao(long id) {

		Optional<Product> optional = repository.findById(id);

		if (optional.isPresent()) {
			return Optional.of(optional.get());
		}
		return null;
		// return repository.findByIdWithCategory(id);
	}

	// PUT - update product by id
	public Product updateProductDao(Product product) {
		return repository.save(product);
	}

	// DELETE - Delete product by id
	public String deleteProductDao(long id) {
		Product product = getProductDao(id).get();

		if (product != null) {
			repository.deleteById(id);
			return "Product with ID " + id + " deleted successfully.";
		} else {
			return "Product with ID " + id + " does not exist.";
		}
	}

}
