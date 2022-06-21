/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.apigateway.filter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 *
 * @author stive
 */
@Component
@Slf4j
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.ConfigFilter> {

    public EjemploGatewayFilterFactory() {
        super(ConfigFilter.class);
    }

    /**
     *
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(ConfigFilter config) {
        log.info("Ejecuntando pre gateway filter factory {}", config.mensaje);
        return new OrderedGatewayFilter((exchange, chain) -> {

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Optional.ofNullable(config.cookieValue).ifPresent(cookie -> {
                    exchange.getResponse().addCookie(ResponseCookie.from(config.cookieName, cookie).build());
                });
                log.info("Ejecuntando post gateway filter factory {}", config.cookieValue);
            }));
        }, 2);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("mensaje", "cookieName", "cookieValue");
    }
//   Si se quiere poner un nombre diferente al de la clase
//    @Override
//    public String name() {
//        return "EjemploCookie"; //To change body of generated methods, choose Tools | Templates.
//    }

    public static class ConfigFilter {

        private String mensaje;
        private String cookieValue;
        private String cookieName;

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getCookieValue() {
            return cookieValue;
        }

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }

    }

}
