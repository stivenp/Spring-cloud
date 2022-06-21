package com.wspereira.udemy.microservice.itemservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Product product;

    private Integer amount;


    public Double getTotalPrice() {
        return product.getPrice() * amount.doubleValue();
    }
}
