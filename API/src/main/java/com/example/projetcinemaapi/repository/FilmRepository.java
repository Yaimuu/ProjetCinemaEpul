package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Query(
        value = "select * from film f where f.NoFilm not IN (select p.NoFilm from personnage p, acteur a where a.NoAct = p.NoAct and a.NoAct IN (:acteurId))",
        nativeQuery = true)
    List<Film> getFilmsNotPlayedBy(int acteurId);
}