import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {AddressAlternative} from '../model/addressAlternative';
import {HttpClient} from '@angular/common/http';
// import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AddressAlternativeDataService {

  /**
   * liste des adresses Alternatives de l'application
   */

  private availableAddressAlternatives: AddressAlternative[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableAddressAlternatives$: BehaviorSubject<AddressAlternative[]> = new BehaviorSubject(this.availableAddressAlternatives);

  constructor(private httpClient: HttpClient) {}

  /**
   * cherche toutes les adresses alternatives grâce à la methode CRUD
   */

  public getAddressAlternative(): Observable<AddressAlternative[]> {
    return this.httpClient.get<AddressAlternative[]>('http://localhost:8080/api/adressealternative');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des adresses alternatives depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishAddressAlternative() {
    this.getAddressAlternative().subscribe(
      addressAlternativeList => {
        this.availableAddressAlternatives = addressAlternativeList;
        this.availableAddressAlternatives$.next(this.availableAddressAlternatives);
      });
  }

/*  public findAddressAlternative(addressAlternativeId: number): Observable<AddressAlternative> {
    if (addressAlternativeId) {
      if (!this.availableAddressAlternatives) {
        return this.getAddressAlternative().pipe(map(addressAlternatives => addressAlternatives.find(addressAlternative =>
          addressAlternative.idAddressAlternative === addressAlternativeId)));
      }
      return of(this.availableAddressAlternatives.find(addressAlternative =>
        addressAlternative.idAddressAlternative === addressAlternativeId));
    } else {
      return of(new AddressAlternative(0, '', null));
    }
  }*/

  public findAddressAlternativeByCertificate(certificateId: number) {
    return this.httpClient.get<AddressAlternative>(`http://localhost:8080/api/adressealternative/certificat=${certificateId}`);
  }
/*
  public createApplication(newApplication: Application) {
    this.httpClient.post<Application>('http://localhost:8080/api/application/ajout', newApplication).subscribe(
      createApplication => {
        this.availableApplications.push(createApplication);
        this.availableApplications$.next(this.availableApplications);
      }
    );
  }

  public updateApplication(application: Application) {
    this.httpClient.put<Application>(`http://localhost:8080/api/application/modifid=${application.idApplication}`, application).subscribe(
      updateApplication => {
        this.availableApplications$.next(this.availableApplications);
      }
    );
  }

  public deleteApplication(application: Application) {
    this.httpClient.delete<Application>(`http://localhost:8080/api/application/supprid=${application.idApplication}`).subscribe(
      deleteApplication => {
        const index1 = this.availableApplications.indexOf(application);
        this.availableApplications.splice(index1, 1);
        this.availableApplications$.next(this.availableApplications);
      }
    );
  }

  /* TEST PRIMENG /
  public getApplicationPrimeNg() {
    return this.httpClient.get<any>('http://localhost:8080/api/application').toPromise().then(data => {
      return data;
    });
  }*/
}
