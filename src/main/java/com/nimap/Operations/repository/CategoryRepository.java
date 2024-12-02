package com.nimap.Operations.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.nimap.Operations.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

