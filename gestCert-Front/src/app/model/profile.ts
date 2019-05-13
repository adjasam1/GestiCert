import {AppUser} from './appUser';

export interface Profile {

  idProfile?: number;
  typeProfile?: string;
  users?: AppUser[];

}

export class Profile {

  public idProfile?: number;
  public typeProfile?: string;
  public users?: AppUser[];

  constructor(idProfile?: number,
              typeProfile?: string,
              users?: AppUser[]) {

    this.idProfile = idProfile;
    this.typeProfile = typeProfile;
    this.users = users;
  }

}
