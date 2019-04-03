import {Demand} from './demand';

export class StatusDemand {

  constructor(public idStatusDemand: number,
              public nameStatusDemand: string,
              public demands: Demand[]) {}

}
