package com.wspereira.udemy.microservice.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wspereira.udemy.microservice.productservice.models.entity.Product;
import com.wspereira.udemy.microservice.productservice.models.service.IProductService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    public Product detalle(@PathVariable Long id) throws InterruptedException {
        // romper
        if (id.equals(10l)) {
            throw new IllegalStateException("Producto no encontrado");
        }
        //genera un timeout
        if (id.equals(7L)) {
            TimeUnit.SECONDS.sleep(5L);
        }

        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product pro) {

        return productService.save(pro);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product edit(@RequestBody Product pro, @PathVariable Long id) {
        Product findById = productService.findById(id);
        findById.setName(pro.getName());
        findById.setPrice(pro.getPrice());
        return productService.save(findById);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
