import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
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
}
