import {StatusDemand} from './statusDemand';
import {TypeDemand} from './typeDemand';
import {Application} from './application';
import {Certificate} from './certificate';
import {AppUser} from './appUser';

export class Demand {

  constructor(public idDemand: number,
              public dateDemand: Date,
              public dateCreationDesired: Date,
              public dateTransmission: Date,
              public eMailReferent: string,
              public descriptionContext: string,
              public remarkRoot: string,
              public statusDemand: StatusDemand,
              public typeDemand: TypeDemand,
              public user: AppUser,
              public application: Application,
              public certificate: Certificate) {}

}
