import {Component, OnInit} from '@angular/core';
import {FilmService} from "../services/film.service";
import {HttpResponse} from "@angular/common/http";
import {Film} from "../model/film.model";
import {MatTableDataSource} from "@angular/material/table";
import {Router} from "@angular/router";

@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.scss']
})
export class FilmComponent implements OnInit {

  dataSource: MatTableDataSource<Film> = new MatTableDataSource<Film>();
  displayedColumns: string[] = ['titre', 'duree', 'dateSortie', 'budget', 'montantRecette', 'modifier', 'supprimer'];

  constructor(private filmService: FilmService, private router: Router) {
  }

  ngOnInit(): void {
    this.filmService.getFilms().subscribe({
      next: (response: HttpResponse<Film[]>) => {
        if (!response.ok || !response.body) {
          throw new Error('Erreur lors du chargement des films');
        }
        this.dataSource.data = response.body;
      }
    })
  }

  modifier(id: number) {
    this.router.navigate(['/films/edit/' + id]);
  }

}
