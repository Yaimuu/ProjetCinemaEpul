package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.User;
import com.example.projetcinemaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByLogin(username)
                .orElse(null);
    }
}
