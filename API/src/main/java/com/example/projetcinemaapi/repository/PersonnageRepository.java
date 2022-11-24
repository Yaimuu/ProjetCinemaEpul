package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.Personnage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnageRepository extends JpaRepository<Personnage, Integer> {
}