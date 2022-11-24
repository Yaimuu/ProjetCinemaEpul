package com.example.projetcinemaapi.service;

import com.example.projetcinemaapi.domains.Film;
import com.example.projetcinemaapi.domains.request.FilmRequest;
import com.example.projetcinemaapi.exception.FilmNotFoundException;
import com.example.projetcinemaapi.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    private final RealisateurService realisateurService;
    private final CategorieService categorieService;

    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    public Film getFilmById(int id) {
        return filmRepository.findById(id).orElseThrow(FilmNotFoundException::new);
    }

    public void createFilm(FilmRequest request) {
        Film film = Film.builder()
                .titre(request.getTitre())
                .duree(request.getDuree())
                .dateSortie(request.getDateSortie())
                .budget(request.getBudget())
                .montantRecette(request.getMontantRecette())
                .noRea(realisateurService.getById(request.getNoRea()))
                .codeCat(categorieService.getCategorieById(request.getCodeCat()))
                .build();
        filmRepository.save(film);
    }

    public void updateFilm(int id, FilmRequest request) {
        Film filmEntity = getFilmById(id);

        filmEntity.setBudget(request.getBudget());
        filmEntity.setCodeCat(categorieService.getCategorieById(request.getCodeCat()));
        filmEntity.setDuree(request.getDuree());
        filmEntity.setDateSortie(request.getDateSortie());
        filmEntity.setMontantRecette(request.getMontantRecette());
        filmEntity.setNoRea(realisateurService.getById(request.getNoRea()));
        filmEntity.setTitre(request.getTitre());

        filmRepository.save(filmEntity);
    }

    public void removeFilm(int id) {
        filmRepository.deleteById(id);
    }
}
