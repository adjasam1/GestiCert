import {StatusDemand} from './statusDemand';
import {TypeDemand} from './typeDemand';
import {User} from './user';
import {Application} from './application';
import {Certificate} from './certificate';

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
              public user: User,
              public application: Application,
              public certificate: Certificate) {}

}
