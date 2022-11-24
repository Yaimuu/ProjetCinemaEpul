package com.example.projetcinemaapi.repository;

import com.example.projetcinemaapi.domains.Realisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RealisateurRepository extends JpaRepository<Realisateur, Integer> {
    Optional<Realisateur> findByNomReaAndPrenRea(String nomRea, String prenRea);

    Optional<Realisateur> findByNomRea(String nomRea);

    Optional<Realisateur> findByPrenRea(String prenRea);
}