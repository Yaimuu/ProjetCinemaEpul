package com.example.projetcinemaapi.domains.auth;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    ADMIN("ROLE_admin");

    final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role getRole(String name) {
        final Optional<Role> first = Arrays.stream(Role.values()).filter(f -> f.getName().equals(name)).findFirst();
        if(first.isPresent()) {
            return first.get();
        }
        throw new RuntimeException("Role " + name + " not found");
    }
}
