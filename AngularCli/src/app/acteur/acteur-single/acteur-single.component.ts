import {Component, OnInit} from '@angular/core';
import {ActeurService} from "../../services/acteur.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-acteur-single',
  templateUrl: './acteur-single.component.html',
  styleUrls: ['./acteur-single.component.scss']
})
export class ActeurSingleComponent implements OnInit {

  acteur: any;

  constructor(private acteurService: ActeurService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    if (this.route.snapshot.params['id']) {
      this.acteurService.getActeur(this.route.snapshot.params['id']).subscribe({
        next: (response) => {
          if (!response.ok || !response.body) {
            throw new Error('Erreur lors du chargement de l\'acteur');
          }
          this.acteur = response.body;
        },
      });
    }
  }

  personnages(id: number) {
    this.router.navigate(['/personnages/acteur/' + id]);
  }

  modifier(id: number) {
    this.router.navigate(['/acteurs/edit/' + id]);
  }

  supprimer(id: number) {
    this.acteurService.deleteActeur(id).subscribe({
      next: (response) => {
        if (response.ok) {
          window.location.reload();
        } else {
          alert('KO');
        }
      }
    });
  }
}
