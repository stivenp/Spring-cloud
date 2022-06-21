package com.wspereira.udemy.microservice.productservice.models.repository;


import org.springframework.data.repository.CrudRepository;
import com.wspereira.udemy.microservice.productservice.models.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {



}
