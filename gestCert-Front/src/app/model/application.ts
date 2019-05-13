import {Certificate} from './certificate';
import {AppUser} from './appUser';

export interface Application {

  idApplication?: number;
  codeCCX?: string;
  nameApplication?: string;
  nameClient?: string;
  firstNameClient?: string;
  managementClient?: string;
  phoneClient?: string;
  eMailClient?: string;
  users?: AppUser[];
  certificates?: Certificate[];
}

export class Application {

  public idApplication?: number;
  public codeCCX?: string;
  public nameApplication?: string;
  public nameClient?: string;
  public firstNameClient?: string;
  public managementClient?: string;
  public phoneClient?: string;
  public eMailClient?: string;
  public users?: AppUser[];
  public certificates?: Certificate[];

  constructor(idApplication?: number,
              codeCCX?: string,
              nameApplication?: string,
              nameClient?: string,
              firstNameClient?: string,
              managementClient?: string,
              phoneClient?: string,
              eMailClient?: string,
              users?: AppUser[],
              certificates?: Certificate[]) {

    this.idApplication = idApplication;
    this.codeCCX = codeCCX;
    this.nameApplication = nameApplication;
    this.nameClient = nameClient;
    this.firstNameClient = firstNameClient;
    this.managementClient = managementClient;
    this.phoneClient = phoneClient;
    this.eMailClient = eMailClient;
    this.users = users;
    this.certificates = certificates;
  }

}
