import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpResponse} from "@angular/common/http";
import {PersonnageService} from "../services/personnage.service";
import {Personnage, PutPersonnage} from "../model/personnage.model";

@Component({
  selector: 'app-personnage',
  templateUrl: './personnage.component.html',
  styleUrls: ['./personnage.component.scss']
})
export class PersonnageComponent implements OnInit {

  step = -1;
  personnages: Personnage[] = [];
  displayedColumns: string[] = ['nomAct', 'prenAct', 'dateNaiss', 'dateDeces'];

  constructor(private personnageService: PersonnageService, private router: Router) {
  }

  ngOnInit(): void {
    this.personnageService.getPersonnages().subscribe({
      next: (response: HttpResponse<Personnage[]>) => {
        if (!response.ok || !response.body) {
          throw new Error('Erreur lors du chargement des personnages');
        }
        this.personnages = response.body;
      }
    })
  }

  ajouter() {
    this.router.navigate(['/personnages/create']);
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

  film(id: number) {
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
