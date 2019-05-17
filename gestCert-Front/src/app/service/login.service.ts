import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {AppUser} from '../model/appUser';
import {JsonWebToken} from '../model/jwt';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  userRoles: BehaviorSubject<string[]> = new BehaviorSubject([]);

  constructor(private httpClient: HttpClient,
              private router: Router) {
  //  this.getUserRoles();
  }

  public get loggedIn(): boolean {
    return sessionStorage.getItem(environment.accessToken) !== null;
  }

  signIn(user: AppUser) {
    this.httpClient.post<JsonWebToken>(environment.apiUrl + 'utilisateur/sign-in', user).subscribe(
      token => {
        sessionStorage.setItem(environment.accessToken, token.token);

        this.getUserRoles();

  //      const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
   //     const idRH = decodedToken.sub;
   //     console.log('je suis loggé !!!');
   //     this.router.navigate(['/accueil/' + idRH]);

      //  this.router.navigate(['/accueil/']);
      },
   /* error => alert('Identifiant RH et/ou Mot de passe manquant(s) ou incorrecte(s)')*/);
  }

/*  signOut() {
    sessionStorage.removeItem(environment.accessToken);
  }*/

  private getUserRoles() {
    if (sessionStorage.getItem(environment.accessToken)) {
      const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
      const authorities: Array<any> = decodedToken.auth;
      this.userRoles.next(authorities.map(authority => authority.authority));
 //     console.log('token : ' , decodedToken);
      const idRH = decodedToken.sub;
      this.router.navigate(['/accueil/' + idRH]);
    }
  }
}
