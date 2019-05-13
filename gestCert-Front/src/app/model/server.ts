import {Certificate} from './certificate';

export interface Server {

  idServer?: number;
  nameServer?: string;
  certificates?: Certificate[];

}

export class Server {

  public idServer?: number;
  public nameServer?: string;
  public certificates?: Certificate[];

  constructor(idServer?: number,
              nameServer?: string,
              certificates?: Certificate[]) {

    this.idServer = idServer;
    this.nameServer = nameServer;
    this.certificates = certificates;
  }

}
