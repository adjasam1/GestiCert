import { Injectable } from '@angular/core';
import {StatusDemand} from '../model/statusDemand';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StatusDemandDataService {

  /**
   * liste des statuts de demande de l'application
   */

  private availableStatusDemands: StatusDemand[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableStatusDemands$: BehaviorSubject<StatusDemand[]> = new BehaviorSubject(this.availableStatusDemands);

  constructor(private httpClient: HttpClient) {}

  /**
   * en privee car la fonction est appelee seulement dans le service
   */

  public getStatusDemand(): Observable<StatusDemand[]> {
    return this.httpClient.get<StatusDemand[]>('http://localhost:8080/statutdemande');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des statuts de demande depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishStatusDemand() {
    this.getStatusDemand().subscribe(
      statusDemandsList => {
        this.availableStatusDemands = statusDemandsList;
        this.availableStatusDemands$.next(this.availableStatusDemands);
      });
  }
}
