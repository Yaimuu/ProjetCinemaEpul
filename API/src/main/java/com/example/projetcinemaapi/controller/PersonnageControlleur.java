package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.domains.FilmEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/personnage")
public class PersonnageControlleur {

    @GetMapping("/getPersonnages")
    public List<FilmEntity> findAll() {
        return new ArrayList<>();
    }
}
