import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {FilmComponent} from "../film/film.component";
import {LoginGuard} from "../guards/login-guard.service";
import {FilmEditComponent} from "../film/film-edit/film-edit.component";
import {ActeurComponent} from "./acteur.component";
import {ActeurEditComponent} from "./acteur-edit/acteur-edit.component";

const routes: Routes = [
  {path: '', component: ActeurComponent, canActivate: [LoginGuard]},
  {path: 'edit/:id', component: ActeurEditComponent, canActivate: [LoginGuard]},
  {path: 'create', component: ActeurEditComponent, canActivate: [LoginGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActeurRoutingModule { }
