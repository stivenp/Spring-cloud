package com.wspereira.udemy.microservice.itemservice.model.service;

import com.wspereira.udemy.microservice.itemservice.model.Item;
import com.wspereira.udemy.microservice.itemservice.model.Product;
import java.util.List;

public interface ItemService {
    public List<Item> getItems();

    public Item getItem(Long id, Integer amount);
    
    public Product save(Product product);
    public Product update(Product pro, Long id);
    public void delete(Long id);
}
