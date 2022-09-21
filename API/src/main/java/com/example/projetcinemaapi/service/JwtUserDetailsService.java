package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.UserEntity;
import com.example.projetcinemaapi.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserEntityRepository userRepostory;

    // on initialise
    @Autowired
    public JwtUserDetailsService(UserEntityRepository UtilisateurRepostory) {
        this.userRepostory = UtilisateurRepostory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity unUtilisateur = null;
        // on accède à l'utilisateur
        unUtilisateur = userRepostory.rechercheNom(username);
        if (unUtilisateur != null) {
            return new User(unUtilisateur.getLogin(), unUtilisateur.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}