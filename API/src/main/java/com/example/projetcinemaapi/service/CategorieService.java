package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Categorie;
import com.example.projetcinemaapi.exception.CategoryCodeAlreadyExistsException;
import com.example.projetcinemaapi.exception.CategoryNotFoundException;
import com.example.projetcinemaapi.repository.CategorieRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

    public String createCategorie(Categorie categorie) { // TODO DTO
        if (categorie.getId() == null) { // TODO repasser sur la fonction
            categorie.setId(categorie.getLibelleCat().toUpperCase().substring(0,2));
            while(categorieRepository.existsById(categorie.getId())) {
                Categorie catBase = categorieRepository.findById(categorie.getId()).orElse(null);
                if(!catBase.getLibelleCat().equals(categorie.getLibelleCat())){
                    char catIdChar = (char)((int)categorie.getId().charAt(1) + 1);
                    if(catIdChar=='[') break;
                    categorie.setId(categorie.getId().substring(0,1)+catIdChar);
                }
            }
        }
        if(!categorieRepository.existsById(categorie.getId())){
           categorieRepository.save(categorie);
        }
        else{
            throw new CategoryCodeAlreadyExistsException();
        }
        return categorie.getId();
    }

    public void updateCategorie(Categorie categorie) { // TODO DTO
        Categorie categorieEntity = getCategorieById(categorie.getId());

        categorieEntity.setLibelleCat(categorie.getLibelleCat());
        categorieEntity.setImage(categorie.getImage());

        categorieRepository.save(categorieEntity);
    }
}
