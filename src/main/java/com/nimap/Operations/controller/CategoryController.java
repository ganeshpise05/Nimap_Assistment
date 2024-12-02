package com.nimap.Operations.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.nimap.Operations.dao.CategoryDao;
import com.nimap.Operations.dto.ResponseStructure;
import com.nimap.Operations.entity.Category;
import com.nimap.Operations.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryDao dao;

	// GET all categories // http://localhost:8081/api/categories?page=2
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Category>>> getAllCategories(
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "6") int size) {

		return categoryService.getAllCategory(page, size);
	}

	// POST - create a new category
	@PostMapping
	public ResponseEntity<ResponseStructure<Category>> createCategory(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}

	// GET - Get category by ID
	@GetMapping("{id}") // http://localhost:8081/api/categories/1
	public ResponseEntity<ResponseStructure<Category>> getCategoryById(@PathVariable long id) {
		return categoryService.getCategory(id);
	}

	// PUT - Update category by ID // http://localhost:8081/api/categories/1
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Category>> updateCategory(@PathVariable long id,
			@RequestBody Map<String, String> updatedCategoryData) {

		Optional<Category> existingCategory = dao.getCategoryDao(id);

		if (existingCategory.isPresent()) {
			Category category = existingCategory.get();

			// update the name
			String newName = updatedCategoryData.get("name");
			if (newName != null && !newName.isEmpty()) {
				category.setName(newName);
			}

			// Save the updated category
			dao.updateCategoryDao(category);

			ResponseStructure<Category> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Category name updated successfully.");
			structure.setData(category);

			return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
		} else {
			ResponseStructure<Category> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Category not found.");
			structure.setData(null);

			return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
		}
	}

	// DELETE - Delete category by ID
	@DeleteMapping("{id}") // http://localhost:8081/api/categories/4
	public ResponseEntity<ResponseStructure<String>> deleteCategory(@PathVariable long id) {
		return categoryService.deleteCategory(id);
	}

}
