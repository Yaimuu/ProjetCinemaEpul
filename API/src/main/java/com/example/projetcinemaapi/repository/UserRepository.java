package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User rechercheNom(String login);
}