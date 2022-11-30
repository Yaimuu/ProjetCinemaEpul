import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {PutFilm, Film} from "../model/film.model";
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

  public createFilm(film: PutFilm): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${environment.baseUrl}/films/create`, film, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public updateFilm(id: number, film: PutFilm): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${environment.baseUrl}/films/update/` + id, film, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public deleteFilm(id: number): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${environment.baseUrl}/films/delete/` + id, {observe: "response", headers: this.auth.getTokenHeader()})
  }
}
