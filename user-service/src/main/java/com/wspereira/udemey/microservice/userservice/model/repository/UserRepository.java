/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wspereira.udemey.microservice.userservice.model.repository;

import com.wspereira.udemey.microservice.userservice.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 *
 * @author stive
 */
@RepositoryRestResource(path="user")
public interface UserRepository extends CrudRepository<User, Long> {
  @RestResource(path="findUser")
    public User findByUsername(@Param("name") String username);
    
    
}
