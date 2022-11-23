package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Personnage;
import com.example.projetcinemaapi.domains.PersonnageId;
import com.example.projetcinemaapi.domains.request.PersonnageRequest;
import com.example.projetcinemaapi.exception.PersonnageNotFoundException;
import com.example.projetcinemaapi.repository.PersonnageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonnageService {

    private final PersonnageRepository personnageRepository;
    private final ActeurService acteurService;
    private final FilmService filmService;

    public List<Personnage> getAll() {
        return personnageRepository.findAll();
    }

    private Personnage getById(PersonnageId id) {
        return personnageRepository.findById(id).orElseThrow(PersonnageNotFoundException::new);
    }

    public Personnage getPersonnage(PersonnageRequest request) {
        return getById(new PersonnageId(request.getNoFilm(), request.getNoAct()));
    }

    public void create(PersonnageRequest request) {
        Personnage personnage = Personnage.builder()
                .id(new PersonnageId(request.getNoFilm(), request.getNoAct()))
                .noAct(acteurService.getActeurById(request.getNoAct()))
                .noFilm(filmService.getFilmById(request.getNoFilm()))
                .nomPers(request.getNomPers())
                .build();
        personnageRepository.save(personnage);
    }

    public void update(PersonnageRequest request) {
        Personnage personnage = getById(new PersonnageId(request.getNoFilm(), request.getNoAct()));
        personnage.setNomPers(request.getNomPers());
        personnageRepository.save(personnage);
    }

    public void removeById(PersonnageId id) {
        personnageRepository.deleteById(id);
    }
}