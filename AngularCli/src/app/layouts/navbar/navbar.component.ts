import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthService} from "../../shared/auth.service";
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  encapsulation: ViewEncapsulation.None,
})

export class NavbarComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit(): void {
  }

  logout() {
    this.authService.doLogout()
  }

}
