import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActeurService} from "../../services/acteur.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpResponse} from "@angular/common/http";
import {Acteur, PutActeur} from "../../model/acteur.model";
import {MatCheckboxChange} from "@angular/material/checkbox";

@Component({
  selector: 'app-acteur-edit',
  templateUrl: './acteur-edit.component.html',
  styleUrls: ['./acteur-edit.component.scss']
})
export class ActeurEditComponent {

  acteur: any;
  titre?: string;
  formActeur: FormGroup = new FormGroup<any>({
    id: new FormControl<number>(-1, [Validators.required]),
    nomAct: new FormControl<string>("", [Validators.required]),
    prenAct: new FormControl<string>("", [Validators.required]),
    dateNaiss: new FormControl<Date>(new Date(), [Validators.required]),
    dateDeces: new FormControl<Date>(new Date()),
  });

  constructor(private acteurService: ActeurService,
              private route: ActivatedRoute,
              private router: Router) {
    if (this.route.snapshot.params['id']) {
      this.acteurService.getActeur(this.route.snapshot.params['id']).subscribe({
        next: (response: HttpResponse<Acteur>) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement du acteur');
          }
          this.titre = 'Modifier un acteur';
          this.acteur = response.body;
          this.formActeur.controls['id'].setValue(response.body.id);
          this.formActeur.controls['nomAct'].setValue(response.body.nomAct);
          this.formActeur.controls['prenAct'].setValue(response.body.prenAct);
          this.formActeur.controls['dateNaiss'].setValue(response.body.dateNaiss);
          this.formActeur.controls['dateDeces'].setValue(response.body.dateDeces);
        },
        error: (error: any) => {
          this.router.navigate(['/acteurs']);
        }
      });
    }
  }

  change(event: MatCheckboxChange) {
    if (event.checked) {
      this.formActeur.controls['dateDeces'].setValue(null);
    } else {
      this.formActeur.controls['dateDeces'].setValue(new Date());
    }
  }

  submit() {
    const acteur: PutActeur = new PutActeur(
      this.formActeur.controls['nomAct'].value,
      this.formActeur.controls['prenAct'].value,
      this.formActeur.controls['dateNaiss'].value,
      this.formActeur.controls['dateDeces'].value
    );
    if (this.formActeur.controls['id'].value === -1) {
      this.acteurService.createActeur(acteur).subscribe({
        next: (response) => {
          if (response.ok) {
            this.router.navigate(['/acteurs']);
          }
        }
      });
    } else {
      this.acteurService.updateActeur(this.formActeur.controls['id'].value, acteur).subscribe({
        next: (response) => {
          if (response.ok) {
            this.router.navigate(['/acteurs/' + this.formActeur.controls['id'].value]);
          } else {
            alert('KO');
          }
        }
      });
      return;
    }
  }

}
