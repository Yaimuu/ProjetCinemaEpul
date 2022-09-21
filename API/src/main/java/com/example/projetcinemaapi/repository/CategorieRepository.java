package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<CategorieEntity, String> {
}