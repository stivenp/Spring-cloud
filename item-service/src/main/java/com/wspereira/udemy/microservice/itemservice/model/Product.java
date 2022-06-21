/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.itemservice.model;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author stive
 */
@Data
public class Product {
    
    private long id;
    private String name;
    private double price;
    private Date createdAt;
}
