package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Acteur;
import com.example.projetcinemaapi.exception.ActorNotFoundException;
import com.example.projetcinemaapi.repository.ActeurRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActeurService {

    private ActeurRepository acteurRepository;
    public ActeurService(ActeurRepository ar){
        acteurRepository = ar;
    }

    public Acteur getActeurById(int id){
        return acteurRepository.findById(id).orElse(null);
    }

    public List<Acteur> getAllActors(){
        return acteurRepository.findAll();
    }

    public void updateActeur(Acteur acteur){
        Acteur ae = acteurRepository.findById(acteur.getId()).orElseThrow(ActorNotFoundException::new);
        ae.setDateDeces(acteur.getDateDeces());
        ae.setDateNaiss(acteur.getDateNaiss());
        ae.setNomAct(acteur.getNomAct());
        ae.setPrenAct(acteur.getPrenAct());
        acteurRepository.save(ae);
    }

    public void createActeur(Acteur acteur){
        acteurRepository.save(acteur);
    }


}
