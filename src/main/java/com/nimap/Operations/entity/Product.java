package com.nimap.Operations.entity;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
 
import jakarta.persistence.Entity; 
import jakarta.persistence.Id; 
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	private long id;
	private String name;

	@JsonIgnore
	@ManyToOne
	private Category category;

	public Product() {
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

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;

	}

}
