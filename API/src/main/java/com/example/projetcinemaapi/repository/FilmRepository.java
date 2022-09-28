package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Query("SELECT f FROM Film f WHERE f.id=?1")
    public List<Film> getFilmsBy(int noRea);
}