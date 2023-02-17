import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Film, PutFilm} from "../../model/film.model";
import {Acteur} from "../../model/acteur.model";
import {FilmService} from "../../services/film.service";
import {PersonnageService} from "../../services/personnage.service";
import {ActeurService} from "../../services/acteur.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Personnage, PutPersonnage} from "../../model/personnage.model";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-personnage-edit',
  templateUrl: './personnage-edit.component.html',
  styleUrls: ['./personnage-edit.component.scss']
})
export class PersonnageEditComponent {

  personnage: any;
  titre: string = "Créer un personnage";
  films: Film[] = [];
  acteurs: Acteur[] = [];
  formPersonnage: FormGroup = new FormGroup<any>({
    nomPers: new FormControl<string>("", [Validators.required]),
    noFilm: new FormControl<number>(-1, [Validators.required]),
    noAct: new FormControl<number>(-1, [Validators.required])
  });

  constructor(private personnageService: PersonnageService,
              private filmService: FilmService,
              private acteurService: ActeurService,
              private route: ActivatedRoute,
              private router: Router) {

    if (this.route.snapshot.params['film_id'] && this.route.snapshot.params['acteur_id']) {
      this.titre = "Modifier un personnage";

      this.personnageService.getPersonnageByFilmAndActeur(this.route.snapshot.params['film_id'], this.route.snapshot.params['acteur_id']).subscribe({
        next: (response) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement du personnage');
          }
          this.personnage = response.body;
          this.films.push(this.personnage.noFilm);
          this.titre = this.titre + "  dans le film " + this.personnage.noFilm.titre;
          this.acteurs.push(this.personnage.noAct);
          this.titre = this.titre + "  pour l'acteur " + this.personnage.noAct.nomAct;
          this.formPersonnage.controls['nomPers'].setValue(this.personnage.nomPers);
          this.formPersonnage.controls['noFilm'].setValue(this.personnage.noFilm.id);
          this.formPersonnage.controls['noAct'].setValue(this.personnage.noAct.id);
          this.formPersonnage.controls['noFilm'].disable();
          this.formPersonnage.controls['noAct'].disable();
        },
        error: (error: any) => {
          this.router.navigate(['/personnages']);
        }
      });
    } else if (this.route.snapshot.params['film_id']) {
      this.filmService.getFilm(this.route.snapshot.params['film_id']).subscribe({
        next: (response) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement des films');
          }
          this.films.push(response.body);
          this.titre = this.titre + "  dans le film " + response.body.titre;
          this.formPersonnage.controls['noFilm'].setValue(response.body.id);
          this.formPersonnage.controls['noFilm'].disable();
        }
      });

      this.acteurService.getActeursNotIn(this.route.snapshot.params['film_id']).subscribe({
        next: (response: HttpResponse<Acteur[]>) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement des acteurs');
          }
          this.acteurs = response.body;
          console.log(this.acteurs);
        }
      });
    } else if (this.route.snapshot.params['acteur_id']) {
      this.acteurService.getActeur(this.route.snapshot.params['acteur_id']).subscribe({
        next: (response) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement des acteurs');
          }
          this.acteurs.push(response.body);
          this.titre = this.titre + "  pour l'acteur " + response.body.nomAct;
          this.formPersonnage.controls['noAct'].setValue(response.body.id);
          this.formPersonnage.controls['noAct'].disable();
        }
      });

      this.filmService.getFilmsNotPlayedBy(this.route.snapshot.params['acteur_id']).subscribe({
        next: (response: HttpResponse<Film[]>) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement des films');
          }
          this.films = response.body;
        }
      });
    }
  }

  submit() {
    const pp: PutPersonnage = new PutPersonnage(
      this.formPersonnage.controls['noFilm'].value,
      this.formPersonnage.controls['noAct'].value,
      this.formPersonnage.controls['nomPers'].value
    );
    if (this.titre == "Modifier un personnage") {
      this.personnageService.updatePersonnage(pp).subscribe({
        next: (response) => {
          if (response.ok) {
            this.router.navigate(['/personnages/film/' + this.formPersonnage.controls['noFilm'].value]);
          } else {
            alert('KO');
          }
        }
      });
    } else {
      this.personnageService.createPersonnage(pp).subscribe({
        next: (response) => {
          if (response.ok) {
            this.router.navigate(['/personnages/film/' + this.formPersonnage.controls['noFilm'].value]);
          } else {
            alert('KO');
          }
        }
      });
    }
  }
}
