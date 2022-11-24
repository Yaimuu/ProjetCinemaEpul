package com.example.projetcinemaapi.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateurMotPasse {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        //Le mot de passe à encoder avec la méthode BCryptPasswordEncoder
        System.out.println(bCryptPasswordEncoder.encode("secret"));
    }
}