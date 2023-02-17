import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PersonnageRoutingModule} from "./personnage-routing.module";
import {PersonnageComponent} from "./personnage.component";
import {PersonnageEditComponent} from "./personnage-edit/personnage-edit.component";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import { PersonnageParFilmComponent } from './personnage-par-film/personnage-par-film.component';
import { PersonnageParActeurComponent } from './personnage-par-acteur/personnage-par-acteur.component';
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    PersonnageComponent,
    PersonnageEditComponent,
    PersonnageParFilmComponent,
    PersonnageParActeurComponent
  ],
  imports: [
    CommonModule,
    PersonnageRoutingModule,
    MatExpansionModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,

  ]
})
export class PersonnageModule { }
