import { Injectable } from '@angular/core';
import {Root} from '../model/root';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RootDataService {

  /**
   * liste des racines de l'application
   */

  private availableRoots: Root[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableRoots$: BehaviorSubject<Root[]> = new BehaviorSubject(this.availableRoots);

  constructor(private httpClient: HttpClient) {}

  /**
   * en privee car la fonction est appelee seulement dans le service
   */

  public getRoot(): Observable<Root[]> {
    return this.httpClient.get<Root[]>('http://localhost:8080/racine');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des racines depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishRoot() {
    this.getRoot().subscribe(
      rootsList => {
        this.availableRoots = rootsList;
        this.availableRoots$.next(this.availableRoots);
      });
  }
}
