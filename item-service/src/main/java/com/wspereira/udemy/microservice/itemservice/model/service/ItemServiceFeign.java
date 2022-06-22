/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.itemservice.model.service;

import com.wspereira.udemy.microservice.itemservice.client.ProductClientRest;
import com.wspereira.udemy.microservice.itemservice.model.Item;
import com.wspereira.udemy.microservice.itemservice.model.Product;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author stive
 */
@Service
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductClientRest productClientRest;

    @Override
    public List<Item> getItems() {
        
        return productClientRest.getAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList()); 

    }

    @Override
    public Item getItem(Long id, Integer amount) {
        
        return new Item( productClientRest.detalle(id), amount);
    }

    @Override
    public Product save(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product update(Product pro, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
