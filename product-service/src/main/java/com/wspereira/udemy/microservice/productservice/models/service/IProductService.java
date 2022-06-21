package com.wspereira.udemy.microservice.productservice.models.service;

import java.util.List;
import com.wspereira.udemy.microservice.productservice.models.entity.Product;

public interface IProductService {

    Product findById(Long id);

    Product save(Product product);

    List<Product> findAll();


}
