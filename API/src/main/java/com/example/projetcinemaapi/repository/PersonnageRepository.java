package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.Personnage;
import com.example.projetcinemaapi.domains.PersonnageId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnageRepository extends JpaRepository<Personnage, PersonnageId> {
    List<Personnage> findById_NoAct(Integer noAct);
    List<Personnage> findById_NoFilm(Integer noFilm);
}