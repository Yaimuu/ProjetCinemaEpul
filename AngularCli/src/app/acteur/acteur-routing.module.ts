import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginGuard} from "../guards/login-guard.service";
import {ActeurComponent} from "./acteur.component";
import {ActeurEditComponent} from "./acteur-edit/acteur-edit.component";
import {ActeurSingleComponent} from "./acteur-single/acteur-single.component";

const routes: Routes = [
  {path: '', component: ActeurComponent, canActivate: [LoginGuard]},
  {path: 'create', component: ActeurEditComponent, canActivate: [LoginGuard]},
  {path: 'edit/:id', component: ActeurEditComponent, canActivate: [LoginGuard]},
  {path: ':id', component: ActeurSingleComponent, canActivate: [LoginGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActeurRoutingModule { }
