package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
}