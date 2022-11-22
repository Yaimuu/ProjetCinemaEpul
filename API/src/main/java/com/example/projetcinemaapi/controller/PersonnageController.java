package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.domains.Personnage;
import com.example.projetcinemaapi.domains.PersonnageId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.projetcinemaapi.service.PersonnageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/personnages")
public class PersonnageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PersonnageService personnageService;

    @GetMapping
    public ResponseEntity getAll() {
        logger.info(this.getClass().getSimpleName() + " getAll");
        return ResponseEntity.ok(personnageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getPersonnage(@PathVariable PersonnageId id) {
        logger.info(this.getClass().getSimpleName() + " getPersonnage(" + id + ")");
        return ResponseEntity.ok(personnageService.getById(id));
    }

    // TODO la clé primaire id n'est pas un int mais une clé composée
    //  donc chiant faut faire gaffe
    /*@PutMapping
    public ResponseEntity updatePersonnage(@RequestBody Personnage personnage) { // TODO DTO
        logger.info("REST PUT updatePersonnage with id : {}", );
        personnageService.update(personnage);
        return ResponseEntity.noContent().build();
    }*/

    @DeleteMapping
    public ResponseEntity removePersonnage(@RequestBody PersonnageId id) {
        logger.info(this.getClass().getSimpleName() + " removePersonnage(" + id + ")");
        personnageService.removeById(id);
        return ResponseEntity.noContent().build();
    }
}