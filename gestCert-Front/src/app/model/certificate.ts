import {Application} from './application';
import {Environment} from './environment';
import {Plateform} from './plateform';
import {Root} from './root';
import {Server} from './server';
import {AddressAlternative} from './addressAlternative';
import {StatusDemand} from './statusDemand';
import {TypeDemand} from './typeDemand';
import {AppUser} from './appUser';

export interface Certificate {

  idCertificate?: number;
  nameCertificate?: string;
  linkAddressPrincipal?: string;
  linkInstallation?: string;
  passwordCertificate?: string;
  dateIssue?: Date;
  dateEndValidity?: Date;
  dateDemand?: Date;
  dateCreationDesired?: Date;
  dateTransmission?: Date;
  eMailReferent?: string;
  descriptionContext?: string;
  remarkRoot?: string;
  application?: Application;
  environment?: Environment;
  plateform?: Plateform;
  root?: Root;
  servers?: Server[];
  addressAlternative?: AddressAlternative[];
  user?: AppUser;
  statusDemand?: StatusDemand;
  typeDemand?: TypeDemand;
  applicationName?: string;
  applicationCCX?: string;
  environmentName?: string;
  rootName?: string;

}

export class Certificate {

  public idCertificate?: number;
  public nameCertificate?: string;
  public linkAddressPrincipal?: string;
  public linkInstallation?: string;
  public passwordCertificate?: string;
  public dateIssue?: Date;
  public dateEndValidity?: Date;
  public dateDemand?: Date;
  public dateCreationDesired?: Date;
  public dateTransmission?: Date;
  public eMailReferent?: string;
  public descriptionContext?: string;
  public remarkRoot?: string;
  public application?: Application;
  public environment?: Environment;
  public plateform?: Plateform;
  public root?: Root;
  public servers?: Server[];
  public addressAlternative?: AddressAlternative[];
  public user?: AppUser;
  public statusDemand?: StatusDemand;
  public typeDemand?: TypeDemand;
  public applicationName?: string;
  public applicationCCX?: string;
  public environmentName?: string;
  public rootName?: string;

  constructor(idCertificate?: number,
              nameCertificate?: string,
              linkAddressPrincipal?: string,
              linkInstallation?: string,
              passwordCertificate?: string,
              dateIssue?: Date,
              dateEndValidity?: Date,
              dateDemand?: Date,
              dateCreationDesired?: Date,
              dateTransmission?: Date,
              eMailReferent?: string,
              descriptionContext?: string,
              remarkRoot?: string,
              application?: Application,
              environment?: Environment,
              plateform?: Plateform,
              root?: Root,
              servers?: Server[],
              addressAlternative?: AddressAlternative[],
              user?: AppUser,
              statusDemand?: StatusDemand,
              typeDemand?: TypeDemand,
              applicationName?: string,
              applicationCCX?: string,
              environmentName?: string,
              rootName?: string) {

    this.idCertificate = idCertificate;
    this.nameCertificate = nameCertificate;
    this.linkAddressPrincipal = linkAddressPrincipal;
    this.linkInstallation = linkInstallation;
    this.passwordCertificate = passwordCertificate;
    this.dateIssue = new Date(dateIssue);
    this.dateEndValidity = new Date(dateEndValidity);
    this.dateDemand = new Date(dateDemand);
    this.dateCreationDesired = new Date(dateCreationDesired);
    this.dateTransmission = new Date(dateTransmission);
    this.eMailReferent = eMailReferent;
    this.descriptionContext = descriptionContext;
    this.remarkRoot = remarkRoot;
    this.application = application;
    this.environment = environment;
    this.plateform = plateform;
    this.root = root;
    this.servers = servers;
    this.addressAlternative = addressAlternative;
    this.user = user;
    this.statusDemand = statusDemand;
    this.typeDemand = typeDemand;
  }

}
