import {Injectable} from '@angular/core';
import {Demand} from '../model/demand';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DemandDataService {

  /**
   * liste des demandes de l'application
   */

  private availableDemands: Demand[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableDemands$: BehaviorSubject<Demand[]> = new BehaviorSubject(this.availableDemands);

  constructor(private httpClient: HttpClient) {}

  /**
   * cherche toutes les demandes grâce à la methode CRUD
   */

  public getDemand(): Observable<Demand[]> {
    return this.httpClient.get<Demand[]>('http://localhost:8080/demande');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des demandes depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishDemand() {
    this.getDemand().subscribe(
      demandsList => {
        this.availableDemands = demandsList;
        this.availableDemands$.next(this.availableDemands);
      });
  }
}
