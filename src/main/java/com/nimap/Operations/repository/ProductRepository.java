package com.nimap.Operations.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; 

import com.nimap.Operations.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
	Optional<Product> findByIdWithCategory(@Param("id") Long id);
	

}
