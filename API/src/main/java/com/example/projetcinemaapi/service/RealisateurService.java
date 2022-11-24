package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Realisateur;
import com.example.projetcinemaapi.exception.RealisateurNotFoundException;
import com.example.projetcinemaapi.repository.RealisateurRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealisateurService {

    private final RealisateurRepository realisateurRepository;

    public List<Realisateur> getAll() {
        return realisateurRepository.findAll();
    }

    public Realisateur getById(int id) {
        return realisateurRepository.findById(id).orElseThrow(RealisateurNotFoundException::new);
    }

    public Realisateur getByNom(String nom) {
        return realisateurRepository.findByNomRea(nom).orElse(null);
    }

    public Realisateur getByPrenom(String prenom) {
        return realisateurRepository.findByPrenRea(prenom).orElse(null);
    }

    public Realisateur getByNomAndPrenom(String nom, String prenom) {
        return realisateurRepository.findByNomReaAndPrenRea(nom, prenom).orElse(null);
    }

    public void updateRealisateur(Realisateur realisateur) {
        Realisateur realisateurEntity = getById(realisateur.getId());

        realisateurEntity.setNomRea(realisateur.getNomRea());
        realisateurEntity.setPrenRea(realisateur.getPrenRea());

        realisateurRepository.save(realisateurEntity);
    }
}
