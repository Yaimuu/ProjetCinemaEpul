package com.example.projetcinemaapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.projetcinemaapi.service.PersonnageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/personnage")
public class PersonnageControlleur {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PersonnageService personnageService;

    public PersonnageControlleur(PersonnageService personnageService) {
        this.personnageService = personnageService;
    }

    @GetMapping("/list")
    public ResponseEntity getAll() {
        logger.info("REST GET allPersonnage");
        return ResponseEntity.ok(personnageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getPersonnage(@PathVariable int id) {
        logger.info("REST GET personnage with id : {}", id);
        return ResponseEntity.ok(personnageService.getById(id));
    }

    // TODO la clé primaire id n'est pas un int mais une clé composée
    //  donc chiant faut faire gaffe
    /*@PutMapping
    public ResponseEntity updatePersonnage(@RequestBody Personnage personnage) {
        logger.info("REST PUT updatePersonnage with id : {}", );
        personnageService.update(personnage);
        return ResponseEntity.noContent().build();
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity removePersonnage(@PathVariable int id) {
        logger.info("REST DELETE Personnage with id : {}", id);
        personnageService.removeById(id);
        return ResponseEntity.noContent().build();
    }
}