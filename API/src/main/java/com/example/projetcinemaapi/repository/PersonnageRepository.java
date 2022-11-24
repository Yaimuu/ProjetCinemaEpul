package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.Personnage;
import com.example.projetcinemaapi.domains.PersonnageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnageRepository extends JpaRepository<Personnage, PersonnageId> {
}