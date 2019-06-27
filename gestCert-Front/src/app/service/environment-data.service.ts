import { Injectable } from '@angular/core';
import {Environment} from '../model/environment';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentDataService {

  /**
   * liste des environnements de l'application
   */

  private availableEnvironments: Environment[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableEnvironments$: BehaviorSubject<Environment[]> = new BehaviorSubject(this.availableEnvironments);

  constructor(private httpClient: HttpClient) {}

  /**
   * cherche tous les environnements grâce à la methode CRUD
   */

  public getEnvironment(): Observable<Environment[]> {
    return this.httpClient.get<Environment[]>('http://localhost:8080/api/environnement');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des environnements depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishEnvironment() {
    this.getEnvironment().subscribe(
      environmentsList => {
        this.availableEnvironments = environmentsList;
        this.availableEnvironments$.next(this.availableEnvironments);
      });
  }

  public findEnvironment(environmentId: number): Observable<Environment> {
    if (environmentId) {
      if (!this.availableEnvironments) {
        return this.getEnvironment().pipe(map(environments => environments.find(environment =>
          environment.idEnvironment === environmentId)));
      }
      return of(this.availableEnvironments.find(environment => environment.idEnvironment === environmentId));
    } else {
      return of(new Environment(0, '', null));
    }
  }

  public createEnvironment(newEnvironment: Environment) {
    return this.httpClient.post<Environment>('http://localhost:8080/api/environnement/ajout', newEnvironment);
  }

  public updateEnvironment(environment: Environment) {
    return this.httpClient.put<Environment>(`http://localhost:8080/api/environnement/modifid=${environment.idEnvironment}`, environment);
  }

  public deleteEnvironment(environment: Environment) {
    return this.httpClient.delete<Environment>(`http://localhost:8080/api/environnement/supprid=${environment.idEnvironment}`);
  }

  /* TEST PRIMENG */
  public getEnvironmentPrimeNg() {
    return this.httpClient.get('http://localhost:8080/api/environnement').toPromise().then(data => {
      return data;
    });
  }
}
