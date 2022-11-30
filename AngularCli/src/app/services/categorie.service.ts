import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {AuthService} from "../shared/auth.service";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Categorie} from "../model/categorie.model";

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  public getCategories(): Observable<HttpResponse<Categorie[]>> {
    return this.http.get<Categorie[]>(`${environment.baseUrl}/categories`, {observe: "response", headers: this.auth.getTokenHeader()})
  }
}
