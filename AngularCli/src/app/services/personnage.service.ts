import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {PutPersonnage, Personnage} from "../model/personnage.model";
import {environment} from "../../environments/environment";
import {AuthService} from "../shared/auth.service";

@Injectable({
  providedIn: 'root'
})
export class PersonnageService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  public getPersonnages(): Observable<HttpResponse<Personnage[]>> {
    return this.http.get<Personnage[]>(`${environment.baseUrl}/personnages`, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public getPersonnage(personnage: Personnage): Observable<HttpResponse<Personnage>> {
    return this.http.post<Personnage>(`${environment.baseUrl}/personnages/id`, personnage,{observe: "response", headers: this.auth.getTokenHeader()})
  }

  public getPersonnagesByFilm(film_id: number): Observable<HttpResponse<Personnage[]>> {
    return this.http.get<Personnage[]>(`${environment.baseUrl}/personnages/film/` + film_id, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public getPersonnagesByActeur(acteur_id: number): Observable<HttpResponse<Personnage[]>> {
    return this.http.get<Personnage[]>(`${environment.baseUrl}/personnages/acteur/` + acteur_id, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public getPersonnageByFilmAndActeur(film_id: number, acteur_id: number): Observable<HttpResponse<Personnage[]>> {
    return this.http.get<Personnage[]>(`${environment.baseUrl}/personnages/film/` + film_id + `/acteur/` + acteur_id, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public createPersonnage(personnage: PutPersonnage): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${environment.baseUrl}/personnages/create`, personnage, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public updatePersonnage(personnage: PutPersonnage): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${environment.baseUrl}/personnages/update`, personnage, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public deletePersonnage(personnage: PutPersonnage): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${environment.baseUrl}/personnages/delete`, personnage,  {observe: "response", headers: this.auth.getTokenHeader()})
  }
}
