package spring.di.app.springboot_di.model.service;

import java.util.List;
import java.util.stream.Collectors;

import spring.di.app.springboot_di.model.dao.ProductRepository;
import spring.di.app.springboot_di.model.entity.Product;

public class ProductService {

    private ProductRepository repository = new ProductRepository();

    public List<Product> findAll() {
        return this.repository.findAll().stream().map(p -> {
            Double newPrice = p.getPrice() * 1.25d;
            //Product copyProduct = new  Product(p.getId(), p.getName(), newPrice.longValue());       //esto se hace para cuidar la inmutabilidad de los objs. //se sustituye por el metodo clone en Product class.
            Product newProduct = (Product)p.clone();
            newProduct.setPrice(newPrice.longValue());
            return newProduct;
        }).collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return this.repository.findyId(id);
    }

}
