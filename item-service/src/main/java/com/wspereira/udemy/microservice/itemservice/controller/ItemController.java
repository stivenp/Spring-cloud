/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.itemservice.controller;

import com.wspereira.udemy.microservice.itemservice.model.Item;
import com.wspereira.udemy.microservice.itemservice.model.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class ItemController {
    @Autowired
    @Qualifier(value = "itemService")
    private ItemService itemService;
    
    @GetMapping
    public List<Item> getAll(){
    return itemService.getItems();
    }
    @GetMapping("{id}/amount/{amount}")
    public Item detalle(@PathVariable Long id,@PathVariable Integer amount){
    return itemService.getItem(id, amount);
    }
}
