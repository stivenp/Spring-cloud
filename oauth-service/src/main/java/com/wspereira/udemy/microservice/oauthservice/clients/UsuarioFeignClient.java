/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.oauthservice.clients;

import com.wspereira.udemy.microservice.oauthservice.model.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author stive
 */
@FeignClient(name = "user-service")
public interface UsuarioFeignClient {

    @GetMapping("/user/search/findUser")
    public User findByUsername(@RequestParam String name);
}
