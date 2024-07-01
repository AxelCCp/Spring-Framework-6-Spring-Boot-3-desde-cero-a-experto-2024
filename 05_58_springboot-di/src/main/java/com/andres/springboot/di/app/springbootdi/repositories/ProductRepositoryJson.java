package com.andres.springboot.di.app.springbootdi.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.andres.springboot.di.app.springbootdi.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductRepositoryJson implements ProductRepository {

    private List<Product> list;

    //CONSTRUCTOR Q OBTIENE EL RECURSO DE MANERA PROGRAMATICA
    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json");             
        readValueJson(resource);

        /*Resource resource2 = new ClassPathResource("json/product.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            list = Arrays.asList(objectMapper.readValue(resource2.getFile(), Product[].class));
            //list = Arrays.asList(objectMapper.readValue(resource2.getInputStream(), Product[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    //CONSTRUCTOR Q OBTIENE EL RECURSO DE MANERA DECLARATIVA // el resource viene de Appconfig
    public ProductRepositoryJson(Resource resource) {
        readValueJson(resource);
    }

    private void readValueJson(Resource resource) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            list = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }
    
}
