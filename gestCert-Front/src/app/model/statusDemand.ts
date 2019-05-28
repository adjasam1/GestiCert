import {Certificate} from './certificate';

export interface StatusDemand {

  idStatusDemand?: number;
  nameStatusDemand?: string;
  certificates?: Certificate[];

}

export class StatusDemand {

  public idStatusDemand?: number;
  public nameStatusDemand?: string;
  public certificates?: Certificate[];

  constructor(idStatusDemand: number,
              nameStatusDemand: string,
              certificates?: Certificate[]) {

    this.idStatusDemand = idStatusDemand;
    this.nameStatusDemand = nameStatusDemand;
    this.certificates = certificates;
  }

}
