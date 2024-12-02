# SpringBoot Restful API 

## Overview

This project implements a Spring Boot application with RESTful APIs for performing CRUD operations on Category and Product entities. The Category and Product entities have a one-to-many relationship, where one category can have multiple products. The application also supports server-side pagination for both categories and products, and provides category details when fetching a single product.

## Features

- *Category CRUD Operations:*
  - Get all categories with pagination: GET /api/categories?page={page}
  - Create a new category: POST /api/categories
  - Get category by ID: GET /api/categories/{id}
  - Update category by ID: PUT /api/categories/{id}
  - Delete category by ID: DELETE /api/categories/{id}
  
- *Product CRUD Operations:*
  - Get all products with pagination: GET /api/products?page={page}
  - Create a new product: POST /api/products
  - Get product by ID with associated category details: GET /api/products/{id}
  - Update product by ID: PUT /api/products/{id}
  - Delete product by ID: DELETE /api/products/{id}

- *Database Configuration:*
  - Relational database (RDB) configuration PostgreSQL.
  - JPA and Hibernate are used for persistence.

- *Category-Product Relationship:*
  - One-to-many relationship between Category and Product (one category can have multiple products).

- *Server-Side Pagination:*
  - Pagination support for fetching categories and products.

## Requirements

- Spring Boot
- JPA & Hibernate
- Relational Database (PostgreSQL/MySQL)
- Maven for dependency management

## How to Run the Application

   Clone the repository:
   bash
   git clone https://github.com/ganeshpise05/Nimap_Assistment
   cd SpringBoot Restful API



## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.Nimap_Assessment.nimap.NimapApplication class from your IDE.

### Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

shell
mvn spring-boot:run


## GitHub Repository
[SpringBoot Restful API](https://github.com/ganeshpise05/Nimap_Assistment)

## How to Run the Code
- Clone the repository: `git clonehttps://github.com/ganeshpise05/Nimap_Assistment.git`.
- Navigate to the directory: `cd SpringBoot Restful API`.
- Run the application:
  - Using Maven: `mvn spring-boot:run`.
  - Using an IDE: Open the project and run the `NimapApplication` class.
- Ensure the database (PostgreSQL/MySQL) is configured correctly in `application.properties`.

## How to Run the Machine Test
- Use Postman or cURL to interact with the endpoints.
- Test all CRUD operations for both categories and products.
- Check server-side pagination by passing `page` and `size` parameters.


## DB Design

### Category Table:

sql
CREATE TABLE category (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL 
);


CREATE TABLE product (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL 
    FOREIGN KEY (category_id) REFERENCES category(id)
);
