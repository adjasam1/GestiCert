import {Profile} from './profile';
import {Department} from './department';
import {Application} from './application';

export class User {

  constructor(public idUser: number,
              public nameUser: string,
              public firstNameUser: string,
              public roleUser: string,
              public eMailUser: string,
              public phoneUser: string,
              public idRHUser: string,
              public passwordUser: string,
              public profile: Profile,
              public department: Department,
              public applications: Application[]) {}
}
