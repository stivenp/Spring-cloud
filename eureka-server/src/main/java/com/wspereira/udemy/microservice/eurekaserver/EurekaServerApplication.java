package com.wspereira.udemy.microservice.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
@EnableEurekaServer
@SpringBootApplication

@PropertySources({
    @PropertySource(value = "file:config/application.yml")})
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
