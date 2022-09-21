package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.PersonnageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnageRepository extends JpaRepository<PersonnageEntity, Integer> {
}