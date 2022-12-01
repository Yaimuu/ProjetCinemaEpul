package com.example.projetcinemaapi.controller;

import com.example.projetcinemaapi.domains.request.FilmRequest;
import com.example.projetcinemaapi.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/films")
public class FilmController {

    private final Logger logger = LoggerFactory.getLogger(CategorieController.class);

    private final FilmService filmService;

    @GetMapping
    public ResponseEntity getAllFilms() {
        logger.info(this.getClass().getSimpleName() + " getAllFilms");
        return ResponseEntity.ok(filmService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getFilm(@PathVariable int id) {
        logger.info(this.getClass().getSimpleName() + " getFilm(" + id + ")");
        return ResponseEntity.ok(filmService.getFilmById(id));
    }

    @PostMapping("/create")
    public ResponseEntity createFilm(@RequestBody FilmRequest film) {
        logger.info(this.getClass().getSimpleName() + " createFilm" + film.toString());
        filmService.createFilm(film);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateFilm(@PathVariable int id, @RequestBody FilmRequest film) {
        logger.info(this.getClass().getSimpleName() + " updateFilm" + film.toString());
        filmService.updateFilm(id, film);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable int id) {
        logger.info(this.getClass().getSimpleName() + " deleteFilm(" + id + ")");
        filmService.removeFilm(id);
    }
}
