/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.apigateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author stive
 */
@Configuration
public class Config {
    @Bean
public HttpClient httpClient() {
    return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
}
}
