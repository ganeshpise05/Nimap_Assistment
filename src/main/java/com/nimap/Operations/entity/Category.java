package com.nimap.Operations.entity;

import java.util.List;
  
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity; 
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id
	private long id;
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> product;

	public Category() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
