import { Injectable } from '@angular/core';
import {Server} from '../model/server';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ServerDataService {

  /**
   * liste des serveurs de l'application
   */

  private availableServers: Server[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableServers$: BehaviorSubject<Server[]> = new BehaviorSubject(this.availableServers);

  constructor(private httpClient: HttpClient) {}

  /**
   * en privee car la fonction est appelee seulement dans le service
   */

  public getServer(): Observable<Server[]> {
    return this.httpClient.get<Server[]>('http://localhost:8080/api/serveur');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des serveurs depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishServer() {
    this.getServer().subscribe(
      serversList => {
        this.availableServers = serversList;
        this.availableServers$.next(this.availableServers);
      });
  }
}
