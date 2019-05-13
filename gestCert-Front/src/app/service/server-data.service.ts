import { Injectable } from '@angular/core';
import {Server} from '../model/server';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Root} from '../model/root';
import {map} from 'rxjs/operators';

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

  public findServer(serverId: number): Observable<Server> {
    if (serverId) {
      if (!this.availableServers) {
        return this.getServer().pipe(map(servers => servers.find(server =>
          server.idServer === serverId)));
      }
      return of(this.availableServers.find(server => server.idServer === serverId));
    } else {
      return of(new Server(0, '', null));
    }
  }

  public createServer(newServer: Server) {
    this.httpClient.post<Server>('http://localhost:8080/api/serveur/ajout', newServer).subscribe(
      createServer => {
        this.availableServers.push(createServer);
        this.availableServers$.next(this.availableServers);
      }
    );
  }

  public updateServer(server: Server) {
    this.httpClient.put<Server>(`http://localhost:8080/api/serveur/modifid=${server.idServer}`, server).subscribe(
      updateRoot => {
        this.availableServers$.next(this.availableServers);
      }
    );
  }

  public deleteServer(server: Server) {
    this.httpClient.delete<Server>(`http://localhost:8080/api/serveur/supprid=${server.idServer}`).subscribe(
      deleteServer => {
        const index1 = this.availableServers.indexOf(server);
        this.availableServers.splice(index1, 1);
        this.availableServers$.next(this.availableServers);
      }
    );
  }

  /* TEST PRIMENG */
  public getServerPrimeNg() {
    return this.httpClient.get('http://localhost:8080/api/serveur').toPromise().then(data => {
      return data;
    });
  }

}
