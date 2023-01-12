package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActeurRepository extends JpaRepository<Acteur, Integer> {

    @Query(
        value = "select * from acteur a where a.NoAct not IN (select p.NoAct from personnage p, film f where f.NoFilm = p.NoFilm and f.NoFilm IN (:filmId))",
        nativeQuery = true)
    List<Acteur> getActeurNotIn(int filmId);
}