/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.itemservice.controller;

import com.wspereira.udemy.microservice.itemservice.model.Item;
import com.wspereira.udemy.microservice.itemservice.model.Product;
import com.wspereira.udemy.microservice.itemservice.model.service.ItemService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author stive
 */
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    @Qualifier(value = "itemService")
    private ItemService itemService;
    
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;
    
    @GetMapping
    public List<Item> getAll() {
        return itemService.getItems();
    }

    //@HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("{id}/amount/{amount}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer amount) {
        return circuitBreakerFactory.create("items")
                .run(() -> itemService.getItem(id, amount));//, e -> metodoAlternativo(id, amount, e));
    }
    
    public Item metodoAlternativo(Long id, Integer amount, Throwable e) {
        log.debug("Error {}", e.getMessage());
        Item item = new Item();
        Product producto = new Product();
        item.setAmount(amount);
        producto.setId(id);
        producto.setName("xxxx");
        producto.setPrice(0);
        item.setProduct(producto);
        return item;
    }
    
}
