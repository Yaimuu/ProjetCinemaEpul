import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginGuard} from "../guards/login-guard.service";
import {PersonnageComponent} from "./personnage.component";
import {PersonnageEditComponent} from "./personnage-edit/personnage-edit.component";
import {PersonnageParFilmComponent} from "./personnage-par-film/personnage-par-film.component";
import {PersonnageParActeurComponent} from "./personnage-par-acteur/personnage-par-acteur.component";

const routes: Routes = [
  {path: '', component: PersonnageComponent, canActivate: [LoginGuard]},
  {path: 'film/:id', component: PersonnageParFilmComponent, canActivate: [LoginGuard]},
  {path: 'acteur/:id', component: PersonnageParActeurComponent, canActivate: [LoginGuard]},
  {path: 'edit/:film_id/:acteur_id', component: PersonnageEditComponent, canActivate: [LoginGuard]},
  {path: 'create/film/:film_id', component: PersonnageEditComponent, canActivate: [LoginGuard]},
  {path: 'create/acteur/:acteur_id', component: PersonnageEditComponent, canActivate: [LoginGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonnageRoutingModule { }
