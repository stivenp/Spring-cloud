/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.oauthservice.service;

import com.wspereira.udemy.microservice.oauthservice.clients.UsuarioFeignClient;
import com.wspereira.udemy.microservice.oauthservice.model.dto.UserDe;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author stive
 */
@Service
@Slf4j
public class UserService implements IUserService,UserDetailsService {
    
    @Autowired
    private UsuarioFeignClient client;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDe usuario = client.findByUsername(username);
        if (usuario == null) {
            log.error("Error en el login, No existe el usuario {}", username);
            throw new UsernameNotFoundException("Error en el login, No existe el usuario '" + username + "'en el sistema");
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authotiry -> log.info("rol{}", authotiry.getAuthority()))
                .collect(Collectors.toList());
        log.debug("usuario authenticado {}", username);
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }

    @Override
    public UserDe findByUsername(String username) {
       return client.findByUsername(username);
    }
    
}
