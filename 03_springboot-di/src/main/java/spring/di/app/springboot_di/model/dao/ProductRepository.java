package spring.di.app.springboot_di.model.dao;

import java.util.Arrays;
import java.util.List;

import spring.di.app.springboot_di.model.entity.Product;

public class ProductRepository {

    public ProductRepository() {
         this.data = Arrays.asList(new Product(1L, "Memoria Corsair", 50_000L), 
                                    new Product(2L, "CPU intel i9", 70_500L),
                                    new Product(3L, "Teclado Razer", 30_500L),
                                    new Product(4L, "Placa madre", 65_500L));
    }


    public List<Product>findAll() {
        return this.data;
    }

    public Product findyId(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    private List<Product> data;



}
