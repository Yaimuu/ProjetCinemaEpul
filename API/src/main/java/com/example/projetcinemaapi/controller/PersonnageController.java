package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.domains.PersonnageId;
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

    @GetMapping("/id")
    public ResponseEntity getPersonnage(@RequestBody PersonnageRequest request) {
        logger.info(this.getClass().getSimpleName() + " getPersonnage " + request);
        return ResponseEntity.ok(personnageService.getPersonnage(request));
    }

    @PostMapping("/create")
    public ResponseEntity createPersonnage(@RequestBody PersonnageRequest request) {
        logger.info(this.getClass().getSimpleName() + " createPersonnage " + request);
        personnageService.create(request);
        return ResponseEntity.ok("Personnage créé");
    }

    @PostMapping("/update")
    public ResponseEntity updatePersonnage(@RequestBody PersonnageRequest request) {
        logger.info(this.getClass().getSimpleName() + " updatePersonnage " + request);
        personnageService.update(request);
        return ResponseEntity.ok("Personnage mis à jour");
    }

    @DeleteMapping
    public ResponseEntity removePersonnage(@RequestBody PersonnageId id) {
        logger.info(this.getClass().getSimpleName() + " removePersonnage(" + id + ")");
        personnageService.removeById(id);
        return ResponseEntity.noContent().build();
    }
}