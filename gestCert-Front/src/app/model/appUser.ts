import {Profile} from './profile';
import {Department} from './department';
import {Application} from './application';

export interface AppUser {

  idUser?: number;
  nameUser?: string;
  firstNameUser?: string;
  eMailUser?: string;
  phoneUser?: string;
  idRHUser?: string;
  passwordUser?: string;
  profile?: Profile;
  department?: Department;
  applications?: Application[];
  roleList?: string[];

}

export class AppUser {

/*  constructor(public id?: number,
              public username?: string,
              public password?: string,
              public roleList?: string[]) {}*/

  public idUser?: number;
  public nameUser?: string;
  public firstNameUser?: string;
  public eMailUser?: string;
  public phoneUser?: string;
  public idRHUser?: string;
  public passwordUser?: string;
  public profile?: Profile;
  public department?: Department;
  public applications?: Application[];
  public roleList?: string[];

  /* ? obligatoire pour primeng afin que l'objet n'est pas assigne avec tous ses attributs */
  constructor(idUser?: number,
              nameUser?: string,
              firstNameUser?: string,
              eMailUser?: string,
              phoneUser?: string,
              idRHUser?: string,
              passwordUser?: string,
              profile?: Profile,
              department?: Department,
              applications?: Application[],
              roleList?: string[]) {

    this.idUser = idUser;
    this.nameUser = nameUser;
    this.firstNameUser = firstNameUser;
    this.eMailUser = eMailUser;
    this.phoneUser = phoneUser;
    this.idRHUser = idRHUser;
    this.passwordUser = passwordUser;
    this.profile = profile;
    this.department = department;
    this.applications = applications;
    this.roleList = roleList;
  }
}
