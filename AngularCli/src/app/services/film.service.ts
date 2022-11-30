import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Film} from "../model/film.model";
import {environment} from "../../environments/environment";
import {AuthService} from "../shared/auth.service";

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  public getFilms(): Observable<HttpResponse<Film[]>> {
    return this.http.get<Film[]>(`${environment.baseUrl}/films`, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public getFilm(id: number): Observable<HttpResponse<Film>> {
    return this.http.get<Film>(`${environment.baseUrl}/films/` + id, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public deleteFilm(id: number): void {
    this.http.post<Film>(`${environment.baseUrl}/films/delete/` + id, {headers: this.auth.getTokenHeader()})
  }
}
