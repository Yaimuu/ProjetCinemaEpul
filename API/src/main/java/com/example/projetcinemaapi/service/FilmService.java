package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Film;
import com.example.projetcinemaapi.exception.FilmCodeAlreadyExistsException;
import com.example.projetcinemaapi.exception.FilmNotFoundException;
import com.example.projetcinemaapi.repository.FilmRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    FilmRepository filmRepository;
    public FilmService(FilmRepository cr){
        filmRepository = cr;
    }

    public Film getFilmById(int id){
        return filmRepository.findById(id).orElse(null);
    }

    public void updateFilm(Film film){
        Film filmEntity = filmRepository.findById(film.getId()).orElseThrow(FilmNotFoundException::new);
        filmEntity.setBudget(film.getBudget());
        filmEntity.setCodeCat(film.getCodeCat());
        filmEntity.setDuree(film.getDuree());
        filmEntity.setDateSortie(film.getDateSortie());
        filmEntity.setMontantRecette(film.getMontantRecette());
        filmEntity.setNoRea(film.getNoRea());
        filmEntity.setTitre(film.getTitre());
        filmRepository.save(filmEntity);
    }

    public void createCategory(Film film){
        if(!filmRepository.existsById(film.getId())){
            filmRepository.save(film);
        }
        else{
            throw new FilmCodeAlreadyExistsException();
        }
    }
}
