import {Demand} from './demand';

export class TypeDemand {

  constructor(public idTypeDemand: number,
              public typeTypeDemand: string,
              public demands: Demand[]) {}

}
