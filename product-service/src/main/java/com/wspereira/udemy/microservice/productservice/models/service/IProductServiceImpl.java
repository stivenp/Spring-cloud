package com.wspereira.udemy.microservice.productservice.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wspereira.udemy.microservice.productservice.models.entity.Product;
import com.wspereira.udemy.microservice.productservice.models.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        log.info("Finding product by id: {}", id);
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        log.info("Saving product: {}", product);
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        log.info("Finding all products");
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("detele  product: {}", id);
        productRepository.deleteById(id);
    }

}


