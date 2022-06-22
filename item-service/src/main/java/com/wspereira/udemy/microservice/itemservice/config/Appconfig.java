package com.wspereira.udemy.microservice.itemservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Appconfig {

    @Bean
    @LoadBalanced
    public RestTemplate registerRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory
                -> factory.configureDefault(
                        id -> {
                            return new Resilience4JConfigBuilder(id)
                                    .circuitBreakerConfig(CircuitBreakerConfig.custom()
                                            .slidingWindowSize(10)
                                            .failureRateThreshold(50)
                                            .waitDurationInOpenState(Duration.ofSeconds(10L))
                                            .permittedNumberOfCallsInHalfOpenState(5)
                                            .build())
                                    .timeLimiterConfig(TimeLimiterConfig.ofDefaults())
                                    .build();

                        });

    }
}
