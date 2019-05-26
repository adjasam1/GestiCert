import {Certificate} from './certificate';

export interface Server {

  idServer?: number;
  nameServer?: string;
  certificate?: Certificate[];

}

export class Server {

  public idServer?: number;
  public nameServer?: string;
  public certificate?: Certificate[];

  constructor(idServer?: number,
              nameServer?: string,
              certificate?: Certificate[]) {

    this.idServer = idServer;
    this.nameServer = nameServer;
    this.certificate = certificate;
  }

}
