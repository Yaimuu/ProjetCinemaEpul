import { NgModule } from '@angular/core';
// @ts-ignore
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import {LoginComponent} from "./authentication/login/login.component";
import {LoginGuard} from "./guards/login-guard.service";
import {FilmComponent} from "./film/film.component";
import {ActeurComponent} from "./acteur/acteur.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'films', component: FilmComponent, canActivate: [LoginGuard] },
  { path: 'acteurs', component: ActeurComponent, canActivate: [LoginGuard] },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
