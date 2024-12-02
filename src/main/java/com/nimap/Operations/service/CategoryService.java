package com.nimap.Operations.service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nimap.Operations.dao.CategoryDao;
import com.nimap.Operations.dto.ResponseStructure;
import com.nimap.Operations.entity.Category; 

@Service
public class CategoryService {

	@Autowired
	private CategoryDao dao;

	// GET all the categories
	public ResponseEntity<ResponseStructure<List<Category>>> getAllCategory(int page, int size) {

		Pageable pageable = (Pageable) PageRequest.of(page - 1, size);

		Page<Category> returnedCategories = dao.getAllCategoryDao(pageable);

		List<Category> categoryList = returnedCategories.getContent();

		// Creating response structure
		ResponseStructure<List<Category>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("All categories fetched successfully with pagination.");
		structure.setData(categoryList);

		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	// POST - create new Category.
	public ResponseEntity<ResponseStructure<Category>> saveCategory(Category category) {

		Category saveCategoryDao = dao.saveCategoryDao(category);
		ResponseStructure<Category> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Category inserted successfully into the database.");
		response.setData(saveCategoryDao);

		return new ResponseEntity<ResponseStructure<Category>>(response, HttpStatus.CREATED);
	}

	// GET - Get category by Id
	public ResponseEntity<ResponseStructure<Category>> getCategory(long id) {
		Optional<Category> returnedCategory = dao.getCategoryDao(id);

		ResponseStructure<Category> structure = new ResponseStructure<>();

		if (returnedCategory.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Category retrieved successfully.");
			structure.setData(returnedCategory.get());
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Category not found.");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	// PUT - Update category by Id

	public ResponseEntity<ResponseStructure<Category>> updateCategory(Optional<Category> optionalCategory,
			Category updatedCategory) {
		if (optionalCategory.isPresent()) {

			Category existingCategory = optionalCategory.get();
			existingCategory.setName(updatedCategory.getName());
			existingCategory.setProduct(updatedCategory.getProduct());
			dao.updateCategoryDao(existingCategory);

			ResponseStructure<Category> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Category and products updated successfully.");
			structure.setData(existingCategory);

			//

			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.ACCEPTED);
		} else {
			ResponseStructure<Category> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Category data is missing.");
			structure.setData(null);

			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.BAD_REQUEST);
		}
	}

	// DELETE - Delete category by Id
	public ResponseEntity<ResponseStructure<String>> deleteCategory(long id) {
		Optional<Category> existingCategory = dao.getCategoryDao(id);

		ResponseStructure<String> structure = new ResponseStructure<>();

		if (existingCategory.isPresent()) {
			dao.deleteCategoryDao(id);

			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Category deleted successfully.");
			structure.setData("Category with ID " + id + " has been deleted.");

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Category not found.");
			structure.setData("Category with ID " + id + " does not exist.");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		}
	}

}
