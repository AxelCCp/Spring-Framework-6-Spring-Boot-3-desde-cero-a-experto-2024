package spring.di.app.springboot_di.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.di.app.springboot_di.model.dao.ProductRepository;
import spring.di.app.springboot_di.model.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return this.repository.findAll().stream().map(p -> {
            Double newPrice = p.getPrice() * 1.25d;
            //Product copyProduct = new  Product(p.getId(), p.getName(), newPrice.longValue());       //esto se hace para cuidar la inmutabilidad de los objs. //se sustituye por el metodo clone en Product class.
            Product newProduct = (Product)p.clone();
            newProduct.setPrice(newPrice.longValue());
            return newProduct;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return this.repository.findById(id);
    }

    /*@no necesita el autowired de manera explicita.
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }*/

    /*@Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }*/

    

    

}
