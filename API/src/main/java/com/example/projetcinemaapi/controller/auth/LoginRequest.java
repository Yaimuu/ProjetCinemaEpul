package com.example.projetcinemaapi.controller.auth;


import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
