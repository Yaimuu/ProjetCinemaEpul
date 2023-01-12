import {Component, OnInit} from '@angular/core';
import {Acteur} from "../model/acteur.model";
import {ActeurService} from "../services/acteur.service";
import {Router} from "@angular/router";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-acteur',
  templateUrl: './acteur.component.html',
  styleUrls: ['./acteur.component.scss']
})
export class ActeurComponent implements OnInit {

  step = -1;
  acteurs: Acteur[] = [];
  displayedColumns: string[] = ['nomAct', 'prenAct', 'dateNaiss', 'dateDeces'];

  constructor(private acteurService: ActeurService, private router: Router) {
  }

  ngOnInit(): void {
    this.acteurService.getActeurs().subscribe({
      next: (response: HttpResponse<Acteur[]>) => {
        if (!response.ok || !response.body) {
          throw new Error('Erreur lors du chargement des acteurs');
        }
        this.acteurs = response.body;
      }
    })
  }

  ajouter() {
    this.router.navigate(['/acteurs/create']);
  }

  modifier(id: number) {
    this.router.navigate(['/acteurs/edit/' + id]);
  }

  supprimer(id: number) {
    this.acteurService.deleteActeur(id).subscribe({
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
    this.router.navigate(['/personnages/acteur/' + id]);
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
