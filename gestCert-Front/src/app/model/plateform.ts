import {Certificate} from './certificate';

export class Plateform {

  constructor(public idPlateform: number,
              public namePlateform: string,
              public certificates: Certificate[]) {}

}
