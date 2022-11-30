import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {AuthService} from "../shared/auth.service";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Realisateur} from "../model/realisateur.model";

@Injectable({
  providedIn: 'root'
})
export class RealisateurService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  public getRealisateurs(): Observable<HttpResponse<Realisateur[]>> {
    return this.http.get<Realisateur[]>(`${environment.baseUrl}/realisateurs`, {observe: "response", headers: this.auth.getTokenHeader()})
  }

  public getRealisateur(id: number): Observable<HttpResponse<Realisateur>> {
    return this.http.get<Realisateur>(`${environment.baseUrl}/realisateurs/` + id, {observe: "response", headers: this.auth.getTokenHeader()})
  }
}
