package spring.di.app.springboot_di.model.dao;

import java.util.List;

import spring.di.app.springboot_di.model.entity.Product;

public interface ProductRepository {


    List<Product>findAll();

    Product findById(Long id);


}
