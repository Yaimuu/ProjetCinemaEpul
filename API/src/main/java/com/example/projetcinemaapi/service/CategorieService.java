package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Categorie;
import com.example.projetcinemaapi.exception.CategoryNotFoundException;
import com.example.projetcinemaapi.repository.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories(){
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(String id){
        return categorieRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
}
