package spring.di.app.springboot_di.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.di.app.springboot_di.model.entity.Product;
import spring.di.app.springboot_di.model.service.ProductService;

@RestController
@RequestMapping("/api")
public class SomeController {

    @GetMapping
    public List<Product> list() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public Product show(@PathVariable Long id) {
        return this.service.findById(id);
    }



    private ProductService service = new ProductService();

}
