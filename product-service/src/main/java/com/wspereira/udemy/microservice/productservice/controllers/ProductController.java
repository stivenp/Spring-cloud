package com.wspereira.udemy.microservice.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wspereira.udemy.microservice.productservice.models.entity.Product;
import com.wspereira.udemy.microservice.productservice.models.service.IProductService;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product detalle(@PathVariable Long id) {
        boolean ok =false;
        if(!ok){
        throw new RuntimeException("No se pudo");}
        return productService.findById(id);
    }
    
}
