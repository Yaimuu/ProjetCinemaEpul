package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Film;
import com.example.projetcinemaapi.exception.FilmCodeAlreadyExistsException;
import com.example.projetcinemaapi.exception.FilmNotFoundException;
import com.example.projetcinemaapi.repository.FilmRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    public Film getFilmById(int id) {
        return filmRepository.findById(id).orElseThrow(FilmNotFoundException::new);
    }

    public void createFilm(Film film){
        if (!filmRepository.existsById(film.getId())) {
            filmRepository.save(film);
        } else {
            throw new FilmCodeAlreadyExistsException();
        }
    }

    public void updateFilm(Film film) {
        Film filmEntity = getFilmById(film.getId());

        filmEntity.setBudget(film.getBudget());
        filmEntity.setCodeCat(film.getCodeCat());
        filmEntity.setDuree(film.getDuree());
        filmEntity.setDateSortie(film.getDateSortie());
        filmEntity.setMontantRecette(film.getMontantRecette());
        filmEntity.setNoRea(film.getNoRea());
        filmEntity.setTitre(film.getTitre());

        filmRepository.save(filmEntity);
    }
}
