package com.andres.curso.springboot.backend.backendproducts.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.andres.curso.springboot.backend.backendproducts.entities.Product;
@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {

}
