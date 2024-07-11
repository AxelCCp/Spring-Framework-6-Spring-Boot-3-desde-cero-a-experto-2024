package com.andres.curso.springboot.backend.backendproducts.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.andres.curso.springboot.backend.backendproducts.entities.Product;

@CrossOrigin(origins = "http://localhost:5173")
@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {

}
