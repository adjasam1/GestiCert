import {Injectable} from '@angular/core';
import {Demand} from '../model/demand';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

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
    return this.httpClient.get<Demand[]>('http://localhost:8080/api/demande');
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

  public findDemand(demandId: number): Observable<Demand> {
    if (demandId) {
      if (!this.availableDemands) {
        return this.getDemand().pipe(map(demands => demands.find(demand =>
          demand.idDemand === demandId)));
      }
      return of(this.availableDemands.find(demand => demand.idDemand === demandId));
    } else {
      return of(new Demand(0, null, null, null, null, '', '', null, null, null, null, null));
    }
  }

  public createDemand(newDemand: Demand) {
    this.httpClient.post<Demand>('http://localhost:8080/api/demande/ajout', newDemand).subscribe(
      createDemand => {
        this.availableDemands.push(createDemand);
        this.availableDemands$.next(this.availableDemands);
      }
    );
  }

  public updateDemand(demand: Demand) {
    this.httpClient.put<Demand>(`http://localhost:8080/api/demande/modifid=${demand.idDemand}`, demand).subscribe(
      updateDemand => {
        this.availableDemands$.next(this.availableDemands);
      }
    );
  }

  public deleteDemand(demand: Demand) {
    this.httpClient.delete<Demand>(`http://localhost:8080/api/demande/supprid=${demand.idDemand}`).subscribe(
      deleteDemand => {
        const index1 = this.availableDemands.indexOf(demand);
        this.availableDemands.splice(index1, 1);
        this.availableDemands$.next(this.availableDemands);
      }
    );
  }

  public sendMail(demand: Demand) {
    this.httpClient.post<Demand>('http://localhost:8080/api/demande/mail', demand).subscribe();
  }

/*  public sendMail(demandId: number) {
    this.httpClient.post('http://localhost:8080/api/demande/mail', demandId).subscribe();
  }*/
}
