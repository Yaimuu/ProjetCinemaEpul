package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.auth.Role;
import com.example.projetcinemaapi.domains.auth.User;
import com.example.projetcinemaapi.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserService userService;
    private final JwtUtil jwtUtil;

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

    public boolean isAdminOrAuthenticatedCandidat(String username, String authorization) {
        String token = authorization.substring("Bearer ".length());
        return isAdmin(token) || isAuthenticatedCandidat(username, token);
    }
    private boolean isAdmin(String token) {
        return jwtUtil.getRole(token) == Role.ADMIN;
    }

    private boolean isAuthenticatedCandidat(String username, String token) {
        return jwtUtil.getUserName(token).equals(username);
    }


}
