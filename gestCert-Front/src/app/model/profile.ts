import {AppUser} from './appUser';


export class Profile {

  constructor(public idProfile?: number,
              public typeProfile?: string,
              public users?: AppUser[]) {}

}
