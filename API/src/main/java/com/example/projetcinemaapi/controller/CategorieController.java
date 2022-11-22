package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.domains.Categorie;
import com.example.projetcinemaapi.repository.CategorieRepository;
import com.example.projetcinemaapi.service.CategorieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/categories")
public class CategorieController {

    private final Logger logger = LoggerFactory.getLogger(CategorieController.class);

    private final CategorieService categorieService;

    @GetMapping
    public ResponseEntity getAllCategories() {
        logger.info(this.getClass().getSimpleName() + " getAllCategories");
        List<Categorie> categories = categorieService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategorie(@PathVariable String id) {
        logger.info(this.getClass().getSimpleName() + " getCategorie(" + id + ")");
        Categorie categorie = categorieService.getCategorieById(id);
        return ResponseEntity.ok(categorie);
    }

    @PostMapping("/create")
    public ResponseEntity createCategorie(@RequestBody Categorie categorie) { // TODO make DTO for create
        logger.info(this.getClass().getSimpleName() + " createCategorie" + categorie.toString());
        categorieService.createCategorie(categorie);
        return ResponseEntity.ok("Catégorie créée");
    }

    @PostMapping("/update")
    public ResponseEntity updateCategorie(@RequestBody Categorie categorie) { // TODO make DTO for update
        logger.info(this.getClass().getSimpleName() + " updateCategorie" + categorie.toString());
        categorieService.updateCategorie(categorie);
        return ResponseEntity.ok("Catégorie mise à jour");
    }
}


