import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FilmService} from "../../services/film.service";

@Component({
  selector: 'app-film-single',
  templateUrl: './film-single.component.html',
  styleUrls: ['./film-single.component.scss']
})
export class FilmSingleComponent {
  film: any;

  constructor(private filmService: FilmService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    if (this.route.snapshot.params['id']) {
      this.filmService.getFilm(this.route.snapshot.params['id']).subscribe({
        next: (response) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement du film');
          }
          this.film = response.body;
        }
      });
    }
  }

  personnages(id: number) {
    this.router.navigate(['/personnages/film/' + id]);
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
}
