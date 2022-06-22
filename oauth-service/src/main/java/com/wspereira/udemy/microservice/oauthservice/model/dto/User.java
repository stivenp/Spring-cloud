/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.wspereira.udemy.microservice.oauthservice.model.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author stive
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8799656478674716638L;

    private Long id;
    private String username;

    private String password;

    private Boolean enabled;

    private String name;

    private String email;

    private List<Rol> roles;

}