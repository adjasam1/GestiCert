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
    this.httpClient.post<Environment>('http://localhost:8080/api/environnement/ajout', newEnvironment).subscribe(
      createEnvironment => {
        this.availableEnvironments.push(createEnvironment);
        this.availableEnvironments$.next(this.availableEnvironments);
      }
    );
  }

  public updateEnvironment(environment: Environment) {
    this.httpClient.put<Environment>(`http://localhost:8080/api/environnement/modifid=${environment.idEnvironment}`, environment).subscribe(
      updateEnvironment => {
        this.availableEnvironments$.next(this.availableEnvironments);
      }
    );
  }

  public deleteEnvironment(environment: Environment) {
    this.httpClient.delete<Environment>(`http://localhost:8080/api/environnement/supprid=${environment.idEnvironment}`).subscribe(
      deleteEnvironment => {
        const index1 = this.availableEnvironments.indexOf(environment);
        this.availableEnvironments.splice(index1, 1);
        this.availableEnvironments$.next(this.availableEnvironments);
      }
    );
  }

  /* TEST PRIMENG */
  public getEnvironmentPrimeNg() {
    return this.httpClient.get('http://localhost:8080/api/environnement').toPromise().then(data => {
      return data;
    });
  }

}
