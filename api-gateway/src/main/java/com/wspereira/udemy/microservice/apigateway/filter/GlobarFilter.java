/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.apigateway.filter;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * @author stive
 */
@Component
@Slf4j
public class GlobarFilter implements GlobalFilter,Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Ejecuntando filtro pre");
        exchange.getRequest().mutate().headers(h->h.add("token","12345"));
        
        return chain.filter(exchange).
                then(Mono.fromRunnable(() -> {
                    log.debug("Ejecutando filtro Post");
                   Optional.ofNullable( exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor->{
                       exchange.getResponse().getHeaders().add("token", valor);
                   });
                    exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "red").build());
                    exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
