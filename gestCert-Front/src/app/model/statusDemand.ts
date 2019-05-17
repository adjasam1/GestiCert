import {Demand} from './demand';

export interface StatusDemand {

  idStatusDemand?: number;
  nameStatusDemand?: string;
  demands?: Demand[];

}

export class StatusDemand {

  public idStatusDemand?: number;
  public nameStatusDemand?: string;
  public demands?: Demand[];

  constructor(idStatusDemand: number,
              nameStatusDemand: string,
              demands: Demand[]) {

    this.idStatusDemand = idStatusDemand;
    this.nameStatusDemand = nameStatusDemand;
    this.demands = demands;
  }

}
