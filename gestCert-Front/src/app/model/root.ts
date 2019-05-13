import {Certificate} from './certificate';

export interface Root {

  idRoot?: number;
  nameRoot?: string;
  certificates?: Certificate[];

}

export class Root {

  public idRoot?: number;
  public nameRoot?: string;
  public certificates?: Certificate[];

  constructor(idRoot?: number,
              nameRoot?: string,
              certificates?: Certificate[]) {

    this.idRoot = idRoot;
    this.nameRoot = nameRoot;
    this.certificates = certificates;
  }

}
