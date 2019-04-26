import {AppUser} from './appUser';


export class Department {

  constructor(public idDepartment: number,
              public nameDepartment: string,
              public users?: AppUser[]) {}

}
