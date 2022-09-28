package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Personnage;
import com.example.projetcinemaapi.repository.PersonnageRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonnageService {
    private PersonnageRepository personnageRepository;
    public PersonnageService(PersonnageRepository pr){
        personnageRepository = pr;
    }

}
