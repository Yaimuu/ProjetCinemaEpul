import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {PutActeur, Acteur} from "../model/acteur.model";
import {environment} from "../../environments/environment";
import {AuthService} from "../shared/auth.service";

@Injectable({
  providedIn: 'root'
})
export class ActeurService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  public getActeurs(): Observable<HttpResponse<Acteur[]>> {
    return this.http.get<Acteur[]>(`${environment.baseUrl}/acteurs`, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public getActeur(id: number): Observable<HttpResponse<Acteur>> {
    return this.http.get<Acteur>(`${environment.baseUrl}/acteurs/` + id, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public getActeursNotIn(film_id: number): Observable<HttpResponse<Acteur[]>> {
    return this.http.get<Acteur[]>(`${environment.baseUrl}/acteurs/notfilm/` + film_id, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public createActeur(acteur: PutActeur): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${environment.baseUrl}/acteurs/create`, acteur, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public updateActeur(id: number, acteur: PutActeur): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${environment.baseUrl}/acteurs/update/` + id, acteur, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public deleteActeur(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${environment.baseUrl}/acteurs/` + id, {observe: "response", headers: this.auth.getTokenHeader()})
  }
}
