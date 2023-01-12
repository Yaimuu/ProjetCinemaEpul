import {Component, OnInit} from '@angular/core';
import {FilmService} from "../services/film.service";
import {HttpResponse} from "@angular/common/http";
import {Film} from "../model/film.model";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Categorie} from "../model/categorie.model";
import {CategorieService} from "../services/categorie.service";
import {Realisateur} from "../model/realisateur.model";
import {RealisateurService} from "../services/realisateur.service";

@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.scss']
})
export class FilmComponent implements OnInit {

  step = -1;
  films: Film[] = [];
  displayedColumns: string[] = ['titre', 'duree', 'dateSortie', 'budget', 'montantRecette', 'modifier', 'supprimer'];
  isSearch = false;
  displayedCategories: Categorie[] = [];
  formSearch: FormGroup = new FormGroup<any>({
    nomFilm: new FormControl<string>("", [Validators.required])
  });
  categories: Categorie[] = [];
  realisateurs: Realisateur[] = [];
  displayedRealisateurs: Realisateur[] = [];
  filterFilmsArray: Film[] = [];
  textButton = "Faire une recherche";

  constructor(private filmService: FilmService,
              private categorieService: CategorieService,
              private realisateurService: RealisateurService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.filmService.getFilms().subscribe({
      next: (response: HttpResponse<Film[]>) => {
        if (!response.ok || !response.body) {
          throw new Error('Erreur lors du chargement des films');
        }
        this.films = response.body;
        this.filterFilmsArray = Object.assign([],response.body);
      }
    });

    this.realisateurService.getRealisateurs().subscribe({
      next: (response: HttpResponse<Realisateur[]>) => {
        if (!response.ok || !response.body) {
          throw new Error('Erreur lors du chargement des films');
        }
        this.displayedRealisateurs = Object.assign([],response.body);
      }
    });

    this.categorieService.getCategories().subscribe({
      next: (response) => {
        if (!response.ok || !response.body) {
          throw new Error('Erreur lors du chargement des catégories');
        }
        this.displayedCategories = response.body;
      }
    });
  }

  catAll(checked: boolean) {
    this.categories = [];
    if (checked) {
      for (let cat of this.displayedCategories) {
        this.catAdd(true, cat);
      }
    }
  }

  catAdd(checked: boolean, cat: Categorie) {
    if (checked) {
      this.categories.push(cat);
    } else {
      let index = this.categories.indexOf(cat, 0);
      if (index > -1) {
        this.categories.splice(index, 1);
      }
    }
  }

  realAll(checked: boolean) {
    this.realisateurs = [];
    if (checked) {
      for (let real of this.displayedRealisateurs) {
        this.realAdd(true, real);
      }
    }
  }

  realAdd(checked: boolean, real: Realisateur) {
    if (checked) {
      this.realisateurs.push(real);
    } else {
      let index = this.realisateurs.indexOf(real, 0);
      if (index > -1) {
        this.realisateurs.splice(index, 1);
      }
    }
  }

  rechercher() {
    this.filterFilmsArray = Object.assign([], this.films.filter((value) => this.filterFilms(value)));
  }

  filterFilms(f: Film) {
    let catIn = false;
    for (const cat of this.categories) {
      if (f.codeCat.id == cat.id) catIn = true;
    }
    let realIn = false;
    for (let real of this.realisateurs) {
      if (f.noRea.nomRea == real.nomRea) realIn = true;
    }
    return catIn && realIn && f.titre.search(this.formSearch.controls['nomFilm'].value) > -1;
  }

  ajouter() {
    this.router.navigate(['/films/create']);
  }

  modifier(id: number) {
    this.router.navigate(['/films/edit/' + id]);
  }

  supprimer(id: number) {
    this.filmService.deleteFilm(id).subscribe({
      next: (response) => {
        if (response.ok) {
          window.location.reload();
        } else {
          alert('KO');
        }
      }
    });
  }

  personnages(id: number) {
    this.router.navigate(['/personnages/film/' + id]);
  }

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  switchSearch() {
    this.isSearch = !this.isSearch;
    if (this.isSearch) {
      this.textButton = "Voir les films"
    } else {
      this.textButton = "Faire une recherche";
      this.rechercher();
    }
  }
}
