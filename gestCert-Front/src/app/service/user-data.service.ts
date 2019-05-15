import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {AppUser} from '../model/appUser';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  /**
   * liste des utilisateurs de l'application
   */

  private availableUsers: AppUser[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableUsers$: BehaviorSubject<AppUser[]> = new BehaviorSubject(this.availableUsers);

  constructor(private httpClient: HttpClient) {}

  /**
   * cherche tous les utilisateurs grâce à la methode CRUD
   */

  public getUser(): Observable<AppUser[]> {
    return this.httpClient.get<AppUser[]>('http://localhost:8080/api/utilisateur');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des utilisateurs depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishUser() {
    this.getUser().subscribe(
      usersList => {
        this.availableUsers = usersList;
        this.availableUsers$.next(this.availableUsers);
      });
  }

  /**
   * fonction qui permet de trouver un utilisateur grace a son id dans la liste des utilisateurs charges par l'application
   *
   *  userId
   */

  public findUser(userId: number): Observable<AppUser> {
    if (userId) {
      if (!this.availableUsers) {
        return this.getUser().pipe(map(users => users.find(user => user.idUser === userId)));
      }
      return of(this.availableUsers.find(user => user.idUser === userId));
    } else {
      return of(new AppUser(0, '', '', '', '', '', '', null, null, null, null));
    }
  }

  public createUser(newUser: AppUser) {
    this.httpClient.post<AppUser>(`http://localhost:8080/api/utilisateur/ajout`, newUser).subscribe(
      createUser => {
        this.availableUsers.push(createUser);
        this.availableUsers$.next(this.availableUsers);
      }
    );
  }

  public updateUser(user: AppUser) {
    this.httpClient.put<AppUser>(`http://localhost:8080/api/utilisateur/modifid=${user.idUser}`, user).subscribe(
      updateUser => {
        this.availableUsers$.next(this.availableUsers);
      }
    );
  }

  public deleteUser(user: AppUser) {
    this.httpClient.delete<AppUser>(`http://localhost:8080/api/utilisateur/supprid=${user.idUser}`).subscribe(
      deleteUser => {
        const index1 = this.availableUsers.indexOf(user);
        this.availableUsers.splice(index1, 1);
        this.availableUsers$.next(this.availableUsers);
        this.publishUser();
      }
    );
  }

  /* TEST PRIMENG */
  public getUserPrimeNg() {
    return this.httpClient.get('http://localhost:8080/api/utilisateur').toPromise().then(data =>  {
      return data;
    });
  }

  /* auth proc stockee */
  public  getUserByIdUser(idRHUser: string) {
   // alert(idRHUser);
    return this.httpClient.get('http://localhost:8080/api/utilisateur/getIdUser/paaa001');
  }

  public getRoleUser(idUser: number) {
  //  alert('getRole');
    return this.httpClient.get<string[]>('http://localhost:8080/api/utilisateur/role/' + idUser);
  }

}

