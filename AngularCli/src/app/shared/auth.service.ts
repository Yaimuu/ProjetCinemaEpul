import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../model/user.model";
import {BehaviorSubject, Observable} from "rxjs";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {JwtResponse} from "../model/jwt-response.model";

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private errorMessage$ = new BehaviorSubject<string>('');
  public getErrorMessage = this.errorMessage$.asObservable();

  constructor(private http: HttpClient, public router: Router) {
  }

  signIn(user: User) {
    return this.http
      .post<JwtResponse>(`${environment.baseUrl}/auth/login`, user)
      .subscribe(
        {
          next: jwt => {
            localStorage.setItem('access_token', jwt.accessToken);
            this.getUser(user.login!).subscribe((userRes) => {
              localStorage.setItem('currentUser', JSON.stringify(userRes));
              //TODO route à redéfinir, mais on redirigera pas dans la même page selon le role
              this.router.navigate(['']);
            });
          }, error: err => this.errorMessage$.next(err.error.message)
        });
  };

  public getToken() {
    return localStorage.getItem('access_token');
  }

  get isLoggedIn(): boolean {
    let authToken = localStorage.getItem('access_token');
    return authToken !== null;
  }

  public doLogout() {
    localStorage.removeItem('access_token');
  }

  private getUser(id: string): Observable<User> {
    let api = `${environment.baseUrl}/users/${id}`;
    return this.http.get<User>(api);
  }

  public getTokenHeader(): HttpHeaders {
    return new HttpHeaders().set('Authorization', 'Bearer ' + this.getToken())
                            .set('Content-Type', 'application/json; charset=UTF-8');
  }

}
