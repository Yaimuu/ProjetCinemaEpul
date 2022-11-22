package com.example.projetcinemaapi.controller.user;

import com.example.projetcinemaapi.domains.auth.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurResponse {

    private String username;
    private String role;

    public static UtilisateurResponse fromDomain(User utilisateur) {
        return UtilisateurResponse.builder()
                .username(utilisateur.getLogin())
                .role(utilisateur.getRole())
                .build();
    }
}
