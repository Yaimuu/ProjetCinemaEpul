package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}