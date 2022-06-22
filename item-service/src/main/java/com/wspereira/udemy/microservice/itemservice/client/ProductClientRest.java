/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.itemservice.client;

import com.wspereira.udemy.microservice.itemservice.model.Product;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author stive
 */
@FeignClient(name = "product-service")
public interface ProductClientRest {

    @GetMapping("/products")
    public List<Product> getAll();

    @GetMapping("/products/{id}")
    public Product detalle(@PathVariable(value = "id") Long id);
    
    @PostMapping("/products")            
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product pro);
    
    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product edit(@RequestBody Product pro, @PathVariable(value = "id") Long id) ;
    
    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) ;
}
