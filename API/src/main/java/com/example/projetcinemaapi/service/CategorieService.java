package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.controller.ActeurControlleur;
import com.example.projetcinemaapi.domains.Acteur;
import com.example.projetcinemaapi.domains.Categorie;
import com.example.projetcinemaapi.exception.CategoryCodeAlreadyExistsException;
import com.example.projetcinemaapi.exception.CategoryNotFoundException;
import com.example.projetcinemaapi.repository.CategorieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    CategorieRepository categorieRepository;
    private final Logger logger = LoggerFactory.getLogger(CategorieService.class);
    public CategorieService(CategorieRepository cr){
        categorieRepository = cr;
    }

    public Categorie getCategorieById(String id){
        return categorieRepository.findById(id).orElse(null);
    }

    public void updateCategorie(Categorie categ){
        Categorie categorieEntity = categorieRepository.findById(categ.getId()).orElseThrow(CategoryNotFoundException::new);
        categorieEntity.setLibelleCat(categ.getLibelleCat());
        categorieEntity.setImage(categ.getImage());
        categorieRepository.save(categorieEntity);
    }

    public String createCategorie(Categorie categ){
        if(categ.getId() == null){
            categ.setId(categ.getLibelleCat().toUpperCase().substring(0,2));
            while(categorieRepository.existsById(categ.getId())) {
                Categorie catBase = categorieRepository.findById(categ.getId()).orElse(null);
                if(!catBase.getLibelleCat().equals(categ.getLibelleCat())){
                    char catIdChar = (char)((int)categ.getId().charAt(1) + 1);
                    if(catIdChar=='[') break;
                    categ.setId(categ.getId().substring(0,1)+catIdChar);
                }
            }
        }
        if(!categorieRepository.existsById(categ.getId())){
           categorieRepository.save(categ);
        }
        else{
            throw new CategoryCodeAlreadyExistsException();
        }
        return categ.getId();
    }

    public List<Categorie> getAllCategories(){
        return categorieRepository.findAll();
    }
}
