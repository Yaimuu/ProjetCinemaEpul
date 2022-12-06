package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.domains.request.PersonnageRequest;
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

    @PostMapping("/id")
    public ResponseEntity getPersonnage(@RequestBody PersonnageRequest request) {
        logger.info(this.getClass().getSimpleName() + " getPersonnage " + request);
        return ResponseEntity.ok(personnageService.getPersonnage(request));
    }

    @GetMapping("/film/{id}")
    public ResponseEntity getPersonnagesByFilm(@PathVariable int id) {
        logger.info(this.getClass().getSimpleName() + " getPersonnagesByFilm(" + id + ")");
        return ResponseEntity.ok(personnageService.getPersonnagesByFilm(id));
    }

    @GetMapping("/acteur/{id}")
    public ResponseEntity getPersonnagesByActeur(@PathVariable int id) {
        logger.info(this.getClass().getSimpleName() + " getPersonnagesByActeur(" + id + ")");
        return ResponseEntity.ok(personnageService.getPersonnagesByActeur(id));
    }

    @PostMapping("/create")
    public ResponseEntity createPersonnage(@RequestBody PersonnageRequest request) {
        logger.info(this.getClass().getSimpleName() + " createPersonnage " + request);
        personnageService.create(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity updatePersonnage(@RequestBody PersonnageRequest request) {
        logger.info(this.getClass().getSimpleName() + " updatePersonnage " + request);
        personnageService.update(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity removePersonnage(@RequestBody PersonnageRequest request) {
        logger.info(this.getClass().getSimpleName() + " removePersonnage " + request);
        personnageService.removeById(request);
        return ResponseEntity.noContent().build();
    }
}