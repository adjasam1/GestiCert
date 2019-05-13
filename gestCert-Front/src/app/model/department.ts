import {AppUser} from './appUser';

export interface Department {

  idDepartment?: number;
  nameDepartment?: string;
  users?: AppUser[];

}

export class Department {

  public idDepartment?: number;
  public nameDepartment?: string;
  public users?: AppUser[];

  constructor(idDepartment?: number,
              nameDepartment?: string,
              users?: AppUser[]) {

    this.idDepartment = idDepartment;
    this.nameDepartment = nameDepartment;
    this.users = users;
  }

}
