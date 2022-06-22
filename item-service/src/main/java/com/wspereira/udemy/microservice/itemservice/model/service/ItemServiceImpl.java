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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        Product product = restTemplate.getForObject("http://product-service/products/{id}", Product.class, pathVariable);
        return new Item(product, amount);
    }

    @Override
    public Product save(Product product) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> exchange = restTemplate.exchange("http://product-service/products", HttpMethod.POST, body, Product.class);
        Product response = exchange.getBody();
        return response;
    }

    @Override
    public Product update(Product pro, Long id) {
        HttpEntity<Product> body = new HttpEntity<Product>(pro);
        Map<String, String> pathVariable = new HashMap<String, String>();
        pathVariable.put("id", id.toString());
        ResponseEntity<Product> exchange = restTemplate.exchange("http://product-service/products/{id}", HttpMethod.PUT, body, Product.class, pathVariable);
        Product response = exchange.getBody();
        return response;
    }

    @Override
    public void delete(Long id) {

        Map<String, String> pathVariable = new HashMap<String, String>();
        pathVariable.put("id", id.toString());
        restTemplate.delete("http://product-service/products/{id}", pathVariable);
    }

}
