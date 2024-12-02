package com.nimap.Operations.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import com.nimap.Operations.entity.Category;
import com.nimap.Operations.entity.Product;
import com.nimap.Operations.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Repository
public class CategoryDao {

	@Autowired
	private CategoryRepository repository;

	// GET all the categories
	public Page<Category> getAllCategoryDao(Pageable pageable) {
		return repository.findAll(pageable);
	}

	// POST - create a new category
	public Category saveCategoryDao(Category category) {
		List<Product> products = category.getProduct();
		if (products != null) {

			for (Product p : products) {
				p.setCategory(category);
			}		
		}
		return repository.save(category);
	}

	// GET category by Id
	public Optional<Category> getCategoryDao(long id) {
		Optional<Category> optional = repository.findById(id);
		if (optional.isPresent()) {
//			System.out.println(Optional.of(optional.get()));
			return Optional.of(optional.get());
		}
		return null;
	}

	// PUT - update category by id
	@Transactional
	public Category updateCategoryDao(Category category) {
		for (Product product : category.getProduct()) {
			product.setCategory(category); // Maintain the bidirectional relationship
		}
		return repository.save(category);
	}

	// DELETE - Delete category by id
	public String deleteCategoryDao(long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return "Category with ID " + id + " deleted successfully.";
		} else {
			return "Category with ID " + id + " does not exist.";
		}
	}

}
