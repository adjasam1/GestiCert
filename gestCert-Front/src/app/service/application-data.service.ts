import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Application} from '../model/application';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApplicationDataService {

  /**
   * liste des applications de l'application
   */

  private availableapplications: Application[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableApplications$: BehaviorSubject<Application[]> = new BehaviorSubject(this.availableapplications);

  constructor(private httpClient: HttpClient) {}

  /**
   * cherche toutes les applications grâce à la methode CRUD
   */

  public getApplication(): Observable<Application[]> {
    return this.httpClient.get<Application[]>('http://localhost:8080/application');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des applications depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishApplication() {
    this.getApplication().subscribe(
      applicationList => {
        this.availableapplications = applicationList;
        this.availableApplications$.next(this.availableapplications);
      });
  }
}
