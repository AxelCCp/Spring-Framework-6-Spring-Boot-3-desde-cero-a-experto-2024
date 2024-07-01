package spring.di.app.springboot_di.model.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import spring.di.app.springboot_di.model.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository{


    public ProductRepositoryImpl() {
         this.data = Arrays.asList(new Product(1L, "Memoria Corsair", 50_000L), 
                                    new Product(2L, "CPU intel i9", 70_500L),
                                    new Product(3L, "Teclado Razer", 30_500L),
                                    new Product(4L, "Placa madre", 65_500L));
    }


    @Override
    public List<Product>findAll() {
        return this.data;
    }

    
    @Override
    public Product findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    
    private List<Product> data;


}
