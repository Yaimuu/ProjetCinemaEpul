package com.example.projetcinemaapi.controller.user;

import com.example.projetcinemaapi.domains.auth.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String username;
    private String role;

    public static UserResponse fromDomain(User utilisateur) {
        return UserResponse.builder()
                .username(utilisateur.getLogin())
                .role(utilisateur.getRole())
                .build();
    }
}
