package com.wspereira.udemy.microservice.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
@PropertySources({
    @PropertySource(value = "file:config/application.yml")})
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

}
