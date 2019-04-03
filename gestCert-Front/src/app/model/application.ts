import {User} from './user';
import {Certificate} from './certificate';

export class Application {

  constructor(public idApplication: number,
              public codeCCX: string,
              public nameApplication: string,
              public nameClient: string,
              public firstNameClient: string,
              public managementClient: string,
              public phoneClient: string,
              public eMailClient: string,
              public users: User[],
              public certificates: Certificate[]) {}

}
