package com.wspereira.udemy.microservice.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
@EnableConfigServer
@SpringBootApplication

@PropertySources({
    @PropertySource(value = "file:config/application.yml")})
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
