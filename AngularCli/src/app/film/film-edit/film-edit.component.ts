import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FilmService} from "../../services/film.service";
import {Film} from "../../model/film.model";
import {HttpResponse} from "@angular/common/http";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-film-edit',
  templateUrl: './film-edit.component.html',
  styleUrls: ['./film-edit.component.scss']
})
export class FilmEditComponent implements OnInit {

  film?: Film;
  titre?: string;
  formFilm: FormGroup = new FormGroup<any>({
    id: new FormControl<string>("", [Validators.required]),
    titre: new FormControl<string>("", [Validators.required]),
    duree: new FormControl<string>("", [Validators.required]),
    dateSortie: new FormControl<string>("", [Validators.required]),
    budget: new FormControl<string>("", [Validators.required]),
    montantRecette: new FormControl<string>("", [Validators.required]),
  });

  ngOnInit(): void {

  }

  constructor(private filmService: FilmService, private route: ActivatedRoute, private router: Router) {
    if (this.route.snapshot.params['id']) {
      this.filmService.getFilm(this.route.snapshot.params['id']).subscribe({
        next: (response: HttpResponse<Film>) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement du film');
          }
          this.titre = 'Modifier un film';
          this.film = response.body
          this.formFilm.controls['id'].setValue(response.body.id);
          this.formFilm.controls['titre'].setValue(response.body.titre);
          this.formFilm.controls['duree'].setValue(response.body.duree);
          this.formFilm.controls['dateSortie'].setValue(response.body.dateSortie);
          this.formFilm.controls['budget'].setValue(response.body.budget);
          this.formFilm.controls['montantRecette'].setValue(response.body.montantRecette);
        },
        error: (error: any) => {
          this.router.navigate(['/films']);
        }
      });
    } else {
      this.router.navigate(['/films']);
    }
  }

  submit() {
    // TODO add route to submit
  }

}
