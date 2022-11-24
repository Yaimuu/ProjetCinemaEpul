package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.service.RealisateurService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/realisateurs")
public class RealisateurController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RealisateurService realisateurService;

    @GetMapping
    public ResponseEntity getAll() {
        logger.info(this.getClass().getSimpleName() + " getAll");
        return ResponseEntity.ok(realisateurService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getRealisateur(@PathVariable int id) {
        logger.info(this.getClass().getSimpleName() + " getRealisateur(" + id + ")");
        return ResponseEntity.ok(realisateurService.getById(id));
    }
}
