package com.wspereira.udemy.microservice.itemservice.model.service;

import com.wspereira.udemy.microservice.itemservice.model.Item;
import java.util.List;

public interface ItemService {
    public List<Item> getItems();

    public Item getItem(Long id, Integer amount);

}
