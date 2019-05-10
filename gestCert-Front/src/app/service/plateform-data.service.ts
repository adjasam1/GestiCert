import { Injectable } from '@angular/core';
import {Plateform} from '../model/plateform';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Environment} from '../model/environment';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlateformDataService {

  /**
   * liste des plateformes de l'application
   */

  private availablePlateforms: Plateform[];

  /**
   * liste observable rendu visible partout dans application
   */

  availablePlateforms$: BehaviorSubject<Plateform[]> = new BehaviorSubject(this.availablePlateforms);

  constructor(private httpClient: HttpClient) {}

  /**
   * cherche toutes les plateformes grâce à la methode CRUD
   */

  public getPlateform(): Observable<Plateform[]> {
    return this.httpClient.get<Plateform[]>('http://localhost:8080/api/plateforme');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des plateformes depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishPlateform() {
    this.getPlateform().subscribe(
      plateformsList => {
        this.availablePlateforms = plateformsList;
        this.availablePlateforms$.next(this.availablePlateforms);
      });
  }

  public findPlateform(plateformId: number): Observable<Plateform> {
    if (plateformId) {
      if (!this.availablePlateforms) {
        return this.getPlateform().pipe(map(plateforms => plateforms.find(plateform =>
          plateform.idPlateform === plateformId)));
      }
      return of(this.availablePlateforms.find(plateform => plateform.idPlateform === plateformId));
    } else {
      return of(new Plateform(0, '', null));
    }
  }

  public createPlateform(newPlateform: Plateform) {
    this.httpClient.post<Plateform>('http://localhost:8080/api/plateforme/ajout', newPlateform).subscribe(
      createPlateform => {
        this.availablePlateforms.push(createPlateform);
        this.availablePlateforms$.next(this.availablePlateforms);
      }
    );
  }

  public updatePlateform(plateform: Plateform) {
    this.httpClient.put<Plateform>(`http://localhost:8080/api/plateforme/modifid=${plateform.idPlateform}`, plateform).subscribe(
      updatePlateform => {
        this.availablePlateforms$.next(this.availablePlateforms);
      }
    );
  }

  public deletePlateform(plateform: Plateform) {
    this.httpClient.delete<Plateform>(`http://localhost:8080/api/plateforme/supprid=${plateform.idPlateform}`).subscribe(
      deletePlateform => {
        const index1 = this.availablePlateforms.indexOf(plateform);
        this.availablePlateforms.splice(index1, 1);
        this.availablePlateforms$.next(this.availablePlateforms);
      }
    );
  }

  /* TEST PRIMENG */
  public getPlateformPrimeNg() {
    return this.httpClient.get('http://localhost:8080/api/plateforme').toPromise().then(data => {
      return data;
    });
  }
}
