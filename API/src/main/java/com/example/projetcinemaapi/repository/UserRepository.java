package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User rechercheNom(String login);
    Optional<User> findByLogin(String login);
}