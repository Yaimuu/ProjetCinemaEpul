package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.projetcinemaapi.domains.User unUtilisateur = null;
        // on accède à l'utilisateur
        unUtilisateur = userRepository.findByLogin(username).orElse(null);
        if (unUtilisateur != null) {
            return new User(unUtilisateur.getLogin(), unUtilisateur.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}