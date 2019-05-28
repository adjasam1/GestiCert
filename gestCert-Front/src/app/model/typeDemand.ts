import {Certificate} from './certificate';

export interface TypeDemand {

  idTypeDemand?: number;
  typeTypeDemand?: string;
  certificates?: Certificate[];

}

export class TypeDemand {

  public idTypeDemand?: number;
  public typeTypeDemand?: string;
  public certificates?: Certificate[];

  constructor(idTypeDemand: number,
              typeTypeDemand: string,
              certificates?: Certificate[]) {

    this.idTypeDemand = idTypeDemand;
    this.typeTypeDemand = typeTypeDemand;
    this.certificates = certificates;
  }

}
