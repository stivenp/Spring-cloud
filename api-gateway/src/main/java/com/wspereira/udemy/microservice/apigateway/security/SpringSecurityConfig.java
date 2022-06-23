/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.apigateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 *
 * @author stive
 */
@EnableWebFluxSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers("/api/security/oaut/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/products", "/api/products/products/{id}",
                        "/api/items", "/api/items/item/{id}/amount/{amount}", "/api/user/user"
                ).permitAll()
                .pathMatchers(HttpMethod.GET, "/api/user/user/{id}").hasAnyRole("ADMIN", "USER")
                .pathMatchers("/api/products/**", "/api/items/**", "/api/user/user/**").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and().csrf().disable()
                .build();
    }
}
