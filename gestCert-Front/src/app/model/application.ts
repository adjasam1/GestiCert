import {Certificate} from './certificate';
import {AppUser} from './appUser';

export class Application {

  constructor(public idApplication: number,
              public codeCCX: string,
              public nameApplication: string,
              public nameClient: string,
              public firstNameClient: string,
              public managementClient: string,
              public phoneClient: string,
              public eMailClient: string,
              public users: AppUser[],
              public certificates: Certificate[]) {}

}
