import { Component } from '@angular/core';
import {Personnage, PutPersonnage} from "../../model/personnage.model";
import {PersonnageService} from "../../services/personnage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpResponse} from "@angular/common/http";
import {FilmService} from "../../services/film.service";

@Component({
  selector: 'app-personnage-par-film',
  templateUrl: './personnage-par-film.component.html',
  styleUrls: ['./personnage-par-film.component.scss']
})
export class PersonnageParFilmComponent {

  step = -1;
  personnages: Personnage[] = [];
  displayedColumns: string[] = ['nomAct', 'prenAct', 'dateNaiss', 'dateDeces'];

  constructor(private personnageService: PersonnageService,
              private filmService: FilmService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    if (this.route.snapshot.params['id']) {
      this.personnageService.getPersonnagesByFilm(this.route.snapshot.params['id']).subscribe({
        next: (response: HttpResponse<Personnage[]>) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement des personnages');
          }
          this.personnages = response.body;
        }
      })
    }
  }

  ajouter(id: number) {
    this.router.navigate(['/personnages/create/film/' + id]);
  }

  modifier(p: Personnage) {
    this.router.navigate(['/personnages/edit/' + p.noFilm.id + '/' + p.noAct.id]);
  }

  supprimer(p: Personnage) {
    const pp: PutPersonnage = new PutPersonnage(
      p.noFilm.id,
      p.noAct.id,
      p.nomPers
    );
    this.personnageService.deletePersonnage(pp).subscribe({
      next: (response: any) => {
        if (response.ok) {
          window.location.reload();
        } else {
          alert('KO');
        }
      }
    });
  }

  showFilm(id: number) {
    this.router.navigate(['/films/' + id]);
  }

  acteur(id: number) {
    this.router.navigate(['/acteurs/' + id]);
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
