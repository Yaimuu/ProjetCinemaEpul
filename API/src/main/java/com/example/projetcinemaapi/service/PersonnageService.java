package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Personnage;
import com.example.projetcinemaapi.exception.PersonnageNotFoundException;
import com.example.projetcinemaapi.repository.PersonnageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonnageService {

    private final PersonnageRepository personnageRepository;

    public List<Personnage> getAll() {
        return personnageRepository.findAll();
    }

    public Personnage getById(int id) {
        return personnageRepository.findById(id).orElseThrow(PersonnageNotFoundException::new);
    }

    public void removeById(int id) {
        personnageRepository.deleteById(id);
    }

}