import {Certificate} from './certificate';

export interface Plateform {

  idPlateform?: number;
  namePlateform?: string;
  certificates?: Certificate[];

}

export class Plateform {

  public idPlateform?: number;
  public namePlateform?: string;
  public certificates?: Certificate[];

  constructor(idPlateform?: number,
              namePlateform?: string,
              certificates?: Certificate[]) {

    this.idPlateform = idPlateform;
    this.namePlateform = namePlateform;
    this.certificates = certificates;
  }

}
