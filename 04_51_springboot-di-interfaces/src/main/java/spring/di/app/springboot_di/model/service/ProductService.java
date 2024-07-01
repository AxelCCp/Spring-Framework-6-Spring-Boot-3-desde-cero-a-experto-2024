package spring.di.app.springboot_di.model.service;

import java.util.List;

import spring.di.app.springboot_di.model.entity.Product;

public interface ProductService {

     List<Product> findAll();

     Product findById(Long id);

}
