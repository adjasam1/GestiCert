import {Demand} from './demand';

export interface TypeDemand {

  idTypeDemand?: number;
  typeTypeDemand?: string;
  demands?: Demand[];

}

export class TypeDemand {

  public idTypeDemand?: number;
  public typeTypeDemand?: string;
  public demands?: Demand[];

  constructor(idTypeDemand: number,
              typeTypeDemand: string,
              demands: Demand[]) {

    this.idTypeDemand = idTypeDemand;
    this.typeTypeDemand = typeTypeDemand;
    this.demands = demands;
  }

}
