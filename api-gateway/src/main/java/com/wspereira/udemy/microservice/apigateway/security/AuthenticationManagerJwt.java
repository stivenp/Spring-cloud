/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.apigateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 *
 * @author stive
 */
@Component
public class AuthenticationManagerJwt implements ReactiveAuthenticationManager {

    @Value("${config.security.oauth.jwt.key}")
    private String jwtKey;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        return Mono.just(authentication.getCredentials().toString())
                .map(token -> {
                    SecretKey llave = Keys.hmacShaKeyFor(Base64.encodeBase64(jwtKey.getBytes()));
                    return Jwts.parserBuilder().setSigningKey(llave).build().parseClaimsJws(token).getBody();
                })
                .map(claims -> {
                    String username = claims.get("user_name", String.class);
                    List<String> roles = claims.get("authorities", List.class);
                    Collection<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                });
    }

}
