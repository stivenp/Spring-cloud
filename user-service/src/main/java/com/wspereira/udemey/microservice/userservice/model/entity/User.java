/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.wspereira.udemey.microservice.userservice.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author stive
 */
@Data
@Entity
@Table(name = "usuarios")
public class User implements Serializable {

    private static final long serialVersionUID = 8799656478674716638L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;

    private String password;

    private Boolean enabled;

    private String name;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Rol> roles;

    private Integer intentos;

}
