package com.example.projetcinemaapi.domains.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "login", nullable = false, length = 30)
    private String login;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role", nullable = false, length = 30)
    private String role;
}