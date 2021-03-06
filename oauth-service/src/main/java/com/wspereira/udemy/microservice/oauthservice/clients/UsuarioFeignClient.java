/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemy.microservice.oauthservice.clients;

import com.wspereira.udemy.microservice.oauthservice.model.dto.UserDe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author stive
 */
@FeignClient(name = "user-service")
public interface UsuarioFeignClient {

    @GetMapping("/user/search/findUser")
    public UserDe findByUsername(@RequestParam(name = "name") String name);

    @PutMapping("/user/{id}")
    public UserDe update(@RequestBody UserDe usuario, @PathVariable(name="id") Long id);
}
