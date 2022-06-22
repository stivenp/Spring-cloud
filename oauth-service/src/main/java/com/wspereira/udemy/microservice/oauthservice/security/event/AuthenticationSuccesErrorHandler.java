/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.oauthservice.security.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author stive
 */
@Component
@Slf4j
public class AuthenticationSuccesErrorHandler implements AuthenticationEventPublisher {

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        log.debug("Usuario logeado {}", user);
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        log.error("Usuario logeado {}", exception.getMessage());
    }

}
