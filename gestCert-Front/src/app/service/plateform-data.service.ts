import { Injectable } from '@angular/core';
import {Plateform} from '../model/plateform';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

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
    return this.httpClient.get<Plateform[]>('http://localhost:8080/plateforme');
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
}
