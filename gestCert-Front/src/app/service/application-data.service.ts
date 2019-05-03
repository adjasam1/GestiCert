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
    return this.httpClient.get<Application[]>('http://localhost:8080/api/application');
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

  public findApplication(applicationId: number): Observable<Application> {
    if (applicationId) {
      if (!this.availableapplications) {
        return this.getApplication().pipe(map(applications => applications.find(application => application.idApplication === applicationId)));
      }
      return of(this.availableapplications.find(application => application.idApplication === applicationId));
    } else {
      return of(new Application(0, '', '', '', '', '', '', '', null, null));
    }
  }

  public createApplication(newApplication: Application) {
    this.httpClient.post<Application>('http://localhost:8080/api/application/ajout', newApplication).subscribe(
      createApplication => {
        this.availableapplications.push(createApplication);
        this.availableApplications$.next(this.availableapplications);
      }
    );
  }

  public updateApplication(application: Application) {
    this.httpClient.put<Application>(`http://localhost:8080/api/application/modifid=${application.idApplication}`, application).subscribe(
      updateApplication => {
        this.availableApplications$.next(this.availableapplications);
      }
    );
  }

  public deleteApplication(application: Application) {
    this.httpClient.delete<Application>(`http://localhost:8080/api/application/supprid=${application.idApplication}`).subscribe(
      deleteApplication => {
        const index1 = this.availableapplications.indexOf(application);
        this.availableapplications.splice(index1, 1);
        this.availableApplications$.next(this.availableapplications);
      }
    );
  }

  /* TEST PRIMENG */
  public getApplicationPrimeNg() {
    return this.httpClient.get('http://localhost:8080/api/application').toPromise().then(data =>  {return data;} );
  }
}
