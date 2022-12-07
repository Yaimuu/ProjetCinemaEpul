import { Component } from '@angular/core';
import {Personnage, PutPersonnage} from "../../model/personnage.model";
import {PersonnageService} from "../../services/personnage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpResponse} from "@angular/common/http";
import {ActeurService} from "../../services/acteur.service";

@Component({
  selector: 'app-personnage-par-acteur',
  templateUrl: './personnage-par-acteur.component.html',
  styleUrls: ['./personnage-par-acteur.component.scss']
})
export class PersonnageParActeurComponent {

  step = -1;
  personnages: Personnage[] = [];
  displayedColumns: string[] = ['nomAct', 'prenAct', 'dateNaiss', 'dateDeces'];

  constructor(private personnageService: PersonnageService,
              private acteurService: ActeurService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    if (this.route.snapshot.params['id']) {
      this.personnageService.getPersonnagesByActeur(this.route.snapshot.params['id']).subscribe({
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
    this.router.navigate(['/personnages/create/acteur/' + id]);
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

  showActeur(id: number) {
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
