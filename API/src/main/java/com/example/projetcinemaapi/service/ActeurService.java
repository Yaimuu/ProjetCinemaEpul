package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Acteur;
import com.example.projetcinemaapi.exception.ActorNotFoundException;
import com.example.projetcinemaapi.repository.ActeurRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    public void createActeur(Acteur acteur) { // TODO DTO
        acteurRepository.save(acteur);
    }

    public void updateActeur(Acteur acteur) { // TODO DTO
        Acteur ae = getActeurById(acteur.getId());

        ae.setDateDeces(acteur.getDateDeces());
        ae.setDateNaiss(acteur.getDateNaiss());
        ae.setNomAct(acteur.getNomAct());
        ae.setPrenAct(acteur.getPrenAct());

        acteurRepository.save(ae);
    }
}
