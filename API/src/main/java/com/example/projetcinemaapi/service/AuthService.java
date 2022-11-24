package com.example.projetcinemaapi.service;


import com.example.projetcinemaapi.domains.Role;
import com.example.projetcinemaapi.domains.auth.User;
import com.example.projetcinemaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("Nom d'utilisateur inconnu");
    }

    /** Get Authenticated User
     * Get currently authenticated user from Spring Security API
     * @return The authenticated user
     */
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUsername(auth.getName());
    }


    public boolean isAuthenticated(String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return isAdmin(auth) || isAuthenticatedCandidat(username, auth);
    }
    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .anyMatch(a -> Role.ADMIN.getName().equals(a.getAuthority()));
    }

    private boolean isAuthenticatedCandidat(String username, Authentication authentication) {
        return authentication.getName().equals(username);
    }
}