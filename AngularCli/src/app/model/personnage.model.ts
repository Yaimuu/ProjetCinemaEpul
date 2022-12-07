import {Film} from "./film.model";
import {Acteur} from "./acteur.model";

export interface Personnage {
  noFilm: Film,
  noAct: Acteur,
  nomPers: string
}

export class PutPersonnage {
  constructor(public noFilm: number, public noAct: number, public nomPers: string) {
  }
}
