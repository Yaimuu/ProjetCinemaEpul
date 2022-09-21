package com.example.projetcinemaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    public UserEntity rechercheNom(String login);
}