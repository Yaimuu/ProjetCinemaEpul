import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../shared/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  error$: Observable<string>;

  loginForm = this.fb.nonNullable.group({
    login: [''],
    password: ['']
  });

  constructor(
    public fb: FormBuilder,
    public authService: AuthService,
    public router: Router
  ) {
    this.error$ = this.authService.getErrorMessage;
  }

  loginUser() {
    this.authService.signIn(this.loginForm.getRawValue());
  }

  ngOnInit(): void {
  }

}
