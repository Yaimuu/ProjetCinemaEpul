package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.domains.Acteur;
import com.example.projetcinemaapi.repository.ActeurRepository;
import com.example.projetcinemaapi.service.ActeurService;
import com.mysql.fabric.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/acteur")
public class ActeurControlleur {
    private ActeurRepository acteurRepository;
    private final Logger logger = LoggerFactory.getLogger(ActeurControlleur.class);
    public ActeurControlleur(ActeurRepository acteurRepository){
        this.acteurRepository = acteurRepository;
    }
    @PostMapping("/create")
    public ResponseEntity createActor(@RequestBody Acteur acteur){
        ActeurService acteurService = new ActeurService(acteurRepository);
        acteurService.createActeur(acteur);
        return ResponseEntity.ok("Acteur créé");
    }
    @PostMapping("/update")
    public ResponseEntity updateActor(@RequestBody Acteur acteur){
        ActeurService acteurService = new ActeurService(acteurRepository);
        acteurService.updateActeur(acteur);
        return ResponseEntity.ok("Acteur mis à jour");
    }

    @GetMapping("/{id}")
    public ResponseEntity getActor(@PathVariable int id){
        ActeurService acteurService = new ActeurService(acteurRepository);
        Acteur acteur = acteurService.getActeurById(id);
        return ResponseEntity.ok(acteur);
    }

    @GetMapping("/all")
    public ResponseEntity getAllActors(){
        ActeurService acteurService = new ActeurService(acteurRepository);
        List<Acteur> acteurs = acteurService.getAllActors();
        return ResponseEntity.ok(acteurs);
    }
}
