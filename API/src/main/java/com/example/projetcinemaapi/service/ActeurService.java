package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Acteur;
import com.example.projetcinemaapi.domains.request.ActeurRequest;
import com.example.projetcinemaapi.exception.ActorNotFoundException;
import com.example.projetcinemaapi.repository.ActeurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActeurService {

    private final ActeurRepository acteurRepository;

    public List<Acteur> getAllActors(){
        return acteurRepository.findAll();
    }

    public Acteur getActeurById(int id) {
        return acteurRepository.findById(id).orElseThrow(ActorNotFoundException::new);
    }

    public void createActeur(ActeurRequest request) {
        Acteur acteur = Acteur.builder()
                .nomAct(request.getNomAct())
                .prenAct(request.getPrenAct())
                .dateNaiss(request.getDateNaiss())
                .dateDeces(request.getDateDeces())
                .build();
        acteurRepository.save(acteur);
    }

    public void updateActeur(int id, ActeurRequest acteur) {
        Acteur ae = getActeurById(id);

        ae.setDateDeces(acteur.getDateDeces());
        ae.setDateNaiss(acteur.getDateNaiss());
        ae.setNomAct(acteur.getNomAct());
        ae.setPrenAct(acteur.getPrenAct());

        acteurRepository.save(ae);
    }

    public void removeActeur(int id) {
        acteurRepository.deleteById(id);
    }
}
