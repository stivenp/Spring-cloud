/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.itemservice.client;

import com.wspereira.udemy.microservice.itemservice.model.Product;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
