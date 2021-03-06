import { Injectable } from '@angular/core';
import {Root} from '../model/root';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RootDataService {

  /**
   * liste des racines de l'application
   */

  public availableRoots: Root[] = [];

  /**
   * liste observable rendu visible partout dans application
   */

  availableRoots$: BehaviorSubject<Root[]> = new BehaviorSubject(this.availableRoots);

  constructor(private httpClient: HttpClient) {}

  /**
   * en privee car la fonction est appelee seulement dans le service
   */

  public getRoot(): Observable<Root[]> {
    return this.httpClient.get<Root[]>('http://localhost:8080/api/racine');
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

  public findRoot(rootId: number): Observable<Root> {
    if (rootId) {
      if (!this.availableRoots) {
        return this.getRoot().pipe(map(roots => roots.find(root =>
          root.idRoot === rootId)));
      }
      return of(this.availableRoots.find(root => root.idRoot === rootId));
    } else {
      return of(new Root(0, '', null));
    }
  }

  public createRoot(newRoot: Root) {
    this.availableRoots.push(newRoot);
    this.availableRoots$.next(this.availableRoots);
    return this.httpClient.post<Root>('http://localhost:8080/api/racine/ajout', newRoot);
  }

  public updateRoot(root: Root) {
    return this.httpClient.put<Root>(`http://localhost:8080/api/racine/modifid=${root.idRoot}`, root);
  }

  public deleteRoot(root: Root) {
    return this.httpClient.delete<Root>(`http://localhost:8080/api/racine/supprid=${root.idRoot}`);
  }

  /* TEST PRIMENG */
  public getRootPrimeNg() {
    return this.httpClient.get<any>('http://localhost:8080/api/racine').toPromise().then(data => {
      return data;
    });
  }

}
