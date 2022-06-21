package com.wspereira.udemy.microservice.itemservice.model.service;

import com.wspereira.udemy.microservice.itemservice.model.Item;
import com.wspereira.udemy.microservice.itemservice.model.Product;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service(value = "itemService")
@Primary
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Item> getItems() {
        List<Product> products
                = Arrays.asList(restTemplate.getForObject("http://product-service/products", Product[].class)); // Tools
        // |
        return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList()); // Templates.
    }

    @Override
    public Item getItem(Long id, Integer amount) {
        Map<String, String> pathVariable = new HashMap<String, String>();
        pathVariable.put("id", id.toString());
        Product product = restTemplate.getForObject("http://product-service/products/{id}",Product.class, pathVariable);
        return new Item(product, amount);
    }

}
