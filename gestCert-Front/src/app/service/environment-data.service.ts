import { Injectable } from '@angular/core';
import {Environment} from '../model/environment';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

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
    return this.httpClient.get<Environment[]>('http://localhost:8080/environnement');
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
}
