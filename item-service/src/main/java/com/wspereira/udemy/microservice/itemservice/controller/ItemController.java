/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.itemservice.controller;

import com.wspereira.udemy.microservice.itemservice.model.Item;
import com.wspereira.udemy.microservice.itemservice.model.Product;
import com.wspereira.udemy.microservice.itemservice.model.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author stive
 */
@RefreshScope
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    @Qualifier(value = "itemService")
    private ItemService itemService;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping
    public List<Item> getAll() {
        return itemService.getItems();
    }

    //@HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("{id}/amount/{amount}")
    public Item circuitbreakManual(@PathVariable Long id, @PathVariable Integer amount) {
        //Creando configuracion manual /toma la configuracion de la clase si no tiene yml
        // si tiene yml toma la configuracionn del yml como principal
        return circuitBreakerFactory.create("items")
                .run(() -> itemService.getItem(id, amount), e -> metodoAlternativo(id, amount, e));
    }

    //Nombre de configuracion yml - solo toma la configuracion del yml
    @CircuitBreaker(name = "items",fallbackMethod = "metodoAlternativo")
    @GetMapping("{id}/amount/{amount}/anotacion")
    public Item circuiteBreakAnotacion(@PathVariable Long id, @PathVariable Integer amount) {
        return itemService.getItem(id, amount);
    }
    
    //Nombre de configuracion yml - solo toma la configuracion del yml
    //toca envolver la llamada en un completable future para contabilizar el tiempo
    //Este metodo no hace corto circuito si no que manda un timeOut siempre.
    @TimeLimiter(name = "items",fallbackMethod = "metodoAlternativoFuture")
    @GetMapping("{id}/amount/{amount}/timeLimiter")
    public CompletableFuture<Item> timeLimiter(@PathVariable Long id, @PathVariable Integer amount) {
        return CompletableFuture.supplyAsync(()->itemService.getItem(id, amount));
    }
    //Nombre de configuracion yml - solo toma la configuracion del yml
    //toca envolver la llamada en un completable future para contabilizar el tiempo
    //Este metodo no hace corto circuito si no que manda un timeOut siempre.
    //Si se anota con circuitbreak tambien. ahi s i tendria el comportamiento de circuitbreak
    //funciona el circuit break solo para timeout para excecpciones por errores no entraria a cicuitrbreak
    //Para que  funcione entre los dos solo se debe dejar el fallback en circuitbreak o en ninguno de los dos 
    //No funciona la toleracia a fallos solo funciona el timeout
    @CircuitBreaker(name = "items",fallbackMethod = "metodoAlternativoFuture")
    @TimeLimiter(name = "items",fallbackMethod = "metodoAlternativoFuture")
    @GetMapping("{id}/amount/{amount}/timeLimiterCombine")
    public CompletableFuture<Item> combineOnlyCircuitTimeOut(@PathVariable Long id, @PathVariable Integer amount) {
        return CompletableFuture.supplyAsync(()->itemService.getItem(id, amount));
    }
    //Nombre de configuracion yml - solo toma la configuracion del yml
    //toca envolver la llamada en un completable future para contabilizar el tiempo
    //Este metodo no hace corto circuito si no que manda un timeOut siempre.
    //Si se anota con circuitbreak tambien. ahi s i tendria el comportamiento de circuitbreak
    //funciona el circuit break solo para timeout para excecpciones por errores no entraria a cicuitrbreak
    //Para que  funcione entre los dos solo se debe dejar el fallback en circuitbreak o en ninguno de los dos 
    @CircuitBreaker(name = "items",fallbackMethod = "metodoAlternativoFuture")
    @TimeLimiter(name = "items")
    @GetMapping("{id}/amount/{amount}/combine")
    public CompletableFuture<Item> combineExitoso(@PathVariable Long id, @PathVariable Integer amount) {
        return CompletableFuture.supplyAsync(()->itemService.getItem(id, amount));
    }
    /**
     * Metodo fallback para dar respuesta al timeLimiterr
     * tiene que ser futuro para poder calcular el time 
     * @param id
     * @param amount
     * @param e
     * @return 
     */
    public CompletableFuture<Item> metodoAlternativoFuture(Long id, Integer amount, Throwable e) {
        log.debug("Error {}", e.getMessage());
        Item item = new Item();
        Product producto = new Product();
        item.setAmount(amount);
        producto.setId(id);
        producto.setName("xxxx");
        producto.setPrice(0);
        item.setProduct(producto);
        return CompletableFuture.supplyAsync(()->item);
    }
    
    /**
     * Metodo fallback para dar respuesta si el circuitbreak esta abierto
     * y generando error
     * @param id
     * @param amount
     * @param e
     * @return 
     */
    public Item metodoAlternativo(Long id, Integer amount, Throwable e) {
        log.debug("Error {}", e.getMessage());
        Item item = new Item();
        Product producto = new Product();
        item.setAmount(amount);
        producto.setId(id);
        producto.setName("xxxx");
        producto.setPrice(0);
        item.setProduct(producto);
        return item;
    }

}
