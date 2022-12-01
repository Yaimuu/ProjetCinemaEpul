import {Realisateur} from "./realisateur.model";
import {Categorie} from "./categorie.model";

export interface Film {
  id: number,
  titre: string,
  duree: number,
  dateSortie: Date,
  budget: number,
  montantRecette: number,
  noRea: Realisateur,
  codeCat: Categorie
}

export class PutFilm {
  constructor(public titre: string, public duree: number, public dateSortie: Date, public budget: number, public montantRecette: number, public noRea: number, public codeCat: string) {
  }
}
