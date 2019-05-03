import {Profile} from './profile';
import {Department} from './department';
import {Application} from './application';

export class AppUser {

/*  constructor(public id?: number,
              public username?: string,
              public password?: string,
              public roleList?: string[]) {}*/

  constructor(public idUser?: number,
              public nameUser?: string,
              public firstNameUser?: string,
              public eMailUser?: string,
              public phoneUser?: string,
              public idRHUser?: string,
              public passwordUser?: string,
              public profile?: Profile,
              public department?: Department,
              public applications?: Application[],
              public roleList?: string[]) {}
}
