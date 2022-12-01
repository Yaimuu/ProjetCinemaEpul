import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FilmService} from "../../services/film.service";
import {PutFilm, Film} from "../../model/film.model";
import {HttpResponse} from "@angular/common/http";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Realisateur} from "../../model/realisateur.model";
import {RealisateurService} from "../../services/realisateur.service";
import {CategorieService} from "../../services/categorie.service";
import {Categorie} from "../../model/categorie.model";

@Component({
  selector: 'app-film-edit',
  templateUrl: './film-edit.component.html',
  styleUrls: ['./film-edit.component.scss']
})
export class FilmEditComponent implements OnInit {

  film: any;
  titre?: string;
  realisateurs?: Realisateur[] = [];
  categories?: Categorie[] = [];
  formFilm: FormGroup = new FormGroup<any>({
    id: new FormControl<number>(-1, [Validators.required]),
    titre: new FormControl<string>("", [Validators.required]),
    duree: new FormControl<number>(120, [Validators.required]),
    dateSortie: new FormControl<Date>(new Date(), [Validators.required]),
    budget: new FormControl<number>(1000000, [Validators.required]),
    montantRecette: new FormControl<number>(10000000, [Validators.required]),
    realisateur: new FormControl<number>(-1, [Validators.required]),
    categorie: new FormControl<string>("", [Validators.required]),
  });

  constructor(private filmService: FilmService,
              private realisateurService: RealisateurService,
              private categorieService: CategorieService,
              private route: ActivatedRoute,
              private router: Router) {
    if (this.route.snapshot.params['id']) {
      this.filmService.getFilm(this.route.snapshot.params['id']).subscribe({
        next: (response: HttpResponse<Film>) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement du film');
          }
          this.titre = 'Modifier un film';
          this.film = response.body;
          this.formFilm.controls['id'].setValue(response.body.id);
          this.formFilm.controls['titre'].setValue(response.body.titre);
          this.formFilm.controls['duree'].setValue(response.body.duree);
          this.formFilm.controls['dateSortie'].setValue(response.body.dateSortie);
          this.formFilm.controls['budget'].setValue(response.body.budget);
          this.formFilm.controls['montantRecette'].setValue(response.body.montantRecette);
          this.formFilm.controls['realisateur'].setValue(response.body.noRea.id);
          this.formFilm.controls['categorie'].setValue(response.body.codeCat.id);
        },
        error: (error: any) => {
          this.router.navigate(['/films']);
        }
      });
    }
  }

  ngOnInit(): void {
    this.realisateurService.getRealisateurs().subscribe({
      next: (response) => {
        if (!response.ok || !response.body) {
          throw new Error('Erreur lors du chargement des réalisateurs');
        }
        this.realisateurs = response.body;
      }
    });

    this.categorieService.getCategories().subscribe({
      next: (response) => {
        if (!response.ok || !response.body) {
          throw new Error('Erreur lors du chargement des réalisateurs');
        }
        this.categories = response.body;
      }
    });
  }

  submit() {
    const film: PutFilm = new PutFilm(
      this.formFilm.controls['titre'].value,
      this.formFilm.controls['duree'].value,
      this.formFilm.controls['dateSortie'].value,
      this.formFilm.controls['budget'].value,
      this.formFilm.controls['montantRecette'].value,
      this.formFilm.controls['realisateur'].value,
      this.formFilm.controls['categorie'].value
    );
    if (this.formFilm.controls['id'].value === -1) {
      this.filmService.createFilm(film).subscribe({
        next: (response) => {
          if (response.ok) {
            this.router.navigate(['/films']);
          }
        }
      });
    } else {
      this.filmService.updateFilm(this.formFilm.controls['id'].value, film).subscribe({
        next: (response) => {
          if (response.ok) {
            this.router.navigate(['/films']);
          } else {
            alert('KO');
          }
        }
      });
      return;
    }
  }

}
