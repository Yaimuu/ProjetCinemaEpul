package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Acteur;
import com.example.projetcinemaapi.domains.Categorie;
import com.example.projetcinemaapi.exception.CategoryCodeAlreadyExistsException;
import com.example.projetcinemaapi.exception.CategoryNotFoundException;
import com.example.projetcinemaapi.repository.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    CategorieRepository categorieRepository;
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

    public void createCategorie(Categorie categ){
        if(!categorieRepository.existsById(categ.getId())){
           categorieRepository.save(categ);
        }
        else{
            throw new CategoryCodeAlreadyExistsException();
        }
    }

    public List<Categorie> getAllCategories(){
        return categorieRepository.findAll();
    }
}
