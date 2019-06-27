import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Application} from '../model/application';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApplicationDataService {

  /**
   * liste des applications de l'application
   */

  private availableApplications: Application[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableApplications$: BehaviorSubject<Application[]> = new BehaviorSubject(this.availableApplications);

  constructor(private httpClient: HttpClient) {}

  /**
   * cherche toutes les applications grâce à la methode CRUD
   */

  public getApplication(): Observable<Application[]> {
    return this.httpClient.get<Application[]>('http://localhost:8080/api/application');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des applications depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishApplication() {
    this.getApplication().subscribe(
      applicationList => {
        this.availableApplications = applicationList;
        this.availableApplications$.next(this.availableApplications);
      });
  }

  public findApplication(applicationId: number): Observable<Application> {
    if (applicationId) {
      if (!this.availableApplications) {
        return this.getApplication().pipe(map(applications => applications.find(application =>
          application.idApplication === applicationId)));
      }
      return of(this.availableApplications.find(application => application.idApplication === applicationId));
    } else {
      return of(new Application(0, '', '', '', '', '', '', '', null, null));
    }
  }

  public createApplication(newApplication: Application) {
    return this.httpClient.post<Application>(`http://localhost:8080/api/application/ajout`, newApplication);
  }

  public updateApplication(application: Application) {
    return this.httpClient.put<Application>(`http://localhost:8080/api/application/modifid=${application.idApplication}`, application);
  }

  public deleteApplication(application: Application) {
    return this.httpClient.delete<Application>(`http://localhost:8080/api/application/supprid=${application.idApplication}`);
  }

  /* TEST PRIMENG */
  public getApplicationPrimeNg() {
    return this.httpClient.get<any>('http://localhost:8080/api/application').toPromise().then(data => {
      return data;
    });
  }
}
