package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.domains.Acteur;
import com.example.projetcinemaapi.domains.request.ActeurRequest;
import com.example.projetcinemaapi.service.ActeurService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/acteurs")
public class ActeurControlleur {

    private final Logger logger = LoggerFactory.getLogger(ActeurControlleur.class);

    private final ActeurService acteurService;

    @GetMapping
    public ResponseEntity getAllActors() {
        logger.info(this.getClass().getSimpleName() + " getAllActors");
        List<Acteur> acteurs = acteurService.getAllActors();
        return ResponseEntity.ok(acteurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity getActor(@PathVariable int id) {
        logger.info(this.getClass().getSimpleName() + " getActor(" + id + ")");
        Acteur acteur = acteurService.getActeurById(id);
        return ResponseEntity.ok(acteur);
    }

    @PostMapping("/create")
    public ResponseEntity createActor(@RequestBody ActeurRequest request) {
        logger.info(this.getClass().getSimpleName() + " createActor " + request.toString());
        acteurService.createActeur(request);
        return ResponseEntity.ok("Acteur créé");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateActor(@PathVariable int id, @RequestBody ActeurRequest acteur) {
        logger.info(this.getClass().getSimpleName() + " updateActor " + acteur.toString());
        acteurService.updateActeur(id, acteur);
        return ResponseEntity.ok("Acteur mis à jour");
    }
}
