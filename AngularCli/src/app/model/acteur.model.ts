export interface Acteur {
  id: number,
  nomAct: string,
  prenAct: string,
  dateNaiss: Date,
  dateDeces: Date
}

export class PutActeur {
  constructor(public nomAct: string, public prenAct: string, public dateNaiss: Date, public dateDeces: Date) {
  }
}
