package com.example.projetcinemaapi.service;


import com.example.projetcinemaapi.domains.Realisateur;
import com.example.projetcinemaapi.exception.RealisateurNotFoundException;
import com.example.projetcinemaapi.repository.RealisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class RealisateurService {
    private RealisateurRepository realisateurRepository;
    public RealisateurService(RealisateurRepository realisateurRepository){
        this.realisateurRepository = realisateurRepository;
    }

    public Realisateur getRealisateurById(int id){
        return realisateurRepository.findById(id).orElse(null);
    }

    public void updateRealisateur(Realisateur realisateur){
        Realisateur realisateurEntity = realisateurRepository.findById(realisateur.getId()).orElseThrow(RealisateurNotFoundException::new);
        realisateurEntity.setNomRea(realisateur.getNomRea());
        realisateurEntity.setPrenRea(realisateur.getPrenRea());
        realisateurRepository.save(realisateurEntity);
    }
}
