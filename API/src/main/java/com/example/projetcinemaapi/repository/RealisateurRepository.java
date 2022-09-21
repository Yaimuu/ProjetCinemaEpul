package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.RealisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealisateurRepository extends JpaRepository<RealisateurEntity, Integer> {
}