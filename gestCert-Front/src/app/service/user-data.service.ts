import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  /**
   * liste des utilisateurs de l'application
   */

  private availableUsers: User[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableUsers$: BehaviorSubject<User[]> = new BehaviorSubject(this.availableUsers);

  constructor(private httpClient: HttpClient) {}

  /**
   * cherche tous les utilisateurs grâce à la methode CRUD
   */

  public getUser(): Observable<User[]> {
    return this.httpClient.get<User[]>('http://localhost:8080/utilisateur');
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
   * @param userId
   */

  public findUser(userId: number): Observable<User> {
    if (userId) {
      if (!this.availableUsers) {
        return this.getUser().pipe(map(users => users.find(user => user.idUser === userId)));
      }
      return of(this.availableUsers.find(user => user.idUser === userId));
    } else {
      return of(new User(0, '', '', '', '', '', '', '', null, null, null));
    }
  }

  public createUser(newUser: User) {
    this.httpClient.post<User>('http://localhost:8080/utilisateur/ajout', newUser).subscribe(
      createUser => {
        this.availableUsers.push(createUser);
        this.availableUsers$.next(this.availableUsers);
      }
    );
  }

  public updateUser(user: User) {
    this.httpClient.put<User>(`http://localhost:8080/utilisateur/modifid=${user.idUser}`, user).subscribe(
      updateUser => {
        this.availableUsers$.next(this.availableUsers);
      }
    );
  }

}

