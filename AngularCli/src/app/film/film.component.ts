import {Component, OnInit} from '@angular/core';
import {FilmService} from "../services/film.service";
import {HttpResponse} from "@angular/common/http";
import {Film} from "../model/film.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.scss']
})
export class FilmComponent implements OnInit {

  step = -1;
  films: Film[] = [];
  displayedColumns: string[] = ['titre', 'duree', 'dateSortie', 'budget', 'montantRecette', 'modifier', 'supprimer'];

  constructor(private filmService: FilmService, private router: Router) {
  }

  ngOnInit(): void {
    this.filmService.getFilms().subscribe({
      next: (response: HttpResponse<Film[]>) => {
        if (!response.ok || !response.body) {
          throw new Error('Erreur lors du chargement des films');
        }
        this.films = response.body;
      }
    })
  }

  ajouter() {
    this.router.navigate(['/films/create']);
  }

  modifier(id: number) {
    this.router.navigate(['/films/edit/' + id]);
  }

  supprimer(id: number) {
    this.filmService.deleteFilm(id).subscribe({
      next: (response) => {
        if (response.ok) {
          window.location.reload();
        } else {
          alert('KO');
        }
      }
    });
  }

  personnages(id: number) {
    this.router.navigate(['/personnages/film/' + id]);
  }

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }
}
