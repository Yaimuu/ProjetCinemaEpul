import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {FilmComponent} from "./film.component";
import {LoginGuard} from "../guards/login-guard.service";
import {FilmEditComponent} from "./film-edit/film-edit.component";
import {FilmSingleComponent} from "./film-single/film-single.component";

const routes: Routes = [
  {path: '', component: FilmComponent, canActivate: [LoginGuard]},
  {path: 'create', component: FilmEditComponent, canActivate: [LoginGuard]},
  {path: 'edit/:id', component: FilmEditComponent, canActivate: [LoginGuard]},
  {path: ':id', component: FilmSingleComponent , canActivate: [LoginGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FilmRoutingModule { }
