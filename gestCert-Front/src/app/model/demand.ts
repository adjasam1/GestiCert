import {StatusDemand} from './statusDemand';
import {TypeDemand} from './typeDemand';
import {Application} from './application';
import {Certificate} from './certificate';
import {AppUser} from './appUser';

export interface Demand {

  idDemand?: number;
  dateDemand?: Date;
  dateCreationDesired?: Date;
  dateTransmission?: Date;
  eMailReferent?: string;
  descriptionContext?: string;
  remarkRoot?: string;
  statusDemand?: StatusDemand;
  typeDemand?: TypeDemand;
  user?: AppUser;
  application?: Application;
  certificate?: Certificate;

}

export class Demand {

  public idDemand?: number;
  public dateDemand?: Date;
  public dateCreationDesired?: Date;
  public dateTransmission?: Date;
  public eMailReferent?: string;
  public descriptionContext?: string;
  public remarkRoot?: string;
  public statusDemand?: StatusDemand;
  public typeDemand?: TypeDemand;
  public user?: AppUser;
  public application?: Application;
  public certificate?: Certificate;

  constructor(idDemand: number,
              dateDemand: Date,
              dateCreationDesired: Date,
              dateTransmission: Date,
              eMailReferent: string,
              descriptionContext: string,
              remarkRoot: string,
              statusDemand: StatusDemand,
              typeDemand: TypeDemand,
              user: AppUser,
              application: Application,
              certificate: Certificate) {

    this.idDemand = idDemand;
    this.dateDemand = new Date(dateDemand);
    this.dateCreationDesired = new Date(dateCreationDesired);
    this.dateTransmission = new Date(dateTransmission);
    this.eMailReferent = eMailReferent;
    this.descriptionContext = descriptionContext;
    this.remarkRoot = remarkRoot;
    this.statusDemand = statusDemand;
    this.typeDemand = typeDemand;
    this.user = user;
    this.application = application;
    this.certificate = certificate;
  }

}
