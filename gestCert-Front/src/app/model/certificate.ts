import {Application} from './application';
import {Environment} from './environment';
import {Plateform} from './plateform';
import {Root} from './root';
import {Server} from './server';
import {AddressAlternative} from './addressAlternative';

export interface Certificate {

  idCertificate?: number;
  nameCertificate?: string;
  linkAddressPrincipal?: string;
  linkInstallation?: string;
  passwordCertificate?: string;
  dateIssue?: Date;
  dateEndValidity?: Date;
  application?: Application;
  environment?: Environment;
  plateform?: Plateform;
  root?: Root;
  server?: Server[];
  addressAlternative?: AddressAlternative[];
  applicationName?: string;

}

export class Certificate {

  public idCertificate?: number;
  public nameCertificate?: string;
  public linkAddressPrincipal?: string;
  public linkInstallation?: string;
  public passwordCertificate?: string;
  public dateIssue?: Date;
  public dateEndValidity?: Date;
  public application?: Application;
  public environment?: Environment;
  public plateform?: Plateform;
  public root?: Root;
  public server?: Server[];
  public addressAlternative?: AddressAlternative[];
  public applicationName?: string;

  constructor(idCertificate: number,
              nameCertificate: string,
              linkAddressPrincipal: string,
              linkInstallation: string,
              passwordCertificate: string,
              dateIssue: Date,
              dateEndValidity: Date,
              application: Application,
              environment: Environment,
              plateform: Plateform,
              root: Root,
              server: Server[],
              addressAlternative: AddressAlternative[]) {

    this.idCertificate = idCertificate;
    this.nameCertificate = nameCertificate;
    this.linkAddressPrincipal = linkAddressPrincipal;
    this.linkInstallation = linkInstallation;
    this.passwordCertificate = passwordCertificate;
    this.dateIssue = new Date(dateIssue);
    this.dateEndValidity = new Date(dateEndValidity);
    this.application = application;
    this.environment = environment;
    this.plateform = plateform;
    this.root = root;
    this.server = server;
    this.addressAlternative = addressAlternative;
  }

}
