import { Injectable } from '@angular/core';
import {TypeDemand} from '../model/typeDemand';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TypeDemandDataService {

  /**
   * liste des types de demande de l'application
   */

  private availableTypeDemands: TypeDemand[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableTypeDemands$: BehaviorSubject<TypeDemand[]> = new BehaviorSubject(this.availableTypeDemands);

  constructor(private httpClient: HttpClient) {}

  /**
   * en privee car la fonction est appelee seulement dans le service
   */

  public getTypeDemand(): Observable<TypeDemand[]> {
    return this.httpClient.get<TypeDemand[]>('http://localhost:8080/api/typedemande');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des types de demande depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishTypeDemand() {
    this.getTypeDemand().subscribe(
      typeDemandsList => {
        this.availableTypeDemands = typeDemandsList;
        this.availableTypeDemands$.next(this.availableTypeDemands);
      });
  }
}
