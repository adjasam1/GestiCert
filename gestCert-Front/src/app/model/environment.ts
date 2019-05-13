import {Certificate} from './certificate';

export interface Environment {

  idEnvironment?: number;
  nameEnvironment?: string;
  certificates?: Certificate[];

}

export class Environment {

  public idEnvironment?: number;
  public nameEnvironment?: string;
  public certificates?: Certificate[];

  constructor(idEnvironment?: number,
              nameEnvironment?: string,
              certificates?: Certificate[]) {

    this.idEnvironment = idEnvironment;
    this.nameEnvironment = nameEnvironment;
    this.certificates = certificates;
  }

}
