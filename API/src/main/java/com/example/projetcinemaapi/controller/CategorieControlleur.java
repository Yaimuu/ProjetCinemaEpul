package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.domains.Categorie;
import com.example.projetcinemaapi.repository.CategorieRepository;
import com.example.projetcinemaapi.service.CategorieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categorie")
public class CategorieControlleur {
    private CategorieRepository categorieRepository;
    private final Logger logger = LoggerFactory.getLogger(CategorieControlleur.class);
    public CategorieControlleur(CategorieRepository categorieRepository){
        this.categorieRepository = categorieRepository;
    }
    @PostMapping("/create")
    public ResponseEntity createCategorie(@RequestBody Categorie categorie){
        CategorieService categorieService = new CategorieService(categorieRepository);
        categorieService.createCategorie(categorie);
        return ResponseEntity.ok("Categorie créé");
    }
    @PostMapping("/update")
    public ResponseEntity updateCategorie(@RequestBody Categorie categorie){
        CategorieService categorieService = new CategorieService(categorieRepository);
        categorieService.updateCategorie(categorie);
        return ResponseEntity.ok("Categorie mis à jour");
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategorie(@PathVariable String id){
        CategorieService categorieService = new CategorieService(categorieRepository);
        Categorie categorie = categorieService.getCategorieById(id);
        return ResponseEntity.ok(categorie);
    }

    @GetMapping("/all")
    public ResponseEntity getAllCategories(){
        CategorieService categorieService = new CategorieService(categorieRepository);
        List<Categorie> categories = categorieService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}


