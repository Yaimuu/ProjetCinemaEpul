package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, String> {
}